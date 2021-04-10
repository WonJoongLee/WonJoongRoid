package org.sopt.androidseminar

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import org.sopt.androidseminar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root) // 주의, 코틀린에서 프로퍼티기 때문에 .root로 바로 getter 호출이 된다.
        

        initButtonClickEvent()
    }

    private fun initButtonClickEvent() {
        binding.loginButton.setOnClickListener {
            val userID = binding.ETId.text
            if (userID.isNullOrBlank()) { // 비어있으면,
                Toast.makeText(this@MainActivity, "Id를 입력해주세요", Toast.LENGTH_SHORT)
                    .show() // this@MainActivity는 this가 MainActivity임을 알려준다.
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this@MainActivity, "안녕하세요", Toast.LENGTH_SHORT)
                    .show() // this@MainActivity는 this가 MainActivity임을 알려준다.
            }
        }
    }
}