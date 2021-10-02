package com.sharipov.passwordkeeper.Presentation.Repository.Mock;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.sharipov.passwordkeeper.Domain.Model.Password;
import com.sharipov.passwordkeeper.Presentation.Repository.RepositoryTasks;

import java.util.ArrayList;
import java.util.List;

public class MockDataBase implements RepositoryTasks {
    MutableLiveData<List<Password>> mockData;
    List<Password> passwordList;

    public MockDataBase() {
        passwordList = new ArrayList<>();

        Password mockPasswordFirst = new Password();
        mockPasswordFirst.setWebsiteName("Google");
        mockPasswordFirst.setWebsiteAddress("google.com");
        mockPasswordFirst.setLogin("my_gmail@gmail.com");
        mockPasswordFirst.setPassword("qwerty");
        mockPasswordFirst.setDescription("Example password");
        passwordList.add(mockPasswordFirst);

        mockData = new MutableLiveData<>(passwordList);
    }


    @Override
    public void addPassword(Password password) {
        passwordList.add(password);

        mockData.setValue(passwordList);
    }

    @Override
    public MutableLiveData <List<Password>> getAllPasswords() {
        return mockData;
    }

    @Override
    public void updatePasswordNotes(String uid, String noteFile) {
        // TODO: nice one
        return;
    }

    @Override
    public void deletePassword(Password password) {
        passwordList.remove(password);

        mockData.setValue(passwordList);
    }
}
