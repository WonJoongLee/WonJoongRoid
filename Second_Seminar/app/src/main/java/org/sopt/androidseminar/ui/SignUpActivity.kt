package org.sopt.androidseminar.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidseminar.R
import org.sopt.androidseminar.adapters.RepositoryAdapter
import org.sopt.androidseminar.data.RepositoryInfo
import org.sopt.androidseminar.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private val activityName = "SignUpActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(activityName, "onCreate")

        signUpButtonClickEvent()

    }


    private fun signUpButtonClickEvent() {
        binding.btSignUp.setOnClickListener {
            if (isEditTextEmpty()) {
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
    private fun isEditTextEmpty(): Boolean {
        return binding.etGithubName.text.isNullOrBlank() || binding.etGithubId.text.isNullOrBlank() || binding.etGithubPw.text.isNullOrBlank()
    }

    override fun onStart() {
        super.onStart()
        Log.d(activityName, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(activityName, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(activityName, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(activityName, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(activityName, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(activityName, "onDestroy")
    }
}