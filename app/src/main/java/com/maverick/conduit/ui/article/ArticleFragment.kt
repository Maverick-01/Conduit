package com.maverick.conduit.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.maverick.conduit.R
import com.maverick.conduit.databinding.FragmentArticleBinding
import com.maverick.conduit.extension.loadImage
import com.maverick.conduit.extension.timestamp

class ArticleFragment : Fragment() {

    private var binding: FragmentArticleBinding? = null
    lateinit var articleViewModel: ArticleViewModel
    private var articleId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        articleViewModel = ViewModelProvider(this)[ArticleViewModel::class.java]
        binding = FragmentArticleBinding.inflate(inflater, container, false)

        arguments?.let {
            articleId = it.getString(resources.getString(R.string.arg_article_id))
        }

        articleId?.let {
            articleViewModel.fetchArticle(it)
        }

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleViewModel.article.observe({ lifecycle }) {
            binding?.apply {
                titleTextView.text = it.title
                bodyTextView.text = it.body
                authorTextView.text = it.author.username
//                dateTextView.showFormattedDate(it.createdAt)
                dateTextView.timestamp = it.createdAt
                avatarImageView.loadImage(it.author.image, true)
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}