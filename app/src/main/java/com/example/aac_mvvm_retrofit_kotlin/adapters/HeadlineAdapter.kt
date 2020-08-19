package com.example.aac_mvvm_retrofit_kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_mvvm_retrofit_kotlin.R
import com.example.aac_mvvm_retrofit_kotlin.model.Headline
import com.google.android.material.textview.MaterialTextView

class HeadlineAdapter(val headLines: List<Headline>) :
    RecyclerView.Adapter<HeadlineAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.each_row_headlines, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return headLines.size;
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = headLines[position]
        holder.tvHeadlineTitle.text = item.title
        holder.tvHeadlineDescription.text = item.description

    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val tvHeadlineTitle: MaterialTextView = itemView!!.findViewById(R.id.tvHeadlineTitle)
        val tvHeadlineDescription: MaterialTextView = itemView!!.findViewById(R.id.tvHeadlineDescription)
    }
}