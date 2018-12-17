package com.resolvebug.app.easymoney;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitApiInterface {

    String BASE_URL = "https://resolvebug.com/earnmoney/api/";

    @POST("create/CreateUser.php")
    Call<UserDetailsPojo> saveUserDetails(@Body UserDetailsPojo post);

    @POST("read/GetUserDetails.php")
    Call<UserDetailsPojo> getUserDetails(@Body UserDetailsPojo post);
}
