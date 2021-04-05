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
        Log.e(activityName, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.e(activityName, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(activityName, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(activityName, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e(activityName, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e(activityName, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(activityName, "onDestroy")
    }

}