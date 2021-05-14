package org.sopt.androidseminar.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.sopt.androidseminar.api.ServiceCreator
import org.sopt.androidseminar.data.request.RequestSignupData
import org.sopt.androidseminar.data.response.ResponseSignupData
import org.sopt.androidseminar.util.LifecycleObserver
import org.sopt.androidseminar.databinding.ActivitySignUpBinding
import org.sopt.androidseminar.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
                val requestSignUpData = RequestSignupData(
                    email = binding.etGithubEmail.text.toString(),
                    password = binding.etGithubPw.text.toString(),
                    sex = "0",
                    nickname = binding.etGithubNickname.text.toString(),
                    phone = "010-1234-5678",
                    birth = "1234-56-78"
                )
                val call: Call<ResponseSignupData> =
                    ServiceCreator.soptService.postSignUp(requestSignUpData)
                call.enqueue(object : Callback<ResponseSignupData> {
                    override fun onResponse(
                        call: Call<ResponseSignupData>,
                        response: Response<ResponseSignupData>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()?.responseData
                            toast(data?.nickname ?: "")
                        }
                    }

                    override fun onFailure(call: Call<ResponseSignupData>, t: Throwable) {
                        Log.e("NetworkFailure", "error:$t")
                    }

                })
                val intent = Intent(this, SignUpActivity::class.java)
                intent.putExtra("githubID", binding.etGithubNickname.text.toString())
                intent.putExtra("pw", binding.etGithubPw.text.toString())
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    // 하나라도 비어있지 않은 edit text가 있으면 true 반환, 모두 채워져 있다면 false 반환
    private fun isGithubInfoEditTextEmpty(): Boolean =
        binding.etGithubEmail.text.isNullOrBlank() || binding.etGithubNickname.text.isNullOrBlank() || binding.etGithubPw.text.isNullOrBlank()
}