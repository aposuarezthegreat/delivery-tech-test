package apo.suarez.deliverytech.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import apo.suarez.deliverytech.R
import apo.suarez.deliverytech.data.networkcalls.Model
import apo.suarez.deliverytech.utils.DownloadImageTask
import java.net.URL

class DeliveryListAdapter :RecyclerView.Adapter<DeliveryListAdapter.ViewHolder>(){

    var itemClickListener: ((Model.GetDeliveryResponse) -> Unit)? = null

    var deliveryList: List<Model.GetDeliveryResponse> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_delivery, parent, false)
        return ViewHolder(view,itemClickListener)
    }

    override fun getItemCount(): Int = deliveryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(deliveryList[position])
    }

    class ViewHolder(itemView: View,private val itemClickListener: ((Model.GetDeliveryResponse) -> Unit)?) :
        RecyclerView.ViewHolder(itemView) {

        private val productCard : CardView = itemView.findViewById(R.id.deliveryItemCard)

        private val productImage: ImageView = itemView.findViewById(R.id.productImageView)

        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)

        private val productStartDestination: TextView = itemView.findViewById(R.id.productSender)

        private val productEndDestination: TextView = itemView.findViewById(R.id.productRecipient)

        private val productFavouriteImage: ImageView = itemView.findViewById(R.id.productFavouriteImageView)

        fun bind(item: Model.GetDeliveryResponse) {
            val productPriceTotal : Double = getValueAsDouble(item.deliveryFee) + getValueAsDouble(item.surcharge)
            DownloadImageTask(productImage).execute(item.goodsPicture)
            productPrice.text = String.format("$CURRENCY_SIGN %.2f",productPriceTotal)
            productStartDestination.text = itemView.resources.getString(R.string.label_start_destination,item.route.start)
            productEndDestination.text = itemView.resources.getString(R.string.label_end_destination,item.route.end)
            productCard.setOnClickListener { itemClickListener?.invoke(item) }
        }

        private fun getValueAsDouble(numberString: String) : Double
                = numberString.removePrefix(CURRENCY_SIGN).toDouble()
    }

    companion object {
        private const val CURRENCY_SIGN = "$"
    }
}