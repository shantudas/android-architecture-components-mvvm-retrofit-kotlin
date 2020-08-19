package com.example.aac_mvvm_retrofit_kotlin.ui.headlines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_mvvm_retrofit_kotlin.R
import com.example.aac_mvvm_retrofit_kotlin.adapters.HeadlineAdapter
import com.example.aac_mvvm_retrofit_kotlin.model.Headline
import kotlinx.android.synthetic.main.fragment_headlines.*

class HeadlineFragment : Fragment() {

    private lateinit var dashboardViewModel: HeadlineViewModel
    private lateinit var adapter: HeadlineAdapter
    val headlinesArrayList = listOf(
        Headline(
            "Raising Arizona",
            "Doug Stanglin, Joel Shannon",
            "Coronavirus updates: House OKs remote voting, \$3T relief package opposed by Senate; Trump wants vaccine by year's end - USA TODAY",
            "https://www.gannett-cdn.com/-mm-/1a2df851cdf614a1c9b81d1826b3c9fc5c22bda4/c=0-285-5472-3363/local/-/media/2020/05/15/USATODAY/usatsports/ghows-TX-200519363-8ed194b1.jpg?width=3200&height=1680&fit=crop",
            ""
        ),
        Headline(
            "Shantu das",
            "JCPenney department store files for bankruptcy - Sky News Australia",
            "JCPenney has become the latest retail casualty of the coronavirus crisis. The iconic United States department store chain has filed for bankruptcy and will c...",
            "https://i.ytimg.com/vi/m-HGjazxQQw/maxresdefault.jpg",
            ""
        )
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel = ViewModelProviders.of(this).get(HeadlineViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_headlines, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewHeadlines)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HeadlineAdapter(headlinesArrayList)
        recyclerView.adapter = adapter

        return root
    }
}
