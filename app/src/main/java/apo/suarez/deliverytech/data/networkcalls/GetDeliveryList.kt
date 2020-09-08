package apo.suarez.deliverytech.data.networkcalls

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable
import retrofit2.http.*

interface GetDeliveryList {
    @Headers("Content-Type: application/json")
    @GET("/v2/deliveries")
    fun request(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int
    ):  Observable<List<Model.GetDeliveryResponse>>
}

object Model {
    data class GetDeliveryResponse(
        @SerializedName("id") var id: String,
        @SerializedName("remarks") var remarks: String,
        @SerializedName("pickupTime") var pickupTime: String,
        @SerializedName("goodsPicture") var goodsPicture: String,
        @SerializedName("deliveryFee") var deliveryFee: String,
        @SerializedName("surcharge") var surcharge: String,
        @SerializedName("route") var route: Route,
        @SerializedName("sender") var sender: Sender
    )
    data class Route(
        @SerializedName("start") var start: String,
        @SerializedName("end") var end: String
    )

   data class Sender(
       @SerializedName("phone") var phone: String,
       @SerializedName("name") var name: String,
       @SerializedName("email") var email: String
   )
}
