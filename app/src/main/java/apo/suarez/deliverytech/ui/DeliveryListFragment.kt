package apo.suarez.deliverytech.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import apo.suarez.deliverytech.R
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_delivery_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeliveryListFragment : Fragment(R.layout.fragment_delivery_list) {

    private val viewModel by viewModel<DeliveryListViewModel>()

    private val deliveryListAdapter = DeliveryListAdapter()

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deliverListRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = deliveryListAdapter
        }

        val observableDeliveryList = viewModel.getDeliveryList().request("2","20")

        observableDeliveryList.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    Log.d("LLF","Delivery List -> $response")
                    deliveryListAdapter.deliveryList = response
                },
                { error ->
                    Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
                }
            )
    }
}