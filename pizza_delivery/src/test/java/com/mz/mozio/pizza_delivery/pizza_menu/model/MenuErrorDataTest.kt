package com.mz.mozio.pizza_delivery.pizza_menu.model

import org.junit.Assert
import org.junit.Test

class MenuErrorDataTest {

    @Test
    fun `test constructor`() {
        val error = MenuErrorData()

        Assert.assertNotNull(error.message)
        Assert.assertNotNull(error.retry)
        Assert.assertNotNull(error.imageModel.image)
        Assert.assertNotNull(error.imageModel.contentDescription)
    }
}
