package nadirbasalamah.android.com.numoapps.nutritionist.ui.articles

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_nut_article_detail.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.model.entity.Article
import nadirbasalamah.android.com.numoapps.viewmodel.ArticleViewModel

class NutArticleDetailActivity : AppCompatActivity() {
    private lateinit var article: Article
    private lateinit var articleViewModel: ArticleViewModel
    companion object {
        const val EXTRA_NUT_ARTICLE = "EXTRA_NUT_ARTICLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nut_article_detail)

        article = intent.getParcelableExtra(EXTRA_NUT_ARTICLE) as Article
        tv_article_detail_title.text = article.title
        tv_article_writer.text = article.author
        tv_article_content.text = article.description
        tv_source.text = article.source
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.article_detail_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_edit_article -> {
                val intent = Intent(applicationContext,EditArticleActivity::class.java)
                intent.putExtra(EditArticleActivity.EXTRA_NUT_ARTICLE_DETAIL,article)
                startActivity(intent)
                return true
            }
            R.id.menu_delete_article -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage(R.string.delete_confirmation)
                    .setPositiveButton(R.string.confirmation_yes
                    ) { dialog, id ->
                        articleViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ArticleViewModel::class.java)
                        articleViewModel.setContext(applicationContext)
                        articleViewModel.deleteArticle(article.id)?.observe(this, Observer {result ->
                            if(result?.status == true) {
                                finish()
                            }
                        })
                        dialog.dismiss()
                    }
                    .setNegativeButton(R.string.confirmation_no
                    ) { dialog, id ->
                        dialog.cancel()
                    }
                // Create the AlertDialog object and return it
                builder.create()
                builder.show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}