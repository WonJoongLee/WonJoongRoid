package org.sopt.androidseminar.api.soptlogin

import org.sopt.androidseminar.data.request.RequestLoginData
import org.sopt.androidseminar.data.request.RequestSignupData
import org.sopt.androidseminar.data.response.ResponseLoginData
import org.sopt.androidseminar.data.response.ResponseSignupData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SoptService {
    @POST("/login/signin")
    fun postLogin(
        @Body body: RequestLoginData
    ): Call<ResponseLoginData>

    @POST("/login/signup")
    fun postSignUp(
        @Body body: RequestSignupData
    ): Call<ResponseSignupData>
}