package com.sharipov.passwordkeeper.Presentation.Repository;

import androidx.lifecycle.LiveData;

import com.sharipov.passwordkeeper.Domain.Model.PasswordDTO;
import com.sharipov.passwordkeeper.Domain.Model.Password;

import java.util.List;

public interface RepositoryTasks {
    // create
    void addPassword(Password password);

    // read
    <T extends Password> LiveData<List<T>> getAllPasswords();

    // delete
    void deletePassword(Password password);
}
