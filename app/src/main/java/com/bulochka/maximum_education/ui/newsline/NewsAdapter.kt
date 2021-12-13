package com.bulochka.maximum_education.ui.newsline

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.bulochka.maximum_education.data.model.News
import com.bulochka.maximum_education.databinding.ItemNewsBinding

class NewsAdapter: ListAdapter<News, NewsAdapter.NewsViewHolder>(NewsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = getItem(position)
        holder.onBind(news)

        holder.binding.title.setOnClickListener {
            onTitleClickListener?.let {
                it(news)
            }
        }

        holder.binding.image.setOnClickListener {
            onImageClickListener?.let {
                it(news.image)
            }
        }
    }

    inner class NewsViewHolder(var binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(news: News) {
            binding.title.text = news.title
            binding.image.load(news.image) {
                crossfade(true)
            }
        }
    }

    class NewsComparator: DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }

    private var onTitleClickListener: ((News) -> Unit)? = null
    fun setOnTitleClickListener(listener: (News) -> Unit) {
        onTitleClickListener = listener
    }

    private var onImageClickListener: ((String) -> Unit)? = null
    fun setOnImageClickListener(listener: (String) -> Unit) {
        onImageClickListener = listener
    }
}

class SpacingItemDecoration:
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val spacing = 24
        val position = parent.getChildAdapterPosition(view) // item position
        outRect.left = spacing
        outRect.right = spacing
        if (position == 0)
        // top edge
            outRect.top = spacing
        // item bottom
        outRect.bottom = spacing
    }
}