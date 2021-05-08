package org.sopt.androidseminar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidseminar.LifecycleObserver
import org.sopt.androidseminar.R
import org.sopt.androidseminar.adapters.RepositoryAdapter
import org.sopt.androidseminar.data.RepositoryInfo
import org.sopt.androidseminar.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRepoRv()
        moreButtonClickedEvent()
        LifecycleObserver(javaClass.simpleName, this.lifecycle).registerLogger()
    }

    private fun setRepoRv() {
        val repoList = mutableListOf<RepositoryInfo>()
        initData(repoList)

        val repoAdapter = RepositoryAdapter()
        val repoRecyclerView = binding.rvRepository
        repoRecyclerView.adapter = repoAdapter
        repoRecyclerView.setHasFixedSize(false)
        repoAdapter.setItemList(repoList)
    }

    private fun moreButtonClickedEvent() {
        binding.btMore.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initData(repoList: MutableList<RepositoryInfo>) {
        (1..10).forEach {
            repoList.add(RepositoryInfo("레포지토리 이름".plus(it), "레포지토리 설명".plus(it), "레포지토리 언어".plus(it)))
        }
        //Repository 이름이나 설명이 긴 경우 처리를 보여주기 위해 임시로 추가
        repoList.add(
            RepositoryInfo(
                "레포지토리 이름레포지토리 이름레포지토리 이름레포지토리 이름레포지토리 이름레포지토리 이름레포지토리 이름",
                "레포지토리 설명레포지토리 설명레포지토리 설명레포지토리 설명레포지토리 설명",
                "레포지토리 언어"
            )
        )
    }
}