package nadirbasalamah.android.com.numoapps.nutritionist.ui.articles

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_add_article.*
import nadirbasalamah.android.com.numoapps.R
import nadirbasalamah.android.com.numoapps.main.MainActivity
import nadirbasalamah.android.com.numoapps.viewmodel.ArticleViewModel
import nadirbasalamah.android.com.numoapps.viewmodel.AdminViewModel

class AddArticleActivity : AppCompatActivity() {
    private lateinit var articleViewModel: ArticleViewModel
    private lateinit var articleType: String
    private lateinit var adminViewModel: AdminViewModel
    private var userId: Int = 0
    private var articleAuthor: String? = ""
    companion object {
        const val ARTICLE_TYPE = "ARTICLE_TYPE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_article)

        articleType = intent.getStringExtra(ARTICLE_TYPE) as String

        val appContext = MainActivity.getAppContext()
        val loginData = appContext.getSharedPreferences("Login", Context.MODE_PRIVATE)
        userId = loginData.getInt("id_user",0)

        adminViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(AdminViewModel::class.java)
        adminViewModel.setContext(applicationContext)
        adminViewModel.getNutritionistById(userId)?.observe(this, Observer {result ->
            if(result?.status == true) {
                articleAuthor = result?.data.fullname
            }
        })

        btn_article_save.setOnClickListener {
            var data: HashMap<String, String> = HashMap<String, String>()
            val title = et_article_title.text.toString()
            val description = et_article_description.text.toString()
            val source = et_article_source.text.toString()

            data.put("author", articleAuthor.toString())
            data.put("type",articleType)
            data.put("title",title)
            data.put("description",description)
            data.put("source",source)

            articleViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ArticleViewModel::class.java)
            articleViewModel.setContext(applicationContext)
            articleViewModel.addArticle(data)?.observe(this, Observer {result ->
                if(result?.status == true) {
                    finish()
                }
            })
        }
    }
}