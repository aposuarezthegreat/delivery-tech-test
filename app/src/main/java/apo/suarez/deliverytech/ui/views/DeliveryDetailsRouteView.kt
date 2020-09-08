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
class DeliveryDetailsRouteView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_layout_delivery_details_route, this)
    }

    private var routeStart: TextView = findViewById(R.id.routeStartTextView)
    private var routeEnd: TextView = findViewById(R.id.routeEndTextView)

    private lateinit var startLocation: CharSequence
    private lateinit var endLocation: CharSequence

    @TextProp
    fun setStartLocation(startLocation: CharSequence) {
        this.startLocation = startLocation
    }

    @TextProp
    fun setEndLocation(endLocation: CharSequence) {
        this.endLocation = endLocation
    }

    @AfterPropsSet
    fun useProps() {
        routeStart.text = startLocation
        routeEnd.text = endLocation
    }

}