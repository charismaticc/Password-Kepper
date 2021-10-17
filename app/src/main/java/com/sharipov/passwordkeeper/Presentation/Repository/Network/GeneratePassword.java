package com.sharipov.passwordkeeper.Presentation.Repository.Network;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeneratePassword {
    @GET("/api/v1/alphanumeric/json?")
    Call<ResponseBody> generatePassword(@Query("c") int count, @Query("l") int len );
}
