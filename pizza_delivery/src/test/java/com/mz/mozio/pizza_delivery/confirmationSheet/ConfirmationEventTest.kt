package com.mz.mozio.pizza_delivery.confirmationSheet

import org.junit.Assert
import org.junit.Test

class ConfirmationEventTest {

    @Test
    fun `test OnConfirmClicked event`() {
        val event = ConfirmationEvent.OnConfirmClicked

        Assert.assertNotNull(event)
    }
}
