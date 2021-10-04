package com.sharipov.passwordkeeper.Domain.Model;

import androidx.room.Entity;

@Entity(tableName = "pwd_keeper_app", primaryKeys = {"id"})
public class PasswordDTO extends Password {
    public static PasswordDTO DtoFromPassword (Password password) {
        PasswordDTO dto = new PasswordDTO();

        dto.setId(password.getId());
        dto.setWebsiteAddress(password.getWebsiteAddress());
        dto.setWebsiteName(password.getWebsiteName());
        dto.setLogin(password.getLogin());
        dto.setPassword(password.getPassword());
        dto.setDescription(password.getDescription());
//        dto.setNoteFile(password.getNoteFile());

        return dto;
    }
}
