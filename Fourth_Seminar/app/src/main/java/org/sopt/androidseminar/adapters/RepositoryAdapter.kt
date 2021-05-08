package org.sopt.androidseminar.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidseminar.R
import org.sopt.androidseminar.data.RepositoryInfo
import org.sopt.androidseminar.databinding.ItemRepositoryBinding

class RepositoryAdapter() :
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

    fun setItemList(newList:MutableList<RepositoryInfo>){
        repoList.clear()
        repoList.addAll(newList)
        notifyDataSetChanged()
    }

    class RepositoryViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repositoryInfo: RepositoryInfo) {
            binding.apply {
                repo = repositoryInfo
            }
        }
    }
}