package com.sharipov.passwordkeeper.Presentation.Repository;

import android.app.Application;

import com.sharipov.passwordkeeper.Domain.DataBase.PasswordRepository;
import com.sharipov.passwordkeeper.Presentation.Repository.Mock.MockDataBase;

public class Repository {
    static RepositoryTasks repository;

    static public void init(Application application) {
        if (repository == null) {
            repository = new PasswordRepository(application);
        }
    }

    static public RepositoryTasks getRepository() {
        if (repository == null) {
            repository = new MockDataBase();
        }
        return repository;
    }
}
