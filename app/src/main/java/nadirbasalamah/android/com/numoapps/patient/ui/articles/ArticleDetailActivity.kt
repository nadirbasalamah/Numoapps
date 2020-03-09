package nadirbasalamah.android.com.numoapps.patient.ui.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_article_detail.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Article

class ArticleDetailActivity : AppCompatActivity() {
    private lateinit var article: Article

    companion object {
        const val EXTRA_ARTICLE = "EXTRA_ARTICLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)

        article = intent.getParcelableExtra(EXTRA_ARTICLE) as Article
        tv_article_detail_title.text = article.title
        tv_article_writer.text = article.author
        tv_article_content.text = article.description
        tv_source.text = article.source

    }
}
