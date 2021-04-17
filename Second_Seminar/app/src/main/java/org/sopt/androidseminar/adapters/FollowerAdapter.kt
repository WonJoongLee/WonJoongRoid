package org.sopt.androidseminar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidseminar.R
import org.sopt.androidseminar.data.FollowerInfo
import org.sopt.androidseminar.databinding.ItemFollowerBinding

class FollowerAdapter(private val data: List<FollowerInfo>) :
    RecyclerView.Adapter<FollowerAdapter.FollowerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemFollowerBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_follower, parent, false)
        return FollowerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(followerInfo: FollowerInfo) {
            binding.apply {
                name = followerInfo.followerName
                githubNickName = followerInfo.followerGithubId
            }
        }
    }
}