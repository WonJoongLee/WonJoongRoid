package org.sopt.androidseminar.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.sopt.androidseminar.R

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        connectFragment()
    }

    private fun connectFragment() {
        val followerFragment = FollowerFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragment_follower, followerFragment)
        transaction.commit()
    }
}