package nadirbasalamah.android.com.numoapps.model.entity

import com.google.gson.annotations.SerializedName

data class Dietary(
    @SerializedName("id")
     val id: Int,
    @SerializedName("id_patient")
     val id_patient: Int,
    @SerializedName("nafsu_makan")
     val nafsu_makan: String,
    @SerializedName("frekuensi_makan")
     val frekuensi_makan: String,
    @SerializedName("alergi")
     val alergi: String,
    @SerializedName("makanan_kesukaan")
     val makanan_kesukaan: String,
    @SerializedName("dietary_nasi")
     val dietary_nasi: String,
    @SerializedName("dietary_lauk_hewani")
     val dietary_lauk_hewani: String,
    @SerializedName("dietary_lauk_nabati")
     val dietary_lauk_nabati: String,
    @SerializedName("dietary_sayur")
     val dietary_sayur: String,
    @SerializedName("dietary_sumber_minyak")
     val dietary_sumber_minyak: String,
    @SerializedName("dietary_minuman")
     val dietary_minuman: String,
    @SerializedName("dietary_softdrink")
     val dietary_softdrink: String,
    @SerializedName("dietary_jus")
     val dietary_jus: String,
    @SerializedName("dietary_suplemen")
     val dietary_suplemen: String,
    @SerializedName("dietary_lainnya")
     val dietary_lainnya: String,
    @SerializedName("lain_lain")
     val lain_lain: String
)