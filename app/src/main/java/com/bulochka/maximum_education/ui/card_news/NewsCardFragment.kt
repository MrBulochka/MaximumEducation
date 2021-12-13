package com.bulochka.maximum_education.ui.card_news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.bulochka.maximum_education.R
import com.bulochka.maximum_education.databinding.FragmentNewsCardBinding
import com.bulochka.maximum_education.data.model.News
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsCardFragment: Fragment() {

    private lateinit var _binding: FragmentNewsCardBinding
    private lateinit var news: News

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsCardBinding.inflate(inflater, container, false)

        showNews()
        setOnClickListeners()

        return _binding.root
    }

    private fun showNews() {
        val args: NewsCardFragmentArgs by navArgs()
        news = args.selectedNews
        _binding.apply {
            title.text = news.title
            description.text = news.description
            image.load(news.image) {
                crossfade(true)
            }
        }
    }

    private fun setOnClickListeners() {
        _binding.image.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("selected_image", news.image)
            findNavController().navigate(R.id.action_newsCard_to_image, bundle)
        }
    }
}