package com.example.aac_mvvm_retrofit_kotlin.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_mvvm_retrofit_kotlin.R
import com.example.aac_mvvm_retrofit_kotlin.presentation.headlines.HeadlineCategory

class HeadlineCategoryAdapter(
    val context: Context?,
    private val headlineCategories: List<HeadlineCategory>
) : RecyclerView.Adapter<HeadlineCategoryAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.each_row_headline_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = headlineCategories[position]
        holder.tvCategory.text = item.name
    }

    override fun getItemCount(): Int {
        return headlineCategories.size
    }

    class ViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {
        val tvCategory: TextView = itemView!!.findViewById(R.id.tvCategory)
    }
}