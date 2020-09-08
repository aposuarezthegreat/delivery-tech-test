package apo.suarez.deliverytech.ui.details

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import apo.suarez.deliverytech.R
import apo.suarez.deliverytech.data.networkcalls.Model
import apo.suarez.deliverytech.ui.views.deliveryDetailsProductInfoView
import apo.suarez.deliverytech.ui.views.deliveryDetailsRouteView
import apo.suarez.deliverytech.ui.views.deliveryDetailsSectionView
import com.airbnb.epoxy.EpoxyController
import kotlinx.android.synthetic.main.fragment_delivery_details.*

class DeliveryDetailsFragment : Fragment(R.layout.fragment_delivery_details){

    private val deliveryDetailsFragmentArgs by navArgs<DeliveryDetailsFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val deliveryDetailsController = DeliveryDetailsController()

        deliveryDetailsRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            setController(deliveryDetailsController)
        }

        deliveryDetailsController.apply {
            deliveryItem = deliveryDetailsFragmentArgs.deliveryItem
            requestModelBuild()
        }

        previousPageImageView.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    inner class DeliveryDetailsController : EpoxyController(){

        var deliveryItem: DeliveryDetails? = null

        override fun buildModels() {

            deliveryItem?.let {

                deliveryDetailsRouteView {
                    id(getString(R.string.id_delivery_details_route))
                    startLocation(getString(R.string.label_start_destination,it.startRoute))
                    endLocation(getString(R.string.label_end_destination,it.endRoute))
                }

                deliveryDetailsProductInfoView {
                    id(getString(R.string.id_delivery_details_product_info))
                    productImageSrc(it.itemImage)
                }

                deliveryDetailsSectionView {
                    id(getString(R.string.id_delivery_details_section))
                    deliveryFee(it.deliveryFee)
                }

            }
        }

    }
}