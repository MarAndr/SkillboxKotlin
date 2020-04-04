package com.example.skillboxkotlin


fun main() {

    filterList(arrayListOf(1.2, 2.2, 4.3, 55.55, 100.0, 0.5, 0.2444)).forEach { println(it) }
    filterList(arrayListOf(5, 55, 2, 12, 100, 0, 444)).forEach { println(it) }


}

fun <T : Number> filterList(list: List<T>): List<T> {

    val newList = mutableListOf<T>()
    for (item in 0..list.size) {
        if (item % 2 == 0) {
            newList.add(list[item])
        }
    }
    return newList
}