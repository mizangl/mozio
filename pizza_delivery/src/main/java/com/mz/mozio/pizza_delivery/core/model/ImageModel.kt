package com.mz.mozio.pizza_delivery.core.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class ImageModel(
    @DrawableRes val image: Int,
    @StringRes val contentDescription: Int,
)
