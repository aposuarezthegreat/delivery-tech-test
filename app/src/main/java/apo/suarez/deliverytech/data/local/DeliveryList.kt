package apo.suarez.deliverytech.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "delivery_list")
class DeliveryItem(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("deliveryId") val deliveryId: String,
    @SerializedName("remarks") val remarks: String,
    @SerializedName("pickupTime") val pickupTime: String,
    @SerializedName("goodsPicture") val goodsPicture: String,
    @SerializedName("deliveryFee") val deliveryFee: String,
    @SerializedName("surcharge") val surcharge: String,
    @SerializedName("routeStart") val routeStart: String,
    @SerializedName("routeEnd") val routeEnd: String,
    @SerializedName("senderPhone") val senderPhone: String,
    @SerializedName("senderName") val senderName: String,
    @SerializedName("senderEmail") val senderEmail: String,
    @SerializedName("isFavourite") val isFavourite: Boolean
)