package com.example.aac_mvvm_retrofit_kotlin.ui.headlines

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aac_mvvm_retrofit_kotlin.databinding.FragmentHeadlinesBinding
import com.example.aac_mvvm_retrofit_kotlin.state.NetworkState
import com.example.aac_mvvm_retrofit_kotlin.ui.adapter.HeadlineAdapter
import com.example.aac_mvvm_retrofit_kotlin.ui.adapter.HeadlineCategoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class HeadlineFragment : Fragment() {

    private val TAG: String = "HeadlineFragment"
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

        lifecycleScope.launchWhenStarted {
            headlineViewModel.articleResponse.collect { response ->
                when (response) {
                    is NetworkState.Loading -> {
                        Log.d(TAG, "articleResponse: is loading")
                    }

                    is NetworkState.Success -> {
                        Log.d(TAG, "articleResponse: " + response.data)
                    }

                    is NetworkState.Error -> {
                        Log.d(TAG, "articleResponse: error")
                    }

                    else -> {

                    }
                }
            }
        }
    }

    private fun init() {
        setupCategoryRecyclerView()
        setupHeadlineRecyclerView()
    }

    private fun setupObservers() {
//        headlineViewModel.articles.observe(viewLifecycleOwner, Observer {
//            if (!it.isNullOrEmpty()) adapter.setItems(ArrayList(it))
//            for (item in it) {
//                Log.i(TAG, "setupObservers: ${item.title}")
//            }
//        })
    }


    private fun setupCategoryRecyclerView() {
        val headlineCategories = getAllHeadlineCategories()

        val mLayoutManager = LinearLayoutManager(context)
        mLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.recyclerViewHeadlineCategory.layoutManager = mLayoutManager
        categoryAdapter = HeadlineCategoryAdapter(context, headlineCategories)
        binding.recyclerViewHeadlineCategory.adapter = categoryAdapter
    }

    private fun setupHeadlineRecyclerView() {
        binding.recyclerViewHeadlines.layoutManager = LinearLayoutManager(context)
        adapter = HeadlineAdapter(context)
        binding.recyclerViewHeadlines.adapter = adapter
    }

}
