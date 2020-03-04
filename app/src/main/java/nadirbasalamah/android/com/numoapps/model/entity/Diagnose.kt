package nadirbasalamah.android.com.numoapps.model.entity

import com.google.gson.annotations.SerializedName

data class Diagnose(
    @SerializedName("id")
     val id: Int,
    @SerializedName("id_patient")
     val id_patient: Int,
    @SerializedName("diagnose")
     val diagnose: String
)