package com.example.skillboxkotlin

import kotlin.math.abs

fun main() {


    print("Введите положительное число: ")


    while (true) {
        val number = readLine()?.toIntOrNull()
        if (number !is Int || number < 0) {
            println("Вы не ввели число или ваше число отрицательное. Повторите попытку")
            continue
        } else {
            val list = listCreation(number)
            print("Количество положительных чисел, среди введённых данных: ${positiveNumCount(list)}")
            println()
            print("Чётные числа среди введённых данных: ")
            evenNumSearch(list)
            val set = setOf<Int>().union(list)
            println()
            println("Количество уникальных введённых чисел: ${set.size}")
            for (currentNum in list) {
                println(
                    "Число <$currentNum>, cумма введённых чисел <${sumOfListElements(list)}>, НОД <${calculateNod(
                        currentNum,
                        sumOfListElements(list)
                    )}>"
                )
            }
            break
        }
    }

}

fun listCreation(n: Int): MutableList<Int> {
    val list: MutableList<Int> = mutableListOf()
    val range = 0 until n

    for (currentElement in range) {
        while (true) {
            val personalNum = readLine()?.toIntOrNull()
            if (personalNum !is Int) {
                println("Вы не ввели число. Повторите попытку")
                continue
            } else list.add(personalNum)
            break
        }
    }

    return list
}

fun evenNumSearch(list: MutableList<Int>) {
    for (currentNum in list) {
        if (currentNum % 2 == 0) {
            print("$currentNum ")
        } else {
            continue
        }
    }
}

fun positiveNumCount(list: MutableList<Int>): Int {
    var counter = 0
    for (currentNum in list) {
        if (currentNum > 0) counter++
    }
    return counter
}

fun sumOfListElements(list: MutableList<Int>): Int {
    var sum = 0
    for (currentNum in list) {
        sum += currentNum
    }
    return sum
}

tailrec fun calculateNod(a: Int, b: Int): Int {
    return if (abs(a) != abs(b)) {
        if (abs(a) > abs(b)) {
            calculateNod(abs(a) - abs(b), abs(b))
        } else {
            calculateNod(a, abs(b) - abs(a))
        }

    } else {
        abs(a)
    }
}
