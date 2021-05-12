package org.sopt.androidseminar.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.androidseminar.util.LifecycleObserver
import org.sopt.androidseminar.databinding.ActivitySignUpBinding
import org.sopt.androidseminar.util.toast

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpButtonClickEvent()
        LifecycleObserver(javaClass.simpleName, this.lifecycle).registerLogger()
    }

    private fun signUpButtonClickEvent() {
        binding.btSignUp.setOnClickListener {
            if (isGithubInfoEditTextEmpty()) {
                toast("빈 칸이 있는지 확인해주세요")
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
}