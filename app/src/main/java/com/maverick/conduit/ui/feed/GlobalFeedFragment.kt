package com.maverick.conduit.ui.feed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.maverick.conduit.databinding.FragmentFeedBinding

class GlobalFeedFragment : Fragment() {
    private var binding: FragmentFeedBinding? = null
    private lateinit var viewModel: FeedViewModel
    private lateinit var adapter: ArticleFeedAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[FeedViewModel::class.java]
        adapter = ArticleFeedAdapter()

        binding = FragmentFeedBinding.inflate(inflater, container, false)
        binding?.feedRecyclerView?.layoutManager = LinearLayoutManager(context)
        binding?.feedRecyclerView?.adapter = adapter
        viewModel.fetchGlobalFeed()
        viewModel.feed.observe({ lifecycle }){
            adapter.submitList(it)
        }

        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null //used to save from memory leak.
    }
}