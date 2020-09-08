package apo.suarez.deliverytech.ui.details

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DeliveryDetails(
    val startRoute : String,
    val endRoute : String,
    val itemImage : String,
    val deliveryFee : String
) : Parcelable