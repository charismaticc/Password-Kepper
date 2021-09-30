package com.sharipov.passwordkeeper.Presentation.ViewModel;

import androidx.lifecycle.ViewModel;

import com.sharipov.passwordkeeper.Domain.Model.Password;
import com.sharipov.passwordkeeper.Presentation.Repository.Repository;

public class AddPasswordViewModel  extends ViewModel {
    public void AddAppliance(
            String websiteName,
            String websiteAddress,
            String login,
            String password,
            String description
    ) {
        Password newPassword = new Password();
        newPassword.setWebsiteName(websiteName);
        newPassword.setWebsiteAddress(websiteAddress);
        newPassword.setLogin(login);
        newPassword.setPassword(password);
        newPassword.setDescription(description);

        Repository.getRepository().addPassword(newPassword);
    }
}
