package nadirbasalamah.android.com.numoapps.model.response

import com.google.gson.annotations.SerializedName
import nadirbasalamah.android.com.numoapps.model.entity.Antropometry

data class AntropometryResponse(
    @SerializedName("status")
     val status: String,
    @SerializedName("data")
     val data: Antropometry
)