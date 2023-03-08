package com.maverick.conduit.ui.article

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.maverick.conduit.databinding.FragmentCreateArticleBinding

class CreateArticleFragment:Fragment() {

    private var binding:FragmentCreateArticleBinding?=null
    private lateinit var viewModel: ArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateArticleBinding.inflate(layoutInflater,container,false)
        viewModel = ViewModelProvider(this)[ArticleViewModel::class.java]
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            submitButton.setOnClickListener{
                viewModel.createArticle(
                    title = articleTitleTv.text.toString().takeIf { it.isNotBlank() },
                    description = articleDesciptionTv.text.toString().takeIf { it.isNotBlank() },
                    body = articleBodyTv.text.toString().takeIf { it.isNotBlank() },
                    tagList = articleTagTv.text.toString().split("//s".toRegex())
                )
                Toast.makeText(requireContext(),"Article Published",Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}