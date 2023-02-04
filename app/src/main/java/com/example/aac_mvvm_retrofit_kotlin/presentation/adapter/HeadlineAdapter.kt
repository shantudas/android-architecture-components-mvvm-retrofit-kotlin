package com.example.aac_mvvm_retrofit_kotlin.presentation.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.aac_mvvm_retrofit_kotlin.R
import com.example.aac_mvvm_retrofit_kotlin.databinding.EachRowHeadlinesBinding
import com.example.aac_mvvm_retrofit_kotlin.databinding.FragmentHeadlinesBinding
import com.example.aac_mvvm_retrofit_kotlin.domain.model.Article
import com.example.aac_mvvm_retrofit_kotlin.util.CellClickListener
import com.google.android.material.textview.MaterialTextView

class HeadlineAdapter(
    private val context: Context?,
    private var articleList: ArrayList<Article>,
    private val cellClickListener: CellClickListener
    ) : RecyclerView.Adapter<HeadlineAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding= EachRowHeadlinesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return articleList.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = articleList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener{
            cellClickListener.onCellClickListener(holder.absoluteAdapterPosition)
        }
    }

    fun setItems(arrayList: ArrayList<Article>) {
        articleList.clear()
        articleList.addAll(arrayList)
        notifyDataSetChanged()
    }

    inner class MyViewHolder(private val binding: EachRowHeadlinesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.tvHeadlineTitle.text= "$absoluteAdapterPosition ${item.title}"
            Glide.with(context!!)
                .load(item.thumbnail)
                .transform(CenterCrop(),RoundedCorners(15))
                .into(binding.imgViewThumbnail)
        }
    }
}