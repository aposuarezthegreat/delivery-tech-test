package apo.suarez.deliverytech.ui.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import apo.suarez.deliverytech.R
import apo.suarez.deliverytech.utils.DownloadImageTask
import com.airbnb.epoxy.AfterPropsSet
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class DeliveryDetailsProductInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    init {
        View.inflate(context, R.layout.item_layout_delivery_details_product_info, this)
    }

    private var productInfoImage: ImageView = findViewById(R.id.productImageView)

    private lateinit var productImageSrc: CharSequence

    @TextProp
    fun setProductImageSrc( productImageSrc: CharSequence) {
        this.productImageSrc = productImageSrc
    }

    @AfterPropsSet
    fun useProps() {
        DownloadImageTask(productInfoImage).execute(productImageSrc.toString())
    }

}