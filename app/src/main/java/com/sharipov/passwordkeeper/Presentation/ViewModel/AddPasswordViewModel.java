package com.sharipov.passwordkeeper.Presentation.ViewModel;

import androidx.lifecycle.ViewModel;

import com.sharipov.passwordkeeper.Domain.Model.Password;
import com.sharipov.passwordkeeper.Presentation.Repository.Repository;

public class AddPasswordViewModel  extends ViewModel {
    public void AddPassword(
            String websiteAddress,
            String websiteName,
            String login,
            String password,
            String description
    ) {
        Password newPassword = new Password();
        newPassword.setWebsiteAddress(websiteAddress);
        newPassword.setWebsiteName(websiteName);
        newPassword.setLogin(login);
        newPassword.setPassword(password);
        newPassword.setDescription(description);

        Repository.getRepository().addPassword(newPassword);
    }
}
