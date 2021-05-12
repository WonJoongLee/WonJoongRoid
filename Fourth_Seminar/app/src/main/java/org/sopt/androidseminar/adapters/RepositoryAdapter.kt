package org.sopt.androidseminar.adapters

import android.graphics.Typeface
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidseminar.R
import org.sopt.androidseminar.data.RepositoryInfo
import org.sopt.androidseminar.databinding.ItemRepositoryBinding

class RepositoryAdapter :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    private var repoList = mutableListOf<RepositoryInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemRepositoryBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_repository, parent, false)
        return RepositoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(repoList[position])
    }

    override fun getItemCount() = repoList.size

    fun setItemList(newList: List<RepositoryInfo>) {
        repoList.clear()
        repoList.addAll(newList)
        notifyDataSetChanged()
    }

    class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repositoryInfo: RepositoryInfo) {
            binding.apply {
                //repo = repositoryInfo
                tvRepositoryName.text = repositoryInfo.repoName
                tvRepositoryLanguage.text = repositoryInfo.repoLang
                // 만약 레포지토리 설명이 없으면 Github처럼 No Description을 italic체로 보여준다.
                if (repositoryInfo.repoDescription.isNullOrEmpty()) {
                    val noDescriptionStr = SpannableStringBuilder()
                    noDescriptionStr.append("No Description")
                    noDescriptionStr.setSpan(
                        StyleSpan(Typeface.ITALIC),
                        0,
                        noDescriptionStr.toString().length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                    binding.tvRepositoryDetail.text = noDescriptionStr
                } else {
                    tvRepositoryDetail.text = repositoryInfo.repoDescription
                }
            }
        }
    }
}