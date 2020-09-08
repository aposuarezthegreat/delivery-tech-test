package apo.suarez.deliverytech.di

import apo.suarez.deliverytech.data.datasource.DeliveryManager
import apo.suarez.deliverytech.data.datasource.DeliveryManagerImplementation
import apo.suarez.deliverytech.data.local.DeliveryListDao
import apo.suarez.deliverytech.data.local.DeliveryListDb
import apo.suarez.deliverytech.ui.DeliveryListViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val deliveryModule = module {

    factory<DeliveryManager> {
        DeliveryManagerImplementation(DeliveryListDb.getDb(androidContext()).deliveryListDao())
    }

    viewModel { DeliveryListViewModel(get()) }

}