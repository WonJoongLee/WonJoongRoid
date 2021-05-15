package org.sopt.androidseminar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.androidseminar.util.LifecycleObserver
import org.sopt.androidseminar.api.ServiceCreator
import org.sopt.androidseminar.data.request.RequestLoginData
import org.sopt.androidseminar.data.response.ResponseLoginData
import org.sopt.androidseminar.databinding.ActivityMainBinding
import org.sopt.androidseminar.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

        loginButtonClickEvent()
        signUpButtonClickEvent()
        LifecycleObserver(javaClass.simpleName, this.lifecycle).registerLogger()
    }

    private fun loginButtonClickEvent() {
        binding.btLogin.setOnClickListener {
            if (isIdPwETEmpty()) { // 둘 중 하나라도 비어있으면,
                toast("아이디/비밀번호를 확인해주세요!")
            } else { // 모두 차 있다면,
                val requestLoginData = RequestLoginData(
                    email = binding.etGithubId.text.toString(),
                    password = binding.etGithubPw.text.toString()
                )
                val call: Call<ResponseLoginData> =
                    ServiceCreator.soptService.postLogin(requestLoginData)
                call.enqueue(object : Callback<ResponseLoginData> {
                    override fun onResponse(
                        call: Call<ResponseLoginData>,
                        response: Response<ResponseLoginData>
                    ) {
                        if (response.isSuccessful) {
                            val data = response.body()?.data
                            toast(data?.user_nickname + " 로그인 성공")
                            startHomeActivity()
                        } else {
                            //Log.e("error", response.errorBody().toString())
                            toast("아이디/비밀번호를 확인해주세요!")
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                        Log.e("NetworkText", "error:$t")
                    }
                })
            }
        }
    }

    private fun startHomeActivity() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun isIdPwETEmpty() =
        binding.etGithubId.text.isNullOrBlank() || binding.etGithubPw.text.isNullOrBlank()

    private fun signUpButtonClickEvent() {
        binding.tvRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            signUpActivityLauncher.launch(intent)
        }
    }
}