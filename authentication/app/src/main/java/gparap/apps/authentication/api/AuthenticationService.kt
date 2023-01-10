package gparap.apps.authentication.api

import gparap.apps.authentication.data.AuthenticationModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/*
 * Copyright 2023 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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