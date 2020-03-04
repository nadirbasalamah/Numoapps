package nadirbasalamah.android.com.numoapps.model.response

import com.google.gson.annotations.SerializedName
import nadirbasalamah.android.com.numoapps.model.entity.Article

data class ArticleResponse(
    @SerializedName("status")
     val status: Boolean,
    @SerializedName("data")
     val data: Article
)