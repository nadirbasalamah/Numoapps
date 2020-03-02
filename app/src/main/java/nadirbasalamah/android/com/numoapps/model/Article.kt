package nadirbasalamah.android.com.numoapps.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("author")
    private val author: String,
    @SerializedName("type")
    private val type: String,
    @SerializedName("description")
    private val description: String,
    @SerializedName("image")
    private val image: String,
    @SerializedName("source")
    private val source: String
)