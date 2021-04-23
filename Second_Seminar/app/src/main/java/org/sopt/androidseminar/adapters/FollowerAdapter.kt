package org.sopt.androidseminar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidseminar.R
import org.sopt.androidseminar.data.FollowerInfo
import org.sopt.androidseminar.databinding.ItemAdvertisementBinding
import org.sopt.androidseminar.databinding.ItemFollowerBinding

class FollowerAdapter(private val data: List<FollowerInfo>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when(data[position].viewType){
            FollowerInfo.NORMAL_CONTENT -> FollowerInfo.NORMAL_CONTENT
            FollowerInfo.AD_CONTENT -> FollowerInfo.AD_CONTENT
            else -> throw RuntimeException("View Type Error at getItemViewType")
        }
    }

    // getItemViewType의 리턴값 Int가 viewType으로 넘어온다.
    // viewType으로 넘어오는 값에 따라 viewHolder를 알맞게 처리해주면 된다.
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            FollowerInfo.NORMAL_CONTENT -> {
                val binding: ItemFollowerBinding =
                    DataBindingUtil.inflate(layoutInflater, R.layout.item_follower, parent, false)
                FollowerViewHolder(binding)
            }
            FollowerInfo.AD_CONTENT -> {
                val binding: ItemAdvertisementBinding = DataBindingUtil.inflate(
                    layoutInflater,
                    R.layout.item_advertisement,
                    parent,
                    false
                )
                AdViewHolder(binding)
            }
            else -> throw RuntimeException("View Type Error at onCreateViewHolder")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val obj = data[position]
        when (obj.viewType) {
            FollowerInfo.NORMAL_CONTENT -> {
                (holder as FollowerViewHolder).bind(obj)
            }
            FollowerInfo.AD_CONTENT -> {
                (holder as AdViewHolder).bind()
            }
        }
    }

    override fun getItemCount(): Int = data.size

    class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(followerInfo: FollowerInfo) {
            binding.apply {
                follower = followerInfo
            }
        }
    }

    class AdViewHolder(private val binding: ItemAdvertisementBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind() {
            binding.apply {
                adContent = "광고!"
            }
        }
    }
}