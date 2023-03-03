package com.maverick.conduit.ui.feed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maverick.api.models.entities.Article
import com.maverick.conduit.R
import com.maverick.conduit.databinding.ListItemArticleBinding
import com.maverick.conduit.extension.loadImage
import com.maverick.conduit.extension.timestamp

class ArticleFeedAdapter(val onArticleClicked: (slug: String) -> Unit) :
    ListAdapter<Article, ArticleFeedAdapter.ArticleViewHolder>(
        object : DiffUtil.ItemCallback<Article>() {
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.toString() == newItem.toString()
            }

        }
    ) {

//    interface OnArticleClickedListener{
//        fun onArticleClicked()
//    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding =
            ListItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ArticleViewHolder(
            parent.context.getSystemService(LayoutInflater::class.java).inflate(
                R.layout.list_item_article,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        ListItemArticleBinding.bind(holder.itemView).apply {
            val article = getItem(position)

            authorTextView.text = article.author.username
//            dateTextView.showFormattedDate(article.createdAt)
            dateTextView.timestamp = article.createdAt
            titleTextView.text = article.title
            bodySnippetTextView.text = article.body
//            avatarImageView.background = ColorDrawable(Color.CYAN)
            avatarImageView.loadImage(article.author.image, true)
            //using lamda to pass onclick. take function as an argument
            root.setOnClickListener {
                onArticleClicked(article.slug)
            }
        }
    }
}