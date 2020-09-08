package apo.suarez.deliverytech.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import apo.suarez.deliverytech.R
import apo.suarez.deliverytech.data.local.DeliveryItem
import apo.suarez.deliverytech.data.networkcalls.Model
import apo.suarez.deliverytech.ui.details.DeliveryDetails
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_delivery_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeliveryListFragment : Fragment(R.layout.fragment_delivery_list), CoroutineScope by MainScope() {

    private val viewModel by viewModel<DeliveryListViewModel>()

    private val deliveryListAdapter = DeliveryListAdapter().apply {
        itemClickListener = { onItemClick(it) }
    }

    private var currentPage : Int = INITIAL_PAGE

    private var newPage : Int = INITIAL_PAGE

    private val itemOffset: LiveData<Int> get() = _itemOffset

    private val _itemOffset: MutableLiveData<Int> = MutableLiveData()

    init {
        _itemOffset.value = ITEM_OFFSET
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        deliverListRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = deliveryListAdapter
        }

        refreshPageImageView.setOnClickListener {
            _itemOffset.value?.let {
                loadCurrentPage(it)
            }
        }

        currentPageTextView.text = getString(R.string.label_page,currentPage.toString())

        nextPageImageView.setOnClickListener { loadNextPage() }

        itemOffset.observe(viewLifecycleOwner, Observer {
            loadCurrentPage(it)
        })
    }

    private fun onItemClick(item: Model.GetDeliveryResponse){
        if (findNavController().currentDestination?.id == R.id.deliveryListFragment){
            val productPriceTotal : Double = getValueAsDouble(item.deliveryFee) + getValueAsDouble(item.surcharge)
            val deliveryDetails = DeliveryDetails(
                item.route.start,
                item.route.end,
                item.goodsPicture,
                String.format("$CURRENCY_SIGN %.2f",productPriceTotal)
            )
            val actionToEditDevice = DeliveryListFragmentDirections.deliveryListFragmentToDeliveryDetailsFragment(deliveryDetails)
            findNavController().navigate(actionToEditDevice)
        }
    }

    private fun getValueAsDouble(numberString: String) : Double
            = numberString.removePrefix(CURRENCY_SIGN).toDouble()

    @SuppressLint("CheckResult")
    private fun loadCurrentPage(itemOffset: Int){

        val observableDeliveryList = viewModel.getDeliveryList().request(itemOffset,ITEM_LIST_LIMIT)

        observableDeliveryList.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                    currentPage = newPage
                    currentPageTextView.text = getString(R.string.label_page, newPage.toString())

                    previousPageImageView.apply {
                        if (currentPage == INITIAL_PAGE) {
                            visibility = View.INVISIBLE
                            setOnClickListener(null)
                        } else {
                            visibility = View.VISIBLE
                            setOnClickListener { loadPreviousPage() }
                        }
                    }

                    deliveryListAdapter.apply {
                        deliveryList = response
                        notifyDataSetChanged()
                    }
                },
                { error ->
                    Toast.makeText(context, getString(R.string.error_delivery_list), Toast.LENGTH_SHORT).show()
                }
            )
    }

    private fun loadPreviousPage(){
        newPage = currentPage.minus(INITIAL_PAGE)
        _itemOffset.value = _itemOffset.value?.minus(ITEM_LIST_LIMIT)
    }

    private fun loadNextPage(){
        newPage = currentPage.plus(INITIAL_PAGE)
        _itemOffset.value = _itemOffset.value?.plus(ITEM_LIST_LIMIT)
    }

    companion object {

        private const val INITIAL_PAGE = 1

        private const val ITEM_OFFSET = 0

        private const val ITEM_LIST_LIMIT = 20

        private const val CURRENCY_SIGN = "$"

    }
}