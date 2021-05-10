package org.sopt.androidseminar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.Response
import org.sopt.androidseminar.util.LifecycleObserver
import org.sopt.androidseminar.R
import org.sopt.androidseminar.adapters.RepositoryAdapter
import org.sopt.androidseminar.api.github.repository.RepoServiceCreator
import org.sopt.androidseminar.data.RepositoryInfo
import org.sopt.androidseminar.data.response.ResponseRepoData
import org.sopt.androidseminar.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setRepoRv()
        moreButtonClickedEvent()
        LifecycleObserver(javaClass.simpleName, this.lifecycle).registerLogger()
        getRepos()
    }

    private fun getRepos() {
        //val requestRepoData = ResponseRepoData
        val call: Call<List<ResponseRepoData>> = RepoServiceCreator.repoService.getRepos()
        call.enqueue(object : Callback<List<ResponseRepoData>> {
            override fun onResponse(
                call: Call<List<ResponseRepoData>>,
                response: retrofit2.Response<List<ResponseRepoData>>
            ) {
                if (response.isSuccessful) {
                    val responsedData = response.body()

                } else {
                    Log.e("error", "getRepos() error")
                }
            }

            override fun onFailure(call: Call<List<ResponseRepoData>>, t: Throwable) {
                Log.e("onFailure", t.toString())
            }
        })
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
            repoList.add(
                RepositoryInfo(
                    "레포지토리 이름".plus(it),
                    "레포지토리 설명".plus(it),
                    "레포지토리 언어".plus(it)
                )
            )
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