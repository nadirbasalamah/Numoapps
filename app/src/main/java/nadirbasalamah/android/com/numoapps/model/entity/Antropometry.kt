package nadirbasalamah.android.com.numoapps.model.entity

import com.google.gson.annotations.SerializedName

data class Antropometry(
    @SerializedName("id")
     val id: Int,
    @SerializedName("id_patient")
     val id_patient: Int,
    @SerializedName("bb")
     val bb: Float,
    @SerializedName("tb")
     val tb: Float,
    @SerializedName("lila")
     val lila: Float,
    @SerializedName("imt")
     val imt: Float,
    @SerializedName("bbi")
     val bbi: Float,
    @SerializedName("status")
     val status: String,
    @SerializedName("fat")
     val fat: Float,
    @SerializedName("visceral_fat")
     val visceral_fat: Float,
    @SerializedName("muscle")
     val muscle: Float,
    @SerializedName("body_age")
     val body_age: Float
)