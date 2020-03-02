package nadirbasalamah.android.com.numoapps.model

import com.google.gson.annotations.SerializedName

data class Clinic(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("id_patient")
    private val id_patient: Int,
    @SerializedName("tensi")
    private val tensi: Float,
    @SerializedName("rr")
    private val rr: Float,
    @SerializedName("suhu")
    private val suhu: Float,
    @SerializedName("lainnya")
    private val lainnya: String,
    @SerializedName("oedema")
    private val oedema: Float,
    @SerializedName("aktivitas")
    private val aktivitas: String,
    @SerializedName("durasi_olahraga")
    private val durasi_olahraga: String,
    @SerializedName("jenis_olahraga")
    private val jenis_olahraga: String,
    @SerializedName("diagnosa_dahulu")
    private val diagnosa_dahulu: String,
    @SerializedName("diagnosa_skrg")
    private val diagnosa_skrg: String
)