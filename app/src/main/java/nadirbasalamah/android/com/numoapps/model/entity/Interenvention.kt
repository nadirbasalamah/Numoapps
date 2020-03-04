package nadirbasalamah.android.com.numoapps.model.entity

import com.google.gson.annotations.SerializedName

data class Interenvention(
    @SerializedName("id")
     val id: Int,
    @SerializedName("id_patient")
     val id_patient: Int,
    @SerializedName("energi")
     val energi: Float,
    @SerializedName("keterangan_inter")
     val keterangan_inter: String,
    @SerializedName("persen_karbohidrat")
     val persen_karbohidrat: Float,
    @SerializedName("gram_karbohidrat")
     val gram_karbohidrat: Float,
    @SerializedName("persen_protein")
     val persen_protein: Float,
    @SerializedName("gram_protein")
     val gram_protein: Float,
    @SerializedName("persen_lemak")
     val persen_lemak: Float,
    @SerializedName("gram_lemak")
     val gram_lemak: Float
)