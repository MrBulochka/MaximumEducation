package com.bulochka.maximum_education.ui.newsline

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bulochka.maximum_education.data.model.News
import com.bulochka.maximum_education.R
import com.bulochka.maximum_education.databinding.FragmentNewslineBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsLineFragment: Fragment() {

    private val newslineViewModel by viewModels<NewslineViewModel>()

    private lateinit var _binding: FragmentNewslineBinding

    @Inject lateinit var newsAdapter: NewsAdapter
    private val linearManager = LinearLayoutManager(context)
    private var pageNumber = 1
    private var newsList = ArrayList<News>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadNews()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewslineBinding.inflate(inflater, container, false)

        initRecycler()
        setUpObservers()
        setListeners()

        return _binding.root
    }

    private fun initRecycler() {
        _binding.newsRecycler.apply {
            adapter = newsAdapter
            layoutManager = linearManager
            addItemDecoration(SpacingItemDecoration())
            addOnScrollListener(
                ScrollListener({
                    loadNews()
                }, linearManager)
            )
        }
    }

    private fun setUpObservers() {
        newslineViewModel.error.observe(viewLifecycleOwner) { error ->
            _binding.swipeContainer.isRefreshing = false
            Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_SHORT).show()
        }

        newslineViewModel.news.observe(viewLifecycleOwner) { news ->
            if (pageNumber == 1) {
                newsList = news as ArrayList<News>
            } else { newsList.addAll(news) }
            newsAdapter.submitList(newsList)
            _binding.swipeContainer.isRefreshing = false
            Log.d("NewsLineFragment", "news size - ${newsList.size}, $pageNumber")
        }
    }

    private fun setListeners() {
        _binding.swipeContainer.setOnRefreshListener {
            pageNumber = 1
            loadNews()
        }
        newsAdapter.setOnTitleClickListener { news ->
            val bundle = Bundle()
            bundle.putSerializable("selected_news", news)
            findNavController().navigate(R.id.action_newsLine_to_newsCard, bundle)
        }
        newsAdapter.setOnImageClickListener { imageUrl ->
            val bundle = Bundle()
            bundle.putString("selected_image", imageUrl)
            findNavController().navigate(R.id.action_newsLine_to_image, bundle)
        }
    }

    private fun loadNews() {
        newslineViewModel.loadNewsWithException(pageNumber.toString())
        pageNumber += 1
    }
}