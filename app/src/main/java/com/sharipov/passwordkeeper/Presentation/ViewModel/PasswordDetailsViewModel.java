package com.sharipov.passwordkeeper.Presentation.ViewModel;


import androidx.lifecycle.ViewModel;
import com.sharipov.passwordkeeper.Domain.Model.Password;

public class PasswordDetailsViewModel extends ViewModel {
    private Password password;

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

}
