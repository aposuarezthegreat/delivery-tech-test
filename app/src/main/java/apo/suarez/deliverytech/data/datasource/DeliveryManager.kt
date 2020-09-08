package apo.suarez.deliverytech.data.datasource

import androidx.lifecycle.LiveData
import apo.suarez.deliverytech.data.local.DeliveryItem
import apo.suarez.deliverytech.data.networkcalls.GetDeliveryList
import org.json.JSONObject

interface DeliveryManager {

    fun getDeliveryList() : GetDeliveryList

    suspend fun saveDeliveryItem(delivery: DeliveryItem)

    suspend fun getCacheDeliveryList() : LiveData<List<DeliveryItem>>

}