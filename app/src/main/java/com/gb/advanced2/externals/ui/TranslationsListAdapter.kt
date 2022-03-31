package com.gb.advanced2.externals.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gb.advanced2.databinding.TranslationsListItemBinding
import com.gb.advanced2.entities.Article
import com.gb.advanced2.entities.Articles

class Adapter : ListAdapter<Article, Adapter.ViewHolder>(DiffCallback) {
    fun setData(data: Articles) {
        super.submitList(data)
    }

    fun clearData() = setData(Articles())

    inner class ViewHolder(val vb: TranslationsListItemBinding) : RecyclerView.ViewHolder(vb.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        TranslationsListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = super.getItem(position)
        holder.vb.header.text = item.term
        holder.vb.description.text = item.desc
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(a: Article, b: Article) = a == b
        override fun areContentsTheSame(a: Article, b: Article) = a == b
    }
}

