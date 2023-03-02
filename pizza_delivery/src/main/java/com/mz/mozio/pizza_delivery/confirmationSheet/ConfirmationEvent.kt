package com.mz.mozio.pizza_delivery.confirmationSheet

sealed class ConfirmationEvent {
    object OnConfirmClicked: ConfirmationEvent()
}
