package nadirbasalamah.android.com.numoapps.model.response

import com.google.gson.annotations.SerializedName
import nadirbasalamah.android.com.numoapps.model.entity.Monitoring

data class MonitoringResponse(
    @SerializedName("status")
     val status: Boolean,
    @SerializedName("data")
     val data: Monitoring
)