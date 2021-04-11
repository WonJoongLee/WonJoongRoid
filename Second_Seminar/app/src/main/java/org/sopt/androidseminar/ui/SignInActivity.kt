package org.sopt.androidseminar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import org.sopt.androidseminar.databinding.ActivityMainBinding

class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val activityName = "SignInActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // 주의, 코틀린에서 프로퍼티기 때문에 .root로 바로 getter 호출이 된다.
        Log.d(activityName, "onCreate")

        loginButtonClickEvent()
        signUpButtonClickEvent()
    }

    private fun loginButtonClickEvent() {
        binding.loginButton.setOnClickListener {
            val userID = binding.idET.text
            val userPW = binding.pwET.text
            if (userID.isNullOrBlank() || userPW.isNullOrBlank()) { // 둘 중 하나라도 비어있으면,
                Toast.makeText(this, "아이디/비밀번호를 확인해주세요!", Toast.LENGTH_SHORT)
                    .show()
            } else { // 모두 차 있다면,
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun signUpButtonClickEvent() {
        binding.registerTV.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivityForResult(intent, 0)
        }
    }

    //값 수신하는 부분
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when(requestCode){
            0->{
                if(resultCode == RESULT_OK){
                    data?.let{
                        binding.idET.setText(data.getStringExtra("githubID").toString())
                        binding.pwET.setText(data.getStringExtra("pw").toString())
                    }
                }
            }
        }
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