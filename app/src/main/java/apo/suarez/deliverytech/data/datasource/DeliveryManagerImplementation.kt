package apo.suarez.deliverytech.data.datasource

import apo.suarez.deliverytech.data.NetworkClient
import apo.suarez.deliverytech.data.networkcalls.GetDeliveryList
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

class DeliveryManagerImplementation : DeliveryManager{

    private val BASE_URL = "https://mock-api-mobile.dev.lalamove.com"

    override fun getDeliveryList() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(NetworkClient.gsonConverter)
        .client(NetworkClient.networkClient)
        .build()
        .create(GetDeliveryList::class.java)!!

}