package nadirbasalamah.android.com.numoapps.util

import nadirbasalamah.android.com.numoapps.model.response.ArticleResponse
import nadirbasalamah.android.com.numoapps.model.response.ArticlesResponse
import retrofit2.Call
import retrofit2.http.*

interface ArticleApiInterface {
    @GET("articles")
    fun getAllArticles(): Call<ArticlesResponse?>?

    @GET("guides")
    fun getAllGuides(): Call<ArticlesResponse?>?

    @GET("article/{id}")
    fun getArticleById(@Path("id") id: Int?): Call<ArticleResponse?>?

    @GET("getArticle/{title}")
    fun getArticleByTitle(@Path("title") title: String?): Call<ArticlesResponse?>?

    @FormUrlEncoded
    @POST("addArticle")
    fun postAddArticle(
        @Field("author") author: String?,
        @Field("type") type: String?,
        @Field("title") title: String?,
        @Field("description") description: String?,
        @Field("source") source: String?
    ): Call<ArticleResponse?>?


    @POST("deleteArticle/{id}")
    fun deleteArticleById(@Path("id") id: Int?): Call<ArticleResponse?>?

    @FormUrlEncoded
    @POST("editArticle/{id}")
    fun postEditArticle(
        @Path("id") id: Int?,
        @Field("title") title: String?,
        @Field("description") description: String?,
        @Field("source") source: String?
    ): Call<ArticleResponse?>?
}