package org.sopt.androidseminar.data.response

import com.google.gson.annotations.SerializedName

data class ResponseSignupData(
    val success: String,
    val message: String,
    @SerializedName("data")
    val responseData: SignupData
) {
    data class SignupData(
        val nickname: String
    )
}

