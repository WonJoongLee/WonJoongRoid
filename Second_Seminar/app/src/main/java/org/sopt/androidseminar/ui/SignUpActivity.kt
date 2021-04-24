package org.sopt.androidseminar.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.sopt.androidseminar.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(ACTIVITY_NAME, "onCreate")

        signUpButtonClickEvent()
    }

    private fun signUpButtonClickEvent() {
        binding.btSignUp.setOnClickListener {
            if (isGithubInfoEditTextEmpty()) {
                Toast.makeText(this, "빈 칸이 있는지 확인해주세요", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, SignUpActivity::class.java)
                intent.putExtra("githubID", binding.etGithubId.text.toString())
                intent.putExtra("pw", binding.etGithubPw.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    // 하나라도 비어있지 않은 edit text가 있으면 true 반환, 모두 채워져 있다면 false 반환
    private fun isGithubInfoEditTextEmpty(): Boolean =
        binding.etGithubName.text.isNullOrBlank() || binding.etGithubId.text.isNullOrBlank() || binding.etGithubPw.text.isNullOrBlank()

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
        private const val ACTIVITY_NAME = "SignUpActivity"
    }
}