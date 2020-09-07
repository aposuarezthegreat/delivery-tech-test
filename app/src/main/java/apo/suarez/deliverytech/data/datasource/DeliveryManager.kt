package apo.suarez.deliverytech.data.datasource

import apo.suarez.deliverytech.data.networkcalls.GetDeliveryList
import org.json.JSONObject

interface DeliveryManager {

    fun getDeliveryList() : GetDeliveryList

}