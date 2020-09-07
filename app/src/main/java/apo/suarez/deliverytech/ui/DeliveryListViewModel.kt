package apo.suarez.deliverytech.ui

import apo.suarez.deliverytech.data.datasource.DeliveryManager
import io.uniflow.androidx.flow.AndroidDataFlow

class DeliveryListViewModel(private val deliveryManager: DeliveryManager) : AndroidDataFlow() {

    fun getDeliveryList() = deliveryManager.getDeliveryList()

}