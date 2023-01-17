package com.example.aac_mvvm_retrofit_kotlin.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.aac_mvvm_retrofit_kotlin.R
import com.example.aac_mvvm_retrofit_kotlin.model.Article
import com.google.android.material.textview.MaterialTextView

class HeadlineAdapter(val context: Context?) : RecyclerView.Adapter<HeadlineAdapter.MyViewHolder>() {

    private val articles = ArrayList<Article>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.each_row_headlines, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articles.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = articles[position]
        holder.tvHeadlineTitle.text = item.title
//        holder.tvHeadlineSource.text = item.
        Glide.with(context!!)
            .load(item.thumbnail)
            .transform(CenterCrop(),RoundedCorners(15))
            .into(holder.imgViewThumbnail)

    }

    fun setItems(arrayList: ArrayList<Article>) {
        articles.addAll(arrayList)
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val tvHeadlineTitle: MaterialTextView = itemView!!.findViewById(R.id.tvHeadlineTitle)
        val tvHeadlineSource: MaterialTextView =
            itemView!!.findViewById(R.id.tvHeadlineSource)
        val imgViewThumbnail: ImageView = itemView!!.findViewById(R.id.imgViewThumbnail)
    }
}