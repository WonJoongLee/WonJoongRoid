package org.sopt.androidseminar.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.sopt.androidseminar.R
import org.sopt.androidseminar.data.RepositoryInfo

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    var data = listOf<RepositoryInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repository, parent, false)
        return RepositoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size

    class RepositoryViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val repoName = itemView.findViewById<TextView>(R.id.tv_repository_name)
        private val repoInfo = itemView.findViewById<TextView>(R.id.tv_repository_detail)
        private val repoLang = itemView.findViewById<TextView>(R.id.tv_repository_language)
        fun bind(repositoryInfo: RepositoryInfo){
            repoName.text = repositoryInfo.repoName
            repoInfo.text = repositoryInfo.repoInfo
            repoLang.text = repositoryInfo.repoLang
        }
    }
}