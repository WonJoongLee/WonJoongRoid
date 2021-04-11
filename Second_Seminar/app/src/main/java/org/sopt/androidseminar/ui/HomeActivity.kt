package org.sopt.androidseminar.ui

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

    private lateinit var binding : ActivityHomeBinding
    private val activityName = "HomeActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(activityName, "onCreate")

        var repoList = mutableListOf<RepositoryInfo>()
        val repoAdapter = RepositoryAdapter()
        val repoRecyclerView = findViewById<RecyclerView>(R.id.rv_repository)
        repoList = repoDataInput(repoList)
        repoAdapter.data = repoList
        repoRecyclerView.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        repoRecyclerView.adapter = repoAdapter
        repoRecyclerView.setHasFixedSize(false)

    }


    private fun repoDataInput(repoList : MutableList<RepositoryInfo>) : MutableList<RepositoryInfo>{
        repoList.add(RepositoryInfo("레포지토리 이름1", "레포지토리 설명", "레포지토리 언어"))
        repoList.add(RepositoryInfo("레포지토리 이름2", "레포지토리 설명레포지토리 설명레포지토리 설명레포지토리 설명레포지토리 설명레포지토리 설명레포지토리 설명레포지토리 설명레포지토리 설명", "레포지토리 언어"))
        repoList.add(RepositoryInfo("레포지토리 이름3", "레포지토리 설명", "레포지토리 언어"))
        repoList.add(RepositoryInfo("레포지토리 이름4", "레포지토리 설명", "레포지토리 언어"))
        repoList.add(RepositoryInfo("레포지토리 이름5", "레포지토리 설명", "레포지토리 언어"))
        repoList.add(RepositoryInfo("레포지토리 이름6", "레포지토리 설명", "레포지토리 언어"))
        repoList.add(RepositoryInfo("레포지토리 이름7", "레포지토리 설명", "레포지토리 언어"))
        repoList.add(RepositoryInfo("레포지토리 이름8", "레포지토리 설명", "레포지토리 언어"))
        repoList.add(RepositoryInfo("레포지토리 이름9", "레포지토리 설명", "레포지토리 언어"))
        repoList.add(RepositoryInfo("레포지토리 이름10", "레포지토리 설명", "레포지토리 언어"))
        return repoList
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