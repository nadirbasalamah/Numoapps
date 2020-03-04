package nadirbasalamah.android.com.numoapps.util

import nadirbasalamah.android.com.numoapps.model.response.*
import retrofit2.Call
import retrofit2.http.*

interface NutritionistApiInterface {

    @FormUrlEncoded
    @POST("updateAntropometry/{id}")
    fun postUpdateAntropometry(
        @Path("id") id: Int?,
        @Field("bb") bb: Float?,
        @Field("tb") tb: Float?,
        @Field("lila") lila: Float?,
        @Field("bbi") bbi: Float?,
        @Field("fat") fat: Float?,
        @Field("visceral_fat") visceral_fat: Float?,
        @Field("muscle") muscle: Float?,
        @Field("body_age") body_age: Float?
    ): Call<AntropometryResponse?>?

    @FormUrlEncoded
    @POST("updateBiochemistry/{id}")
    fun postUpdateBiochemistry(
        @Path("id") id: Int?,
        @Field("gda") gda: Float?,
        @Field("gdp") gdp: Float?,
        @Field("gd2jpp") gd2jpp: Float?,
        @Field("asam_urat") asam_urat: Float?,
        @Field("trigliserida") trigliserida: Float?,
        @Field("kolesterol") kolesterol: Float?,
        @Field("ldl") ldl: Float?,
        @Field("hdl") hdl: Float?,
        @Field("ureum") ureum: Float?,
        @Field("kreatinin") kreatinin: Float?,
        @Field("sgot") sgot: Float?,
        @Field("sgpt") sgpt: Float?
    ): Call<BiochemistryResponse?>?

    @FormUrlEncoded
    @POST("updateClinic/{id}")
    fun postUpdateClinic(
        @Path("id") id: Int?,
        @Field("tensi") tensi: Float?,
        @Field("rr") rr: Float?,
        @Field("suhu") suhu: Float?,
        @Field("lainnya") lainnya: String?,
        @Field("oedema") oedema: Float?,
        @Field("aktivitas") aktivitas: String?,
        @Field("durasi_olahraga") durasi_olahraga: String?,
        @Field("jenis_olahraga") jenis_olahraga: String?,
        @Field("diagnosa_dahulu") diagnosa_dahulu: String?,
        @Field("diagnosa_skrg") diagnosa_skrg: String?
    ): Call<ClinicResponse?>?

    @FormUrlEncoded
    @POST("updateDietary/{id}")
    fun postUpdateDietary(
        @Path("id") id: Int?,
        @Field("nafsu_makan") nafsu_makan: String?,
        @Field("frekuensi_makan") frekuensi_makan: String?,
        @Field("alergi") alergi: String?,
        @Field("makanan_kesukaan") makanan_kesukaan: String?,
        @Field("dietary_nasi") dietary_nasi: String?,
        @Field("dietary_lauk_hewani") dietary_lauk_hewani: String?,
        @Field("dietary_lauk_nabati") dietary_lauk_nabati: String?,
        @Field("dietary_sayur") dietary_sayur: String?,
        @Field("dietary_sumber_minyak") dietary_sumber_minyak: String?,
        @Field("dietary_minuman") dietary_minuman: String?,
        @Field("dietary_softdrink") dietary_softdrink: String?,
        @Field("dietary_jus") dietary_jus: String?,
        @Field("dietary_suplemen") dietary_suplemen: String?,
        @Field("dietary_lainnya") dietary_lainnya: String?,
        @Field("lain_lain") lain_lain: String?
    ): Call<DietaryResponse?>?

    @FormUrlEncoded
    @POST("updateDiagnose/{id}")
    fun postUpdateDiagnose(
        @Path("id") id: Int?,
        @Field("diagnose") diagnose: String?
    ): Call<DiagnoseResponse?>?

    @FormUrlEncoded
    @POST("updateInterenvention/{id}")
    fun postUpdateInterenvention(
        @Path("id") id: Int?,
        @Field("energi") energi: String?,
        @Field("keterangan_inter") keterangan_inter: String?,
        @Field("persen_karbohidrat") persen_karbohidrat: Float?,
        @Field("persen_protein") persen_protein: Float?,
        @Field("persen_lemak") persen_lemak: Float?
    ): Call<InterenventionResponse?>?

    @FormUrlEncoded
    @POST("updateMonitoring/{id}")
    fun postUpdateMonitoring(
        @Path("id") id: Int?,
        @Field("mon_date") mon_date: String?,
        @Field("result") result: String?,
        @Field("return_date") return_date: String?
    ): Call<MonitoringResponse?>?

    @GET("nutRecord/{id}")
    fun getNutRecordById(@Path("id") id: Int?): Call<NutritionRecordResponse?>?

    @GET("articles")
    fun getAllArticles(): Call<ArticlesResponse?>?

    @GET("guides")
    fun getAllGuides(): Call<ArticlesResponse?>?

    @GET("article/{id}")
    fun getArticleById(@Path("id") id: Int?): Call<ArticleResponse?>?

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

    @POST("editArticle/{id}")
    fun postEditArticle(
        @Path("id") id: Int?,
        @Field("title") title: String?,
        @Field("description") description: String?,
        @Field("source") source: String?
    ): Call<ArticleResponse?>?
}