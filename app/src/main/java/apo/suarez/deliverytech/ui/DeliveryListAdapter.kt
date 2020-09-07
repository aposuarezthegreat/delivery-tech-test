package apo.suarez.deliverytech.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import apo.suarez.deliverytech.R
import apo.suarez.deliverytech.data.networkcalls.GetDeliveryList
import apo.suarez.deliverytech.data.networkcalls.Model
import java.net.URL

class DeliveryListAdapter :RecyclerView.Adapter<DeliveryListAdapter.ViewHolder>(){

    var deliveryList: List<Model.GetDeliveryResponse> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_layout_delivery, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = deliveryList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(deliveryList[position])
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val productImage: ImageView = itemView.findViewById(R.id.productImageView)

        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)

        private val productSender: TextView = itemView.findViewById(R.id.productSender)

        private val productRecipient: TextView = itemView.findViewById(R.id.productRecipient)

        private val productFavouriteImage: ImageView = itemView.findViewById(R.id.productFavouriteImageView)

        fun bind(item: Model.GetDeliveryResponse) {
            DownloadImageTask(productImage)
                .execute(item.goodsPicture)
            productPrice.text = item.deliveryFee
            productSender.text = item.route.start
            productRecipient.text = item.route.end
        }
    }


    private class DownloadImageTask(bmImage: ImageView) :
        AsyncTask<String?, Void?, Bitmap?>() {
        var bmImage: ImageView
        override fun doInBackground(vararg urls: String?): Bitmap? {
            val urldisplay = urls[0]
            var mIcon11: Bitmap? = null
            try {
                val `in` = URL(urldisplay).openStream()
                mIcon11 = BitmapFactory.decodeStream(`in`)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return mIcon11
        }

        override fun onPostExecute(result: Bitmap?) {
            bmImage.setImageBitmap(result)
        }

        init {
            this.bmImage = bmImage
        }
    }
}