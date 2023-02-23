import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StampTest {

    @Test
    fun testKotlinCollectionCase1() {
        val expected = listOf(listOf(2, 3), listOf(1, 1, 7))
        val exchange = exchangeKotlinCollection(listOf(1, 7, 3, 1, 7, 4, 5, 1, 7, 1), listOf(2, 3, 3, 2, 4, 3, 2))

        Assertions.assertIterableEquals(
            expected,
            exchange
        )
    }

    @Test
    fun testKotlinCollectionCase2() {
        val expected = listOf<List<Int>>(emptyList(), emptyList())
        val exchange = exchangeKotlinCollection(listOf(1, 2, 3, 4, 4), listOf(4, 4, 4, 5, 6, 7))

        Assertions.assertIterableEquals(
            expected,
            exchange
        )
    }

    @Test
    fun testKotlinCollectionCase3() {
        val expected = listOf(listOf(), listOf(3, 3))
        val exchange = exchangeKotlinCollection(listOf(5, 4, 4, 3, 3, 3, 3), listOf(1, 3))

        Assertions.assertIterableEquals(
            expected,
            exchange
        )
    }

    @Test
    fun testExchangeArrayCase1() {
        val expected = listOf(listOf(2, 3), listOf(1, 1, 7))
        val exchange = exchangeWithMapList(listOf(1, 7, 3, 1, 7, 4, 5, 1, 7, 1), listOf(2, 3, 3, 2, 4, 3, 2))

        Assertions.assertIterableEquals(
            expected,
            exchange
        )
    }

    @Test
    fun testExchangeArrayCase2() {
        val expected = listOf<List<Int>>(emptyList(), emptyList())
        val exchange = exchangeWithMapList(listOf(1, 2, 3, 4, 4), listOf(4, 4, 4, 5, 6, 7))

        Assertions.assertIterableEquals(
            expected,
            exchange
        )
    }

    @Test
    fun testExchangeArrayCase3() {
        val expected = listOf(listOf(), listOf(3, 3))
        val exchange = exchangeWithMapList(listOf(5, 4, 4, 3, 3, 3, 3), listOf(1, 3))

        Assertions.assertIterableEquals(
            expected,
            exchange
        )
    }

    @Test
    fun testExchangeBacktrackCase1() {
        val expected = listOf(listOf(2, 3), listOf(1, 1, 7))
        val exchange = exchangeWithBacktracking(listOf(1, 7, 3, 1, 7, 4, 5, 1, 7, 1), listOf(2, 3, 3, 2, 4, 3, 2))

        Assertions.assertIterableEquals(
            expected,
            exchange
        )
    }

    @Test
    fun testExchangeBacktrackCase2() {
        val expected = listOf<List<Int>>(emptyList(), emptyList())
        val exchange = exchangeWithBacktracking(listOf(1, 2, 3, 4, 4), listOf(4, 4, 4, 5, 6, 7))

        Assertions.assertIterableEquals(
            expected,
            exchange
        )
    }

    @Test
    fun testExchangeBacktrackCase3() {
        val expected = listOf(listOf(), listOf(3, 3))
        val exchange = exchangeWithBacktracking(listOf(5, 4, 4, 3, 3, 3, 3), listOf(1, 3))

        Assertions.assertIterableEquals(
            expected,
            exchange
        )
    }
}