package nadirbasalamah.android.com.numoapps.model.entity

import com.google.gson.annotations.SerializedName

data class Clinic(
    @SerializedName("id")
     val id: Int,
    @SerializedName("id_patient")
     val id_patient: Int,
    @SerializedName("tensi")
     val tensi: Float,
    @SerializedName("rr")
     val rr: Float,
    @SerializedName("suhu")
     val suhu: Float,
    @SerializedName("lainnya")
     val lainnya: String,
    @SerializedName("oedema")
     val oedema: Float,
    @SerializedName("aktivitas")
     val aktivitas: String,
    @SerializedName("durasi_olahraga")
     val durasi_olahraga: String,
    @SerializedName("jenis_olahraga")
     val jenis_olahraga: String,
    @SerializedName("diagnosa_dahulu")
     val diagnosa_dahulu: String,
    @SerializedName("diagnosa_skrg")
     val diagnosa_skrg: String
)