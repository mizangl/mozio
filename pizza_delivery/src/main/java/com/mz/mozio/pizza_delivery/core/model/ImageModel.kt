package com.mz.mozio.pizza_delivery.core.model

import android.os.Parcelable
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageModel(
    @DrawableRes val image: Int,
    @StringRes val contentDescription: Int,
) : Parcelable
