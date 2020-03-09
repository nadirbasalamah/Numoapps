package nadirbasalamah.android.com.numoapps.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nadirbasalamah.android.com.numoapps.model.response.ArticleResponse
import nadirbasalamah.android.com.numoapps.model.response.ArticlesResponse
import nadirbasalamah.android.com.numoapps.util.ApiClient
import nadirbasalamah.android.com.numoapps.util.ArticleApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticleViewModel : ViewModel() {
    private var articleApiInterface: ArticleApiInterface? = null
    private var getAllArticles: Call<ArticlesResponse?>? = null
    private var getAllGuides: Call<ArticlesResponse?>? = null
    private var getArticleById: Call<ArticleResponse?>? = null
    private var getArticleByTitle: Call<ArticlesResponse?>? = null
    private var postAddArticle: Call<ArticleResponse?>? = null
    private var postDeleteArticle: Call<ArticleResponse?>? = null
    private var postEditArticle: Call<ArticleResponse?>? = null

    private var context: Context? = null

    internal fun setContext(context: Context?) { this.context = context }

    internal fun getAllArticles(): MutableLiveData<ArticlesResponse?>? {
        articleApiInterface = ApiClient.getClient()?.create(ArticleApiInterface::class.java)
        var requestResult: ArticlesResponse?
        val result: MutableLiveData<ArticlesResponse?>? = MutableLiveData()
        getAllArticles = articleApiInterface?.getAllArticles()
        getAllArticles?.enqueue(
            object : Callback<ArticlesResponse?> {
                override fun onResponse(
                    call: Call<ArticlesResponse?>?,
                    response: Response<ArticlesResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data artikel ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data artikel tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<ArticlesResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun getAllGuides(): MutableLiveData<ArticlesResponse?>? {
        articleApiInterface = ApiClient.getClient()?.create(ArticleApiInterface::class.java)
        var requestResult: ArticlesResponse?
        val result: MutableLiveData<ArticlesResponse?>? = MutableLiveData()
        getAllGuides = articleApiInterface?.getAllGuides()
        getAllGuides?.enqueue(
            object : Callback<ArticlesResponse?> {
                override fun onResponse(
                    call: Call<ArticlesResponse?>?,
                    response: Response<ArticlesResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data panduan ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data panduan tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<ArticlesResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun getArticleById(data: Int): MutableLiveData<ArticleResponse?>? {
        articleApiInterface = ApiClient.getClient()?.create(ArticleApiInterface::class.java)
        var requestResult: ArticleResponse?
        val result: MutableLiveData<ArticleResponse?>? = MutableLiveData()
        getArticleById = articleApiInterface?.getArticleById(data)
        getArticleById?.enqueue(
            object : Callback<ArticleResponse?> {
                override fun onResponse(
                    call: Call<ArticleResponse?>?,
                    response: Response<ArticleResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data artikel ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data artikel tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<ArticleResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun getArticleByTitle(data: String): MutableLiveData<ArticlesResponse?>? {
        articleApiInterface = ApiClient.getClient()?.create(ArticleApiInterface::class.java)
        var requestResult: ArticlesResponse?
        val result: MutableLiveData<ArticlesResponse?>? = MutableLiveData()
        getArticleByTitle = articleApiInterface?.getArticleByTitle(data)
        getArticleByTitle?.enqueue(
            object : Callback<ArticlesResponse?> {
                override fun onResponse(
                    call: Call<ArticlesResponse?>?,
                    response: Response<ArticlesResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data artikel ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data artikel tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<ArticlesResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun addArticle(data: HashMap<String, String>): MutableLiveData<ArticleResponse?>? {
        articleApiInterface = ApiClient.getClient()?.create(ArticleApiInterface::class.java)
        var requestResult: ArticleResponse?
        val result: MutableLiveData<ArticleResponse?>? = MutableLiveData()
        postAddArticle = articleApiInterface?.postAddArticle(
            data["author"],
            data["type"],
            data["title"],
            data["description"],
            data["source"]
        )
        postAddArticle?.enqueue(
            object : Callback<ArticleResponse?> {
                override fun onResponse(
                    call: Call<ArticleResponse?>?,
                    response: Response<ArticleResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data artikel berhasil ditambahkan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Penambahan data artikel gagal!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<ArticleResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun deleteArticle(data: HashMap<String, String>): MutableLiveData<ArticleResponse?>? {
        articleApiInterface = ApiClient.getClient()?.create(ArticleApiInterface::class.java)
        var requestResult: ArticleResponse?
        val result: MutableLiveData<ArticleResponse?>? = MutableLiveData()
        postDeleteArticle = articleApiInterface?.deleteArticleById(data["id"]?.toInt())
        postDeleteArticle?.enqueue(
            object : Callback<ArticleResponse?> {
                override fun onResponse(
                    call: Call<ArticleResponse?>?,
                    response: Response<ArticleResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data artikel ditemukan!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Data artikel tidak ditemukan!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<ArticleResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }

    internal fun editArticle(data: HashMap<String, String>): MutableLiveData<ArticleResponse?>? {
        articleApiInterface = ApiClient.getClient()?.create(ArticleApiInterface::class.java)
        var requestResult: ArticleResponse?
        val result: MutableLiveData<ArticleResponse?>? = MutableLiveData()
        postEditArticle = articleApiInterface?.postEditArticle(
            data["id"]?.toInt(),
            data["title"],
            data["description"],
            data["source"]
        )
        postEditArticle?.enqueue(
            object : Callback<ArticleResponse?> {
                override fun onResponse(
                    call: Call<ArticleResponse?>?,
                    response: Response<ArticleResponse?>?
                ) {
                    val test = response?.body()
                    requestResult = test
                    if(requestResult?.status == true) {
                        Toast.makeText(context, "Data artikel berhasil diubah!", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, "Perubahan data artikel gagal!", Toast.LENGTH_SHORT).show()
                    }
                    result?.value = requestResult
                }

                override fun onFailure(
                    call: Call<ArticleResponse?>,
                    t: Throwable
                ) {
                    Toast.makeText(context, "Error", Toast.LENGTH_LONG).show()
                    Log.d("ERROR:",t.toString())
                }
            }
        )
        return result
    }
}