package org.sopt.androidseminar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidseminar.R
import org.sopt.androidseminar.data.FollowerInfo
import org.sopt.androidseminar.databinding.ItemAdvertisementBinding
import org.sopt.androidseminar.databinding.ItemFollowerBinding
import java.lang.RuntimeException

class FollowerAdapter(private val data: List<FollowerInfo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //lateinit var binding: ItemFollowerBinding
        return when(viewType){
            FollowerInfo.NORMAL_CONTENT -> {
                val binding : ItemFollowerBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_follower, parent, false)
                FollowerViewHolder(binding)
            }
            FollowerInfo.AD_CONTENT -> {
                val binding : ItemAdvertisementBinding = DataBindingUtil.inflate(layoutInflater, R.layout.item_advertisement, parent, false)
                AdViewHolder(binding)
            }
            else -> throw RuntimeException("View Type Error")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //holder.bind(data[position])
        if(holder is FollowerViewHolder){
            holder.bind(data[position])
        }else if(holder is AdViewHolder){
            holder.bind()
        }
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

    class AdViewHolder(private val binding: ItemAdvertisementBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(){
           binding.apply {
               adContent = "광고!"
           }
        }
    }
}