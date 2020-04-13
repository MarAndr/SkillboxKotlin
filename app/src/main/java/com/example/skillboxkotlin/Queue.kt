package com.example.skillboxkotlin


fun main() {

    val queueFirst = Queue(mutableListOf("Andrey"))
    queueFirst.enqueue("Anatoliy")
    queueFirst.enqueue("Bobby")
    queueFirst.enqueue("Alex")
    queueFirst.enqueue("John")

    val sortedQueue = queueFirst.filter { x -> x.length > 6 }

    println(sortedQueue)
}

class Queue<T>(list: MutableList<T>) {

    private var queueList = list


    fun enqueue(item: T) {
        queueList.add(item)
    }

    fun dequeue(): T? {

        if (queueList.isEmpty()) return null
        else return queueList.removeAt(0)
    }

    fun filter(lambda: (T) -> Boolean): Queue<T> {
        return Queue(this.queueList.filter { lambda(it) }.toMutableList())
    }


    override fun toString(): String {
        return "Queue(queueList=$queueList)"
    }


}