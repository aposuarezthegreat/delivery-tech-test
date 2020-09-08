package apo.suarez.deliverytech.ui

import apo.suarez.deliverytech.data.datasource.DeliveryManager
import apo.suarez.deliverytech.data.local.DeliveryItem
import apo.suarez.deliverytech.data.networkcalls.Model
import io.uniflow.androidx.flow.AndroidDataFlow
import kotlinx.coroutines.launch

class DeliveryListViewModel(private val deliveryManager: DeliveryManager) : AndroidDataFlow() {

    fun getDeliveryList() = deliveryManager.getDeliveryList()

    suspend fun cacheDeliveryList(deliveryList: List<Model.GetDeliveryResponse>) {
        for (item in deliveryList){
            val delivery = DeliveryItem(
                PRIMARY_KEY,
                item.id,
                item.remarks,
                item.pickupTime,
                item.goodsPicture,
                item.deliveryFee,
                item.surcharge,
                item.route.start,
                item.route.end,
                item.sender.phone,
                item.sender.name,
                item.sender.email,
                false
            )
            deliveryManager.saveDeliveryItem(delivery)
        }
    }

    companion object {

        private const val PRIMARY_KEY = 0

    }

}