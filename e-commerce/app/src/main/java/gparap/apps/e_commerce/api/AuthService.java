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
package gparap.apps.e_commerce.api;

import gparap.apps.e_commerce.data.AuthResponseModel;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthService {
    /**
     * Login user with username and password.
     */
    @POST("login.php")
    @FormUrlEncoded
    Call<AuthResponseModel> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    /**
     * Register user with e-mail, username and password.
     */
    @POST("register.php")
    @FormUrlEncoded
    Call<AuthResponseModel> registerUser(
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password
    );
}
