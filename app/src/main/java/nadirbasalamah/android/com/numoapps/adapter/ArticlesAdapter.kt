package nadirbasalamah.android.com.numoapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.article_item.view.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Article

class ArticlesAdapter() : RecyclerView.Adapter<ArticlesAdapter.CardViewViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private val listArticles  = ArrayList<Article>()

    fun setData(items: ArrayList<Article>) {
        listArticles.clear()
        listArticles.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): CardViewViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.article_item, viewGroup, false)
        return CardViewViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listArticles[position])
    }

    override fun getItemCount(): Int = listArticles.size

    inner class CardViewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(article: Article) {
            with(itemView) {
                tv_article_title.text = article.title
                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(article) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Article)
    }
}