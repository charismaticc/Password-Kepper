package com.sharipov.passwordkeeper.Domain.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.sharipov.passwordkeeper.Domain.Model.PasswordDTO;

import java.util.List;

@Dao
public interface PasswordDAO {
    // create
    @Insert
    void addPassword(PasswordDTO Password);

    // read
    @Query("SELECT * FROM pwd_keeper_app")
    LiveData<List<PasswordDTO>> getAllPasswords();

    //delete
    @Delete
    void deletePassword(PasswordDTO Password);
}
