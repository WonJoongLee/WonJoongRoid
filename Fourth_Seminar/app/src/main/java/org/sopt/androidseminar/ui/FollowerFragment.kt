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
        followerDataInput()
        return binding.root
    }

    private fun setFollowerRv(followerList: List<FollowerInfo>) {
        val followerAdapter = FollowerAdapter()
        val followerRecyclerView = binding.rvFollower
        followerRecyclerView.adapter = followerAdapter
        followerRecyclerView.setHasFixedSize(false)
        followerAdapter.setItemList(followerList)
    }

    private fun followerDataInput() {
        val call: Call<List<FollowerInfo>> = ServiceCreator.githubService.getFollowers()
        call.enqueue(object: Callback<List<FollowerInfo>>{
            override fun onResponse(
                call: Call<List<FollowerInfo>>,
                response: Response<List<FollowerInfo>>
            ) {
                if(response.isSuccessful){
                    response.body()?.let {
                        setFollowerRv(it)
                    }
                }else{
                    Log.e("error", "follower data input error")
                }
            }

            override fun onFailure(call: Call<List<FollowerInfo>>, t: Throwable) {
                Log.e("GetFollower", t.toString())
            }
        })
//        followerList.add(FollowerInfo("김서진", "SeojinSeojin", FollowerInfo.NORMAL_CONTENT))
//        followerList.add(FollowerInfo("김지연", "todayiswindy", FollowerInfo.NORMAL_CONTENT))
//        followerList.add(FollowerInfo("이현우", "l2hyunwoo", FollowerInfo.NORMAL_CONTENT))
//        followerList.add(FollowerInfo("오바마", "obama", FollowerInfo.NORMAL_CONTENT))
//        (0..10).forEach {
//            if (it % 4 == 0) {
//                followerList.add(FollowerInfo("광고", "advertisement", FollowerInfo.AD_CONTENT))
//            } else {
//                followerList.add(FollowerInfo("트럼프".plus(it), "trump", FollowerInfo.NORMAL_CONTENT))
//            }
//        }
    }
}