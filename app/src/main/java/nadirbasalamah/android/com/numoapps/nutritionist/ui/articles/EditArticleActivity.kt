package nadirbasalamah.android.com.numoapps.nutritionist.ui.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import id.rizmaulana.sheenvalidator.lib.SheenValidator
import kotlinx.android.synthetic.main.activity_edit_article.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Article
import nadirbasalamah.android.com.numoapps.viewmodel.ArticleViewModel

class EditArticleActivity : AppCompatActivity() {
    private lateinit var article: Article
    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var sheenValidator: SheenValidator
    companion object {
        const val EXTRA_NUT_ARTICLE_DETAIL = "EXTRA_NUT_ARTICLE_DETAIL"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_article)
        sheenValidator = SheenValidator(this)

        article = intent.getParcelableExtra(EXTRA_NUT_ARTICLE_DETAIL) as Article
        articleViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ArticleViewModel::class.java)
        articleViewModel.setContext(applicationContext)
        articleViewModel.getArticleById(article.id)?.observe(this, Observer {result ->
            if(result?.status == true) {
                et_article_edit_title.setText(result.data.title)
                et_article_edit_description.setText(result.data.description)
                et_article_edit_source.setText(result.data.source)
            }
        })

        sheenValidator.setOnValidatorListener {
            Toast.makeText(this,"Validasi sukses!",Toast.LENGTH_SHORT).show()
        }

        sheenValidator.registerAsRequired(et_article_edit_title)
        sheenValidator.registerAsRequired(et_article_edit_description)
        sheenValidator.registerAsRequired(et_article_edit_source)

        btn_article_edit_save.setOnClickListener {
            sheenValidator.validate()
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
