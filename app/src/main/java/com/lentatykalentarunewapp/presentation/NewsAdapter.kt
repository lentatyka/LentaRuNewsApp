package com.lentatykalentarunewapp.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lentatykalentarunewapp.databinding.ItemNewsBinding
import com.lentatykalentarunewapp.domain.model.Article

class NewsAdapter(private val callback: (String?)-> Unit):ListAdapter<Article, NewsAdapter.ViewHolder>(DiffCallback) {

    companion object{
        private val DiffCallback = object: DiffUtil.ItemCallback<Article>(){
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(
        private val binding: ItemNewsBinding,
        ):RecyclerView.ViewHolder(binding.root){
            fun bind(item: Article){
                binding.article = item
                binding.root.setOnClickListener {
                    callback(item.url)
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}