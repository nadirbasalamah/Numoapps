package nadirbasalamah.android.com.numoapps.nutritionist.ui.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_edit_article.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Article
import nadirbasalamah.android.com.numoapps.viewmodel.ArticleViewModel

class EditArticleActivity : AppCompatActivity() {
    private lateinit var article: Article
    private lateinit var articleViewModel: ArticleViewModel
    companion object {
        const val EXTRA_NUT_ARTICLE_DETAIL = "EXTRA_NUT_ARTICLE_DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_article)

        article = intent.getParcelableExtra(EXTRA_NUT_ARTICLE_DETAIL) as Article
        et_article_edit_title.setText(article.title)
        et_article_edit_description.setText(article.description)
        et_article_edit_source.setText(article.source)

        btn_article_edit_save.setOnClickListener {
            val data: HashMap<String, String> = HashMap()
            val title = et_article_edit_title.text.toString()
            val description = et_article_edit_description.text.toString()
            val source = et_article_edit_source.text.toString()

            data.put("id",article.id.toString())
            data.put("title",title)
            data.put("description",description)
            data.put("source",source)

            articleViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ArticleViewModel::class.java)
            articleViewModel.setContext(applicationContext)
            articleViewModel.editArticle(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    finish()
                }
            })
        }

    }
}
