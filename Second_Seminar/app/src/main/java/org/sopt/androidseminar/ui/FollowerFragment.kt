package org.sopt.androidseminar.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import org.sopt.androidseminar.R
import org.sopt.androidseminar.adapters.FollowerAdapter
import org.sopt.androidseminar.data.FollowerInfo
import org.sopt.androidseminar.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {

    private lateinit var binding: FragmentFollowerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_follower, container, false)

        setFollowerRv()

        return binding.root
    }

    private fun setFollowerRv() {
        val followerList = mutableListOf<FollowerInfo>()
        followerDataInput(followerList)

        val followerAdapter = FollowerAdapter(followerList)
        val followerRecyclerView = binding.rvFollower
        followerRecyclerView.adapter = followerAdapter
        followerRecyclerView.setHasFixedSize(false)
        followerAdapter.notifyDataSetChanged()
    }

    private fun followerDataInput(followerList: MutableList<FollowerInfo>) {
        followerList.add(FollowerInfo("김서진", "SeojinSeojin", FollowerInfo.NORMAL_CONTENT))
        followerList.add(FollowerInfo("김지연", "todayiswindy", FollowerInfo.NORMAL_CONTENT))
        followerList.add(FollowerInfo("이현우", "l2hyunwoo", FollowerInfo.NORMAL_CONTENT))
        followerList.add(FollowerInfo("오바마", "obama", FollowerInfo.NORMAL_CONTENT))
        for (i in 0..10) {
            if(i%4==0){
                followerList.add(FollowerInfo("광고", "advertisement", FollowerInfo.AD_CONTENT))
            }else{
                followerList.add(FollowerInfo("트럼프".plus(i), "trump", FollowerInfo.NORMAL_CONTENT))
            }
        }
    }
}