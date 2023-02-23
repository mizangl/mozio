import kotlin.math.max

fun exchangeKotlinCollection(
    janeStamps: List<Int>,
    aliceStamps: List<Int>,
    spare: Int = 2
): List<List<Int>> {

    val janeDuplicates = janeStamps.groupBy { it }

    val aliceDuplicates = aliceStamps.groupBy { it }

    val toAlice = janeDuplicates
        .filter { it.value.size > spare }
        .filter { (aliceDuplicates[it.key]?.size ?: 0) < spare }
        .map {
            it.value.take(it.value.size - spare)
        }.flatten()

    val toJane = aliceDuplicates
        .filter { it.value.size > spare }
        .filter { (janeDuplicates[it.key]?.size ?: 0) < spare }
        .map {
            it.value.take(it.value.size - spare)
        }.flatten()

    return listOf(toJane, toAlice)
}

fun exchangeWithMapList(
    janeStamps: List<Int>,
    aliceStamps: List<Int>,
    spare: Int = 2
): List<MutableList<Int>> {

    fun compareAndExchange(array1: List<Int>, array2: List<Int>): List<MutableList<Int>> {
        val firstExchange = mutableListOf<Int>()
        val secondExchange = mutableListOf<Int>()

        for (index in array1.indices) {
            if (array2[index] < spare && array1[index] > spare) {
                repeat(array1[index] - spare) {
                    firstExchange.add(index)
                }
            } else if (array2[index] > spare && array1[index] < spare) {
                repeat(array2[index] - spare) {
                    secondExchange.add(index)
                }
            }
        }

        return listOf(secondExchange, firstExchange)
    }

    val (jane, alice) = toFrequencyList(janeStamps, aliceStamps)

    return compareAndExchange(jane, alice)
}

fun exchangeWithBacktracking(
    janeStamps: List<Int>,
    aliceStamps: List<Int>,
    spare: Int = 2
): List<List<Int>> {

    val result = mutableListOf<List<Int>>()
    val (jane, alice) = toFrequencyList(janeStamps, aliceStamps)

    fun backtrack(
        index: Int,
        firstExchange: MutableList<Int> = mutableListOf<Int>(),
        secondExchange: MutableList<Int> = mutableListOf<Int>()
    ) {

        if (index == alice.size) {
            result.add(secondExchange.toList())
            result.add(firstExchange.toList())
            return
        }

        if (alice[index] < spare && jane[index] > spare) {
            repeat(jane[index] - spare) {
                firstExchange.add(index)
            }
        } else if (alice[index] > spare && jane[index] < spare) {
            repeat(alice[index] - spare) {
                secondExchange.add(index)
            }
        }

        backtrack(index + 1, firstExchange, secondExchange)
        if (firstExchange.isNotEmpty()) firstExchange.removeLast()
        if (secondExchange.isNotEmpty()) secondExchange.removeLast()
    }

    backtrack(0)
    return result
}

private fun toFrequencyList(
    array1: List<Int>,
    array2: List<Int>
): Pair<List<Int>, List<Int>> {
    val size = max(array1.sorted().max(), array2.sorted().max())
    val mapList1 = MutableList(size + 1) { 0 }
    val mapList2 = MutableList(size + 1) { 0 }

    array1.forEach {
        mapList1[it] += 1
    }

    array2.forEach {
        mapList2[it] += 1
    }
    return mapList1 to mapList2
}
