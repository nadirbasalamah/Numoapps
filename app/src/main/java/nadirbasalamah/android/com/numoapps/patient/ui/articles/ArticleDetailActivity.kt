package nadirbasalamah.android.com.numoapps.patient.ui.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_article_detail.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Article
import nadirbasalamah.android.com.numoapps.viewmodel.ArticleViewModel

class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var article: Article
    private lateinit var articleViewModel: ArticleViewModel

    companion object {
        const val EXTRA_ARTICLE = "EXTRA_ARTICLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        article = intent.getParcelableExtra(EXTRA_ARTICLE) as Article
        articleViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ArticleViewModel::class.java)
        articleViewModel.setContext(applicationContext)
        articleViewModel.getArticleById(article.id)?.observe(this, Observer {result ->
            if(result?.status == true) {
                tv_article_detail_title.text = result.data.title
                tv_article_writer.text = result.data.author
                tv_article_content.text = result.data.description
                tv_source.text = result.data.source
            }
        })
    }
}
