package com.sharipov.passwordkeeper.Domain.DataBase;


import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sharipov.passwordkeeper.Domain.Model.PasswordDTO;
import com.sharipov.passwordkeeper.Domain.Model.Password;
import com.sharipov.passwordkeeper.Presentation.Repository.RepositoryTasks;

import java.util.List;

public class PasswordRepository implements RepositoryTasks {

    private PasswordDAO passwordDAO;
    private LiveData<List<PasswordDTO>> mutableAllPassword = new MutableLiveData<>();


    public PasswordRepository(Application application) {
        PasswordRoomDataBase db = PasswordRoomDataBase.getDatabase(application);
        passwordDAO = db.applianceDao();
        mutableAllPassword = passwordDAO.getAllPasswords();
    }
    
    // create
    @Override
    public void addPassword(Password password) {
        PasswordDTO dto = PasswordDTO.DtoFromPassword(password);

        PasswordRoomDataBase.databaseWriteExecutor.execute(() -> {
            passwordDAO.addPassword(dto);
        });
    }

    // read
    @Override
    public LiveData<List<PasswordDTO>> getAllPasswords() {
        return mutableAllPassword;
    }

    // update
    @Override
    public void updatePasswordNotes(String uid, String noteFile) {
        PasswordRoomDataBase.databaseWriteExecutor.execute(() -> {
            passwordDAO.updateNoteFile(uid, noteFile);
        });
    }

    // delete
    @Override
    public void deletePassword(Password password) {
        PasswordDTO dto = PasswordDTO.DtoFromPassword(password);

        PasswordRoomDataBase.databaseWriteExecutor.execute(() -> {
            passwordDAO.deletePassword(dto);
        });
    }
}
