package nadirbasalamah.android.com.numoapps.model.response

import com.google.gson.annotations.SerializedName
import nadirbasalamah.android.com.numoapps.model.entity.User

data class UserResponse(
    @SerializedName("status")
     val status: Boolean,
    @SerializedName("data")
     val data: User
)