package com.sharipov.passwordkeeper.Presentation.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.sharipov.passwordkeeper.Domain.Model.Password;
import com.sharipov.passwordkeeper.Presentation.Repository.Repository;

import java.util.List;

public class PasswordListViewModel extends ViewModel {
    public LiveData<List<Password>> getAllPasswords() {
        return Repository.getRepository().getAllPasswords();
    }

    public void deleteAppliance(Password password) {
        Repository.getRepository().deletePassword(password);
    }
}
