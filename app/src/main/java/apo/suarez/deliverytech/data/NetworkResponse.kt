package apo.suarez.deliverytech.data

import com.google.gson.annotations.SerializedName

data class NetworkResponse(
    @SerializedName("status") val status: Int = 0,
    @SerializedName("error") val error: String = "",
    @SerializedName("error_type") val errorType: String = "",
    @SerializedName("message") val message: String = ""
)