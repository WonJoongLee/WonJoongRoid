package org.sopt.androidseminar.data.response

import com.google.gson.annotations.SerializedName

data class ResponseLoginData(
    val success: Boolean,
    val message: String,
    val data: LoginData?
) {
    data class LoginData(
        /**
         * SerializedName은 SerializedName 파라미터로 들어오는 String 값을
         * 아래에서 선언한 변수 명으로 바꿔주는 것이다.
         * 서버에서는 UserId라고 선언했는데, Kotlin에서는 변수명 첫 글자를 소문자로
         * 사용하기 떄문에, 바꿔줄 필요가 있다.
         * 만약 data class에서 선언한 이름과 서버에서의 이름이 같다면 SerializedName이 필요하지 않다.
         **/
        @SerializedName("UserId")
        val userId: Int,
        val user_nickname: String,
        val token: String
    )
}
