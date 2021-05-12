package org.sopt.androidseminar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import org.sopt.androidseminar.util.LifecycleObserver
import org.sopt.androidseminar.adapters.RepositoryAdapter
import org.sopt.androidseminar.api.github.repository.RepoServiceCreator
import org.sopt.androidseminar.data.RepositoryInfo
import org.sopt.androidseminar.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRepos()
        moreButtonClickEvent()
        LifecycleObserver(javaClass.simpleName, this.lifecycle).registerLogger()
    }

    private fun getRepos() {
        val call: Call<List<RepositoryInfo>> = RepoServiceCreator.repoService.getRepos()
        call.enqueue(object : Callback<List<RepositoryInfo>> {
            override fun onResponse(
                call: Call<List<RepositoryInfo>>,
                response: retrofit2.Response<List<RepositoryInfo>>
            ) {
                if (response.isSuccessful) {
                    if (response.body() != null) {
                        setRepoRv(response.body()!!)
                    }
                } else {
                    Log.e("error", "getRepos() error")
                }
            }

            override fun onFailure(call: Call<List<RepositoryInfo>>, t: Throwable) {
                Log.e("onFailure", t.toString())
            }
        })
    }

    private fun setRepoRv(repoList: List<RepositoryInfo>) {
        val repoAdapter = RepositoryAdapter()
        val repoRecyclerView = binding.rvRepository
        repoAdapter.setItemList(repoList)
        repoRecyclerView.smoothScrollToPosition(0)
        repoAdapter.notifyDataSetChanged()
        repoRecyclerView.adapter = repoAdapter
        repoRecyclerView.setHasFixedSize(false)
        binding.progressbarRepoRv.visibility = View.GONE
    }

    private fun moreButtonClickEvent() {
        binding.btMore.setOnClickListener {
            val intent = Intent(this, FragmentActivity::class.java)
            startActivity(intent)
        }
    }
}