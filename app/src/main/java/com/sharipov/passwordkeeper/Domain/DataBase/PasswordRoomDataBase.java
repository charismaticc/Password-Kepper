package com.sharipov.passwordkeeper.Domain.DataBase;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.sharipov.passwordkeeper.Domain.Model.PasswordDTO;
import com.sharipov.passwordkeeper.Domain.Model.Password;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {PasswordDTO.class}, version = 1, exportSchema = false)
public abstract class PasswordRoomDataBase extends RoomDatabase {
    public abstract PasswordDAO passwordDAO();

    private static volatile PasswordRoomDataBase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static PasswordRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (PasswordRoomDataBase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            PasswordRoomDataBase.class, "pwd_keeper_app")
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(() -> {
                                        Password example = new Password();
                                        example.setWebsiteName("Google");
                                        example.setWebsiteAddress("google.com");
                                        example.setLogin("my_gmail@gmail.com");
                                        example.setPassword("qwerty");
                                        example.setDescription("Example password");
                                        getDatabase(context).passwordDAO().addPassword(PasswordDTO.DtoFromPassword(example));
                                    });
                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
