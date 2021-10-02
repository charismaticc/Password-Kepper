package com.sharipov.passwordkeeper.Presentation.ViewModel;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.sharipov.passwordkeeper.Domain.Model.Password;
import com.sharipov.passwordkeeper.Presentation.Repository.Repository;

public class PasswordDetailsViewModel extends ViewModel {
    private Password password;

    public Password getPassword() {
        return password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void updateNoteFile(String uid, String noteFile) {
        Repository.getRepository().updatePasswordNotes(uid, noteFile);
    }
    public void saveUri(Uri uri) {
        password.setNoteFile(uri.toString());
        updateNoteFile(password.getId(), uri.toString());
    }

    public void clearUri() {
        password.setNoteFile(null);
        updateNoteFile(password.getId(), null);
    }
}
