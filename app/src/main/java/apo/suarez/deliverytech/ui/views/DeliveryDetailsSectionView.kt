package apo.suarez.deliverytech.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import apo.suarez.deliverytech.R
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class DeliveryDetailsSectionView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_layout_delivery_details_section, this)
    }

    private var deliveryFeeTextView: TextView = findViewById(R.id.deliveryFeeValueTextView)

    private lateinit var deliveryFee: CharSequence

    @TextProp
    fun setDeliveryFee(deliveryFee: CharSequence) {
        this.deliveryFee = deliveryFee
    }

    @AfterPropsSet
    fun useProps() {
        deliveryFeeTextView.text = deliveryFee
    }

}