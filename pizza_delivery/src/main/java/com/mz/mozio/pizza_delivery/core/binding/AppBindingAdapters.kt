package com.mz.mozio.pizza_delivery.core.binding

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mz.mozio.pizza_delivery.core.Utils

@BindingAdapter("isVisible")
fun View.isVisible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

@BindingAdapter("textRes")
fun TextView.setText(textRes: Int?) {
    if (textRes == null || textRes == 0) {
        visibility = View.GONE
        return
    }
    visibility = View.VISIBLE
    text = context.getString(textRes)
}

@BindingAdapter("textString")
fun TextView.setString(textString: String?) {
    if (textString == null) {
        visibility = View.GONE
        return
    }
    visibility = View.VISIBLE
    text = textString
}

@BindingAdapter(
    "textPrice",
    "formatCurrency"
)
fun TextView.formatString(
    textString: Double?,
    formatCurrency: Int?
) {
    if (textString == null || formatCurrency == null || formatCurrency == 0) {
        visibility = View.GONE
        return
    }
    visibility = View.VISIBLE
    val format = Utils.decimalFormat.format(textString)
    text = context.getString(formatCurrency, format)
}

@BindingAdapter("image")
fun ImageView.setImage(
    image: Int?,
) {
    if (image == null || image == 0) {
        visibility = View.GONE
        return
    }

    visibility = View.VISIBLE
    setImageResource(image)
}

@BindingAdapter("accessibility")
fun ImageView.setContentDescription(
    accessibility: Int?,
) {
    if (accessibility == null || accessibility == 0) {
        return
    }
    contentDescription = context.getString(accessibility)
}
