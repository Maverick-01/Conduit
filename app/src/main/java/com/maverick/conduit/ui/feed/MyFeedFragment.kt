package com.maverick.conduit.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.maverick.conduit.R
import com.maverick.conduit.databinding.FragmentFeedBinding

class MyFeedFragment : Fragment() {
    private var binding: FragmentFeedBinding? = null
    private lateinit var viewModel: FeedViewModel
    private lateinit var adapter: ArticleFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        adapter = ArticleFeedAdapter { openArticle(it) }

        binding = FragmentFeedBinding.inflate(inflater, container, false)
        binding?.feedRecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.feedRecyclerView?.adapter = adapter
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchMyFeed()
        viewModel.feed.observe({ lifecycle }) {
            adapter.submitList(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null //used to save from memory leak.
    }

    private fun openArticle(articleId: String) {
        findNavController().navigate(
            R.id.action_my_feed_open_article,
            bundleOf(
                resources.getString(R.string.arg_article_id) to articleId
            )
        )
    }
}