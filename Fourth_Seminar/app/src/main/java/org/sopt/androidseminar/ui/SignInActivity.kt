package org.sopt.androidseminar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import org.sopt.androidseminar.LifecycleObserver
import org.sopt.androidseminar.api.ServiceCreator
import org.sopt.androidseminar.data.request.RequestLoginData
import org.sopt.androidseminar.data.response.ResponseLoginData
import org.sopt.androidseminar.databinding.ActivityMainBinding
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
        //Log.d(ACTIVITY_NAME, "onCreate")

        loginButtonClickEvent()
        signUpButtonClickEvent()
        LifecycleObserver(javaClass.simpleName, this.lifecycle).registerLogger()
    }

    private fun loginButtonClickEvent() {
        binding.btLogin.setOnClickListener {
            if (isIdPwETEmpty()) { // 둘 중 하나라도 비어있으면,
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요!", Toast.LENGTH_SHORT)
                    .show()
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
                            Toast.makeText(
                                this@SignInActivity,
                                data?.user_nickname,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseLoginData>, t: Throwable) {
                        Log.e("NetworkText", "error:$t")
                    }

                })
//                val intent = Intent(this, HomeActivity::class.java)
//                startActivity(intent)
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
}