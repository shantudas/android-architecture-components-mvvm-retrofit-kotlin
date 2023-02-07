package com.example.aac_mvvm_retrofit_kotlin.presentation.headlines

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aac_mvvm_retrofit_kotlin.databinding.FragmentHeadlinesBinding
import com.example.aac_mvvm_retrofit_kotlin.domain.model.Article
import com.example.aac_mvvm_retrofit_kotlin.util.Resource
import com.example.aac_mvvm_retrofit_kotlin.presentation.adapter.HeadlineAdapter
import com.example.aac_mvvm_retrofit_kotlin.presentation.adapter.HeadlineCategoryAdapter
import com.example.aac_mvvm_retrofit_kotlin.util.CellClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class HeadlineFragment : Fragment(), CellClickListener {

    private val TAG: String = "HeadlineFragment"
    private lateinit var binding: FragmentHeadlinesBinding
    private lateinit var headlineAdapter: HeadlineAdapter
    private lateinit var categoryAdapter: HeadlineCategoryAdapter
    private val headlineViewModel: HeadlineViewModel by viewModels()
    private var articleList: ArrayList<Article> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHeadlinesBinding.inflate(layoutInflater)
        val view = binding.root
        return view.rootView
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init();

        collectHeadlineResponse()

        binding.swipeRefreshHeadline.setOnRefreshListener {
            clearHeadline()
            hideHeadlineList()
            showSwipeRefresh()
            headlineViewModel.refreshArticles()
        }

    }

    private fun init() {
        setupCategoryRecyclerView()
        setupHeadlineRecyclerView()
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
        headlineAdapter = HeadlineAdapter(context, articleList, this)
        binding.recyclerViewHeadlines.adapter = headlineAdapter
    }

    private fun clearHeadline() {
        Log.d(TAG, "clearHeadline: called")
        articleList.clear()
        headlineAdapter.notifyDataSetChanged()
    }

    private fun showHeadlineList() {
        binding.recyclerViewHeadlines.visibility = View.VISIBLE
    }

    private fun hideHeadlineList() {
        binding.recyclerViewHeadlines.visibility = View.GONE
    }


    private fun showHeadlineLoader() {
        binding.shimmerHeadline.visibility = View.VISIBLE
        binding.shimmerHeadline.startShimmer()
    }

    private fun hideHeadlineLoader() {
        binding.shimmerHeadline.stopShimmer()
        binding.shimmerHeadline.visibility = View.GONE
    }

    private fun showSwipeRefresh() {
        if (!binding.swipeRefreshHeadline.isRefreshing) {
            binding.swipeRefreshHeadline.isRefreshing = true
        }
    }

    private fun hideSwipeRefresh() {
        binding.swipeRefreshHeadline.isRefreshing = false
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun collectHeadlineResponse() {
        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                headlineViewModel.uiState.collect { uiState ->
                    when (uiState) {
                        is HeadlineUiState.Loading -> {
                            Log.d(TAG, "articleResponse: is loading")
                            showHeadlineLoader()
                        }

                        is HeadlineUiState.Success -> {
                            hideHeadlineLoader()
                            hideSwipeRefresh()
                            showHeadlineList()

                            articleList= uiState.articles as ArrayList<Article>
                            headlineAdapter.setItems(articleList)
                        }

                        is HeadlineUiState.Error -> {
                            Log.d(TAG, "articleResponse: error")
                        }

                        else -> {
                            Unit
                        }
                    }
                }
            }
        }
    }

    override fun onCellClickListener(position: Int) {
        Log.d(TAG, "onCellClickListener: " + articleList[position].toString())
    }


}
