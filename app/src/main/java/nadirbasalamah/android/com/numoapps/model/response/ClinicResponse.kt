package nadirbasalamah.android.com.numoapps.model.response

import com.google.gson.annotations.SerializedName
import nadirbasalamah.android.com.numoapps.model.entity.Clinic

data class ClinicResponse(
    @SerializedName("status")
     val status: Boolean,
    @SerializedName("data")
     val data: Clinic
)