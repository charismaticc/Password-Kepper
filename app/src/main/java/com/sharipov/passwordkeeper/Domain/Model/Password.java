package com.sharipov.passwordkeeper.Domain.Model;

import androidx.annotation.NonNull;

import java.util.UUID;

public class Password {
    @NonNull
    private String id;
    private String websiteAddress;
    private String websiteName;
    private String login;
    private String password;
    private String description;
    private String noteFile;

    public Password() {
        this.id = UUID.randomUUID().toString();
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getNoteFile() {
        return noteFile;
    }

    public void setNoteFile(String noteFile) {
        this.noteFile = noteFile;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) { this.websiteAddress = websiteAddress; }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
