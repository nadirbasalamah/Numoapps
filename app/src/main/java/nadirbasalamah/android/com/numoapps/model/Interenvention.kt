package nadirbasalamah.android.com.numoapps.model

import com.google.gson.annotations.SerializedName

data class Interenvention(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("id_patient")
    private val id_patient: Int,
    @SerializedName("energi")
    private val energi: Float,
    @SerializedName("keterangan_inter")
    private val keterangan_inter: String,
    @SerializedName("persen_karbohidrat")
    private val persen_karbohidrat: Float,
    @SerializedName("gram_karbohidrat")
    private val gram_karbohidrat: Float,
    @SerializedName("persen_protein")
    private val persen_protein: Float,
    @SerializedName("gram_protein")
    private val gram_protein: Float,
    @SerializedName("persen_lemak")
    private val persen_lemak: Float,
    @SerializedName("gram_lemak")
    private val gram_lemak: Float
)