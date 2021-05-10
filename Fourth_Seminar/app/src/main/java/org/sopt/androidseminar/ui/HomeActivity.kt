package org.sopt.androidseminar.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.sopt.androidseminar.util.LifecycleObserver
import org.sopt.androidseminar.adapters.RepositoryAdapter
import org.sopt.androidseminar.api.github.repository.RepoServiceCreator
import org.sopt.androidseminar.data.RepositoryInfo
import org.sopt.androidseminar.data.response.ResponseRepoData
import org.sopt.androidseminar.databinding.ActivityHomeBinding
import retrofit2.Call
import retrofit2.Callback

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val repoList = mutableListOf<RepositoryInfo>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getRepos()
        moreButtonClickedEvent()
        LifecycleObserver(javaClass.simpleName, this.lifecycle).registerLogger()
    }

    private fun getRepos() {
        val call: Call<List<ResponseRepoData>> = RepoServiceCreator.repoService.getRepos()
        call.enqueue(object : Callback<List<ResponseRepoData>> {
            override fun onResponse(
                call: Call<List<ResponseRepoData>>,
                response: retrofit2.Response<List<ResponseRepoData>>
            ) {
                if (response.isSuccessful) {
                    val responsedData = response.body()
                    val dataSize = responsedData?.size ?: 0
                    //val noDescString : Spannable = "No Description".toSpannable()
                    //noDescString.setSpan(StyleSpan(Typeface.ITALIC), 0, noDescString.toString().length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    for(i in 0 until dataSize){
                        repoList.add(RepositoryInfo(responsedData?.get(i)?.repoName?:"", responsedData?.get(i)?.repoDescription?:"No Description", responsedData?.get(i)?.repoLang?:""))
                        Log.i("RepoName", responsedData?.get(i)?.repoName ?: "Empty Name")
                    }
                    setRepoRv()
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
}