package nadirbasalamah.android.com.numoapps.model.response

import com.google.gson.annotations.SerializedName
import nadirbasalamah.android.com.numoapps.model.entity.Dietary

data class DietaryResponse(
    @SerializedName("status")
     val status: String,
    @SerializedName("data")
     val data: Dietary
)