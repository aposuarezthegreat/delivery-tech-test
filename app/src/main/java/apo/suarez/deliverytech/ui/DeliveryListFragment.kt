package apo.suarez.deliverytech.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import apo.suarez.deliverytech.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeliveryListFragment : Fragment(R.layout.fragment_delivery_list) {

    private val viewModel by viewModel<DeliveryListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val observableDeliveryList = viewModel.getDeliveryList().request("2","20")

        observableDeliveryList.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    Log.d("LLF","Delivery List -> $response")
                },
                { error ->
                    Log.d("LLF","Error -> $error")
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            )
    }
}