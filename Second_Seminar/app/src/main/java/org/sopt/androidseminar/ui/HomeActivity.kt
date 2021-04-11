package org.sopt.androidseminar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidseminar.R
import org.sopt.androidseminar.adapters.RepositoryAdapter
import org.sopt.androidseminar.data.RepositoryInfo
import org.sopt.androidseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val ACTIVITYNAME = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(ACTIVITYNAME, "onCreate")

        setRepoRv()

        moreButtonClickedEvent()
    }

    private fun setRepoRv() {
        val repoTempList = mutableListOf<RepositoryInfo>()
        val repoAdapter = RepositoryAdapter()
        val repoRecyclerView = binding.rvRepository
        val repoList = repoDataInput(repoTempList).toList()
        repoAdapter.data = repoList
        repoRecyclerView.layoutManager =
            LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        repoRecyclerView.adapter = repoAdapter
        repoRecyclerView.setHasFixedSize(false)
        repoAdapter.notifyDataSetChanged()
    }

    private fun moreButtonClickedEvent() {
        binding.btMore.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun repoDataInput(repoList: MutableList<RepositoryInfo>): MutableList<RepositoryInfo> {
        for (i in 1..10) {
            repoList.add(RepositoryInfo("레포지토리 이름".plus(i), "레포지토리 설명".plus(i), "레포지토리 언어".plus(i)))
        }
        //Repository 이름이나 설명이 긴 경우 처리를 보여주기 위해 임시로 추가
        repoList.add(
            RepositoryInfo(
                "레포지토리 이름레포지토리 이름레포지토리 이름레포지토리 이름레포지토리 이름레포지토리 이름레포지토리 이름",
                "레포지토리 설명레포지토리 설명레포지토리 설명레포지토리 설명레포지토리 설명",
                "레포지토리 언어"
            )
        )
        return repoList
    }

    override fun onStart() {
        super.onStart()
        Log.d(ACTIVITYNAME, "onStart")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(ACTIVITYNAME, "onRestart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(ACTIVITYNAME, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(ACTIVITYNAME, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(ACTIVITYNAME, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(ACTIVITYNAME, "onDestroy")
    }

}