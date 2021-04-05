package org.sopt.androidseminar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.androidseminar.databinding.ActivityMainBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // 주의, 코틀린에서 프로퍼티기 때문에 .root로 바로 getter 호출이 된다.


        loginButtonClickEvent()
    }

    private fun loginButtonClickEvent() {
        binding.loginButton.setOnClickListener {
            val userID = binding.idET.text
            val userPW = binding.pwET.text
            if (userID.isNullOrBlank() || userPW.isNullOrBlank()) { // 둘 중 하나라도 비어있으면,
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요!", Toast.LENGTH_SHORT)
                    .show()
            } else { // 모두 차 있다면,
                // TODO Home Activity로 이동
            }
        }
    }
}