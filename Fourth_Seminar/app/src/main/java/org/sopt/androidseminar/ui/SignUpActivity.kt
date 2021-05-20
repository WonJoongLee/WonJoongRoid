package org.sopt.androidseminar.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import org.sopt.androidseminar.api.ServiceCreator
import org.sopt.androidseminar.data.request.RequestSignupData
import org.sopt.androidseminar.data.response.ResponseSignupData
import org.sopt.androidseminar.databinding.ActivitySignUpBinding
import org.sopt.androidseminar.util.LifecycleObserver
import org.sopt.androidseminar.util.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var callbackMethod: OnDateSetListener
    private var birthdayStr = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signUpButtonClickEvent()
        LifecycleObserver(javaClass.simpleName, this.lifecycle).registerLogger()

        btBirthdayOnClick()
    }

    private fun signUpButtonClickEvent() {
        binding.btSignUp.setOnClickListener {
            if (isGithubInfoEditTextEmpty()) {
                toast("빈 칸이 있는지 확인해주세요")
            } else {
                val requestSignUpData = RequestSignupData(
                    email = binding.etGithubEmail.text.toString(),
                    password = binding.etGithubPw.text.toString(),
                    sex = if (binding.rbMale.isChecked) {
                        "0"
                    } else {
                        "1"
                    },
                    nickname = binding.etGithubNickname.text.toString(),
                    phone = binding.etPhoneNumber.text.toString(),
                    birth = binding.tvBirthdayDate.text.toString()
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
                            if (response.body()?.success == true) {
                                toast(data?.nickname ?: "")
                                startSignInActivity()
                            } else {
                                toast("Error")
                            }
                        }
                    }
                    override fun onFailure(call: Call<ResponseSignupData>, t: Throwable) {
                        Log.e("NetworkFailure", "error:$t")
                    }
                })
            }
        }
    }

    private fun startSignInActivity() {
        val intent = Intent(this, SignUpActivity::class.java)
        intent.putExtra("githubID", binding.etGithubNickname.text.toString())
        intent.putExtra("pw", binding.etGithubPw.text.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    // 하나라도 비어있지 않은 edit text가 있으면 true 반환, 모두 채워져 있다면 false 반환
    private fun isGithubInfoEditTextEmpty(): Boolean =
        binding.etGithubEmail.text.isNullOrBlank() || binding.etGithubNickname.text.isNullOrBlank() || binding.etGithubPw.text.isNullOrBlank()

    private fun btBirthdayOnClick() {
        binding.btBirthday.setOnClickListener {
            initializeDialogListener()
            onClickHandler()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initializeDialogListener() {
        callbackMethod =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                birthdayStr = "$year.${monthOfYear + 1}.$dayOfMonth"
                binding.tvBirthdayDate.text = birthdayStr // monthofyear가 0-11까지여서 +1을 해줘야함.
            }
    }

    private fun onClickHandler() {
        val dialog = DatePickerDialog(this, callbackMethod, 1999, 5, 1) // 실제로는 6월 표시
        dialog.show()
    }
}