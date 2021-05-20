package org.sopt.androidseminar.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.sopt.androidseminar.R
import org.sopt.androidseminar.adapters.FollowerAdapter
import org.sopt.androidseminar.api.ServiceCreator
import org.sopt.androidseminar.data.FollowerInfo
import org.sopt.androidseminar.databinding.FragmentFollowerBinding
import org.sopt.androidseminar.util.RvItemDecoration
import org.sopt.androidseminar.util.RvItemDecoration.Companion.FOLLOWER_RV_TYPE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowerFragment : Fragment() {

    private lateinit var binding: FragmentFollowerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_follower, container, false)
        setFollower()
        return binding.root
    }

    private fun setFollowerRv(followerList: List<FollowerInfo>) {
        val followerAdapter = FollowerAdapter()
        val followerRecyclerView = binding.rvFollower
        followerRecyclerView.adapter = followerAdapter
        followerRecyclerView.setHasFixedSize(false)
        followerRecyclerView.addItemDecoration(RvItemDecoration(10, FOLLOWER_RV_TYPE))
        followerAdapter.setItemList(followerList)
    }

    private fun setFollower() {
        val call: Call<List<FollowerInfo>> = ServiceCreator.githubService.getFollowers()
        call.enqueue(object : Callback<List<FollowerInfo>> {
            override fun onResponse(
                call: Call<List<FollowerInfo>>,
                response: Response<List<FollowerInfo>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        setFollowerRv(it)
                    }
                } else {
                    Log.e("error", "follower data input error")
                }
            }

            override fun onFailure(call: Call<List<FollowerInfo>>, t: Throwable) {
                Log.e("GetFollower", t.toString())
            }
        })
    }
}