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
            if (index > array2.indices.last) {
                if (array1[index] - spare > 0)
                    repeat(array1[index] - spare) {
                        firstExchange.add(index)
                    }
            } else {
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
        }

        return listOf(secondExchange, firstExchange)
    }

    fun toMapList(array: List<Int>): List<Int> {
        val mapList = MutableList(array.sorted().max() + 1) { 0 }
        array.forEach {
            mapList[it] += 1
        }
        return mapList
    }

    val jane = toMapList(janeStamps)
    val alice = toMapList(aliceStamps)

    return if (jane.size > alice.size) compareAndExchange(jane, alice)
    else compareAndExchange(alice, jane)

}
