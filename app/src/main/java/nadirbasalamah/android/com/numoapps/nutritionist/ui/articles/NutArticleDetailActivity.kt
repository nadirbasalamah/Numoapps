package nadirbasalamah.android.com.numoapps.nutritionist.ui.articles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import nadirbasalamah.android.com.numoapps.R

class NutArticleDetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_NUT_ARTICLE = "EXTRA_NUT_ARTICLE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nut_article_detail)
    }
}
