package com.lentatykalentarunewapp.presentation

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.lentatykalentarunewapp.databinding.ItemNewsBinding
import com.lentatykalentarunewapp.domain.model.Article

class Adapter:ListAdapter<Article, Adapter.ViewHolder>(DiffCallback) {

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

    class ViewHolder(
        private val binding: ItemNewsBinding
        ):RecyclerView.ViewHolder(binding.root){
            fun bind(item: Article){

            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }
}