package nadirbasalamah.android.com.numoapps.model.entity

import com.google.gson.annotations.SerializedName

data class Biochemistry(
    @SerializedName("id")
     val id: Int,
    @SerializedName("id_patient")
     val id_patient: Int,
    @SerializedName("gda")
     val gda: Float,
    @SerializedName("gdp")
     val gdp: Float,
    @SerializedName("gd2jpp")
     val gd2jpp: Float,
    @SerializedName("asam_urat")
     val asam_urat: Float,
    @SerializedName("trigliserida")
     val trigliserida: Float,
    @SerializedName("kolesterol")
     val kolesterol: Float,
    @SerializedName("ldl")
     val ldl: Float,
    @SerializedName("hdl")
     val hdl: Float,
    @SerializedName("ureum")
     val ureum: Float,
    @SerializedName("kreatinin")
     val kreatinin: Float,
    @SerializedName("sgot")
     val sgot: Float,
    @SerializedName("sgpt")
     val sgpt: Float
)