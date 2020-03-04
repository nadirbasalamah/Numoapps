package nadirbasalamah.android.com.numoapps.model.response

import com.google.gson.annotations.SerializedName
import nadirbasalamah.android.com.numoapps.model.entity.*

data class NutritionRecordResponse(
    @SerializedName("status")
     val status: Boolean,
    @SerializedName("antropometry_data")
     val antropometry_data: Antropometry,
    @SerializedName("biochemistry_data")
     val biochemistry_data: Biochemistry,
    @SerializedName("clinic_data")
     val clinic_data: Clinic,
    @SerializedName("dietary_data")
     val dietary_data: Dietary,
    @SerializedName("diagnose_data")
     val diagnose_data: Diagnose,
    @SerializedName("interenvention_data")
     val interenvention_data: Interenvention,
    @SerializedName("monitoring_data")
     val monitoring_data: Monitoring
)