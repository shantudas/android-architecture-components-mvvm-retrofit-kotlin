package com.example.aac_mvvm_retrofit_kotlin.ui.headlines

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_mvvm_retrofit_kotlin.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadlineFragment : Fragment() {

    private val TAG: String = "AppDebug"
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HeadlineAdapter
    private val headlineViewModel: HeadlineViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_headlines, container, false)
        recyclerView = root.findViewById<RecyclerView>(R.id.recyclerViewHeadlines)
        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupObservers() {
        headlineViewModel.articles.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) adapter.setItems(ArrayList(it))
            for (item in it) {
                Log.i(TAG, "setupObservers: ${item.title}")
            }
        })
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = HeadlineAdapter(context)
        recyclerView.adapter = adapter
    }
}
