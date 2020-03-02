package nadirbasalamah.android.com.numoapps.model

import com.google.gson.annotations.SerializedName

data class Biochemistry(
    @SerializedName("id")
    private val id: Int,
    @SerializedName("id_patient")
    private val id_patient: Int,
    @SerializedName("gda")
    private val gda: Float,
    @SerializedName("gdp")
    private val gdp: Float,
    @SerializedName("gd2jpp")
    private val gd2jpp: Float,
    @SerializedName("asam_urat")
    private val asam_urat: Float,
    @SerializedName("trigliserida")
    private val trigliserida: Float,
    @SerializedName("kolesterol")
    private val kolesterol: Float,
    @SerializedName("ldl")
    private val ldl: Float,
    @SerializedName("hdl")
    private val hdl: Float,
    @SerializedName("ureum")
    private val ureum: Float,
    @SerializedName("kreatinin")
    private val kreatinin: Float,
    @SerializedName("sgot")
    private val sgot: Float,
    @SerializedName("sgpt")
    private val sgpt: Float
)