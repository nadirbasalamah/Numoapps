package nadirbasalamah.android.com.numoapps.model

import com.google.gson.annotations.SerializedName

data class Antropometry(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("id_patient")
    private val id_patient: Int,
    @SerializedName("bb")
    private val bb: Float,
    @SerializedName("tb")
    private val tb: Float,
    @SerializedName("lila")
    private val lila: Float,
    @SerializedName("imt")
    private val imt: Float,
    @SerializedName("bbi")
    private val bbi: Float,
    @SerializedName("status")
    private val status: String,
    @SerializedName("fat")
    private val fat: Float,
    @SerializedName("visceral_fat")
    private val visceral_fat: Float,
    @SerializedName("muscle")
    private val muscle: Float,
    @SerializedName("body_age")
    private val body_age: Float
)