package com.sharipov.passwordkeeper.API.Service;

import java.util.List;

public class PasswordResponse {
    public List<String> pws;
    public Object error;

    public List<String> getPws() {
        return pws;
    }

    public void setPws(List<String> pws) {
        this.pws = pws;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }
}
