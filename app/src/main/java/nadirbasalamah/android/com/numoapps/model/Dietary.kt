package nadirbasalamah.android.com.numoapps.model

import com.google.gson.annotations.SerializedName

data class Dietary(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("id_patient")
    private val id_patient: Int,
    @SerializedName("nafsu_makan")
    private val nafsu_makan: String,
    @SerializedName("frekuensi_makan")
    private val frekuensi_makan: String,
    @SerializedName("alergi")
    private val alergi: String,
    @SerializedName("makanan_kesukaan")
    private val makanan_kesukaan: String,
    @SerializedName("dietary_nasi")
    private val dietary_nasi: String,
    @SerializedName("dietary_lauk_hewani")
    private val dietary_lauk_hewani: String,
    @SerializedName("dietary_lauk_nabati")
    private val dietary_lauk_nabati: String,
    @SerializedName("dietary_sayur")
    private val dietary_sayur: String,
    @SerializedName("dietary_sumber_minyak")
    private val dietary_sumber_minyak: String,
    @SerializedName("dietary_minuman")
    private val dietary_minuman: String,
    @SerializedName("dietary_softdrink")
    private val dietary_softdrink: String,
    @SerializedName("dietary_jus")
    private val dietary_jus: String,
    @SerializedName("dietary_suplemen")
    private val dietary_suplemen: String,
    @SerializedName("dietary_lainnya")
    private val dietary_lainnya: String,
    @SerializedName("lain_lain")
    private val lain_lain: String
)