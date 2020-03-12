package nadirbasalamah.android.com.numoapps.model.response

import com.google.gson.annotations.SerializedName
import nadirbasalamah.android.com.numoapps.model.entity.Article
import nadirbasalamah.android.com.numoapps.model.entity.FoodMenu

data class FoodMenuResponse(
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("data")
    val data: FoodMenu
)