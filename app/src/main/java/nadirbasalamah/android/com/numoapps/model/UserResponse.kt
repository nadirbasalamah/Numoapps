package nadirbasalamah.android.com.numoapps.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("status")
    private val status: String
)