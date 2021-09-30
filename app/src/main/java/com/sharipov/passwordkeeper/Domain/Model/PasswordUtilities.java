package com.sharipov.passwordkeeper.Domain.Model;

import com.google.gson.Gson;

public class PasswordUtilities {
    public static String ApplianceToJson(Password password) {
        Gson gson = new Gson();
        return gson.toJson(password);
    }

    public static Password JsonToAppliance(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Password.class);
    }
}
