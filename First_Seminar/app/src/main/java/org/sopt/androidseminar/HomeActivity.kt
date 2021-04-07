package org.sopt.androidseminar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.sopt.androidseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding : ActivityHomeBinding
    private val activityName = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(activityName, "onCreate")
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