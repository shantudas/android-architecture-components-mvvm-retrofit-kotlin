package com.example.aac_mvvm_retrofit_kotlin.presentation.headlines

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HeadlineFragment : Fragment() {

    private val TAG: String = "HeadlineFragment"
    private lateinit var headlineAdapter: HeadlineAdapter
    private lateinit var binding: FragmentHeadlinesBinding
    private lateinit var categoryAdapter: HeadlineCategoryAdapter
    private val headlineViewModel: HeadlineViewModel by viewModels()
    private var articleArrayList = arrayListOf<Article>()


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

        collectHeadlineResponse()

        binding.swipeRefreshHeadline.setOnRefreshListener {
            clearHeadline()
            hideHeadlineList()
            showSwipeRefresh()
            headlineViewModel.refreshArticles()
        }

    }

    private fun collectHeadlineResponse() {
        lifecycleScope.launchWhenStarted {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                headlineViewModel.articleResource.collect { response ->
                    when (response) {
                        is Resource.Loading -> {
                            Log.d(TAG, "articleResponse: is loading")
                            showHeadlineLoader()
                        }

                        is Resource.Success -> {
                            Log.d(
                                TAG,
                                "articleResponse: size" + response.data?.size + " \n data " + response.data.toString()
                            )
                            hideHeadlineLoader()
                            hideSwipeRefresh()
                            showHeadlineList()
                            response.data?.let { data ->
                                articleArrayList = data as ArrayList<Article>
                                headlineAdapter.setItems(articleArrayList)
                            }
                        }

                        is Resource.Error -> {
                            Log.d(TAG, "articleResponse: error")
                        }

                        else -> {

                        }
                    }
                }
            }
        }
    }

    private fun clearHeadline() {
        Log.d(TAG, "clearHeadline: called")
        articleArrayList.clear()
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
        headlineAdapter = HeadlineAdapter(context)
        binding.recyclerViewHeadlines.adapter = headlineAdapter
    }

}
