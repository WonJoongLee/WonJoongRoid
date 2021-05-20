package org.sopt.androidseminar.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.sopt.androidseminar.R
import org.sopt.androidseminar.data.FollowerInfo
import org.sopt.androidseminar.databinding.ItemAdvertisementBinding
import org.sopt.androidseminar.databinding.ItemFollowerBinding

class FollowerAdapter :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var followerList = mutableListOf<FollowerInfo>()

    override fun getItemViewType(position: Int): Int {
        return when (followerList[position].viewType) {
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
        val obj = followerList[position]
        when (obj.viewType) {
            FollowerInfo.NORMAL_CONTENT -> {
                (holder as FollowerViewHolder).bind(obj)
            }
            FollowerInfo.AD_CONTENT -> {
                (holder as AdViewHolder).bind()
            }
        }
    }

    override fun getItemCount() = followerList.size

    fun setItemList(newList: List<FollowerInfo>) {
        followerList.clear()
        followerList.addAll(newList)
        notifyDataSetChanged()
    }

    class FollowerViewHolder(private val binding: ItemFollowerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(followerInfo: FollowerInfo) {
            binding.follower = followerInfo
            Glide
                .with(binding.ivFollowerPicture.context)
                .load(followerInfo.followerImgUrl)
                .centerCrop()
                .into(binding.ivFollowerPicture)
            binding.clFollowerItemParentView.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(followerInfo.followerHtmlUrl))
                binding.clFollowerItemParentView.context.startActivity(intent)
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