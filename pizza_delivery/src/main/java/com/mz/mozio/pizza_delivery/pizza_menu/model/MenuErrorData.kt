package com.mz.mozio.pizza_delivery.pizza_menu.model

import androidx.annotation.StringRes
import com.mz.mozio.pizza_delivery.R
import com.mz.mozio.pizza_delivery.core.model.ImageModel

data class MenuErrorData(
    @StringRes val message: Int = R.string.default_error_message,
    @StringRes val retry: Int = R.string.default_error_retry,
    val imageModel: ImageModel = ImageModel(
        image = R.drawable.round_error_outline_24,
        contentDescription = R.string.default_error_message
    )
)
