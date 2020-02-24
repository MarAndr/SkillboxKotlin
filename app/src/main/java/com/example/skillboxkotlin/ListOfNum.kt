package com.example.skillboxkotlin

fun main() {

    var count = 0

    print("Введите число: ")
    val n = readLine()?.toIntOrNull()

    val list = listOfNumbers(n)

    for (numElement in list) {
        if (numElement < 0) continue
        count += 1
    }
    val set = setOf<Int>().union(list)



    println("Количество уникальных введённых чисел: ${set.size}")
    println("Количество положительных чисел: $count")
    println("Чётные числа среди введённых данных: ")

    for (numElement in list) {
        if (numElement % 2 == 1) continue
        println("$numElement ")
    }

    println("Cумма введённых чисел: ${listElementsSum(list)}")
    for (numElement in list) {
        println(
            "Число <$numElement>, Сумма <${listElementsSum(list)}>, НОД <${calculateNod(
                numElement,
                listElementsSum(list)
            )}>"
        )
    }


}


fun listOfNumbers(n: Int?): MutableList<Int> {
    val list = mutableListOf<Int>()
    val range = 1..n!!
    for (current in range) {
        val nums = readLine()?.toIntOrNull()
        list.add(nums!!)
    }
    return list
}

fun listElementsSum(list: List<Int>): Int {
    var sum = 0
    for (currentNum in list) {
        sum += currentNum
    }
    return sum
}


tailrec fun calculateNod(a: Int, b: Int): Int {
    return if (a != b) {
        if (a > b) {
            calculateNod(a - b, b)
        } else {
            calculateNod(a, b - a)
        }

    } else {
        a
    }
}
