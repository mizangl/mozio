package com.mz.mozio.pizza_delivery.pizza_menu.model

import android.os.Parcelable
import androidx.annotation.StringRes
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.core.model.ImageModel
import kotlinx.parcelize.Parcelize

@Parcelize
data class PizzaModel(
    val name: String,
    val price: Double,
    @StringRes val formatCurrency: Int = R.string.default_pizza_currency,
    val imageModel: ImageModel = ImageModel(
        image = R.drawable.local_pizza_24,
        contentDescription = R.string.default_pizza_accessibility
    )
) : Parcelable {

    val halfPrice: Double
        get() = price / 2
}
