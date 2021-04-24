package org.sopt.androidseminar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.androidseminar.databinding.ActivityMainBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val signUpActivityLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val data = it.data
                data?.let {
                    binding.etGithubId.setText(data.getStringExtra("githubID").toString())
                    binding.etGithubPw.setText(data.getStringExtra("pw").toString())
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // 주의, 코틀린에서 프로퍼티기 때문에 .root로 바로 getter 호출이 된다.
        Log.d(ACTIVITY_NAME, "onCreate")

        loginButtonClickEvent()
        signUpButtonClickEvent()
    }

    private fun loginButtonClickEvent() {
        binding.btLogin.setOnClickListener {
            if (isIdPwETEmpty()) { // 둘 중 하나라도 비어있으면,
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요!", Toast.LENGTH_SHORT)
                    .show()
            } else { // 모두 차 있다면,
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun isIdPwETEmpty() =
        binding.etGithubId.text.isNullOrBlank() || binding.etGithubPw.text.isNullOrBlank()

    private fun signUpButtonClickEvent() {
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(ACTIVITY_NAME, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(ACTIVITY_NAME, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(ACTIVITY_NAME, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(ACTIVITY_NAME, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(ACTIVITY_NAME, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(ACTIVITY_NAME, "onDestroy")
    }

    companion object {
        private const val ACTIVITY_NAME = "SignInActivity"
    }
}