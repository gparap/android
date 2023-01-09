package gparap.apps.authentication.api

import gparap.apps.authentication.data.AuthenticationModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthenticationService {
    @FormUrlEncoded
    @POST("login.php")
    fun signInWithEmailAndPassword(
        @Field("email") first: String?,
        @Field("password") last: String?
    ): Call<AuthenticationModel?>?

    @FormUrlEncoded
    @POST("register.php")
    fun createUserWithEmailAndPassword(
        @Field("email") first: String?,
        @Field("username") middle: String?,
        @Field("password") last: String?
    ): Call<AuthenticationModel?>?
}