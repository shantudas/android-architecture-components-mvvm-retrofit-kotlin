package com.example.aac_mvvm_retrofit_kotlin.ui.headlines

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aac_mvvm_retrofit_kotlin.R
import com.example.aac_mvvm_retrofit_kotlin.databinding.FragmentHeadlinesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadlineFragment : Fragment() {

    private val TAG: String = "AppDebug"
    private lateinit var adapter: HeadlineAdapter
    private lateinit var binding: FragmentHeadlinesBinding
    private lateinit var categoryAdapter: HeadlineCategoryAdapter
    private val headlineViewModel: HeadlineViewModel by viewModels()


     override fun onCreateView(
         inflater: LayoutInflater,
         container: ViewGroup?,
         savedInstanceState: Bundle?
     ): View? {
 //        val root = inflater.inflate(R.layout.fragment_headlines, container, false)
         binding = FragmentHeadlinesBinding.inflate(layoutInflater)
         val view = binding.root
         return view.rootView
     }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init();

        setupObservers()
    }

    private fun init() {
        setupRecyclerViewCategory()
        setupRecyclerViewHeadline()
    }

    private fun setupObservers() {
        headlineViewModel.articles.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) adapter.setItems(ArrayList(it))
            for (item in it) {
                Log.i(TAG, "setupObservers: ${item.title}")
            }
        })
    }

    private fun setupRecyclerViewCategory() {
        val headlineCategories = getAllHeadlineCategories()

        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerViewHeadlineCategory.layoutManager = mLayoutManager
        categoryAdapter = HeadlineCategoryAdapter(context, headlineCategories)
        binding.recyclerViewHeadlineCategory.adapter = categoryAdapter
    }

    private fun setupRecyclerViewHeadline() {
        binding.recyclerViewHeadlines.layoutManager = LinearLayoutManager(context)
        adapter = HeadlineAdapter(context)
        binding.recyclerViewHeadlines.adapter = adapter
    }
}
