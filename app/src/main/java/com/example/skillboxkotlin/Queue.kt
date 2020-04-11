package com.example.skillboxkotlin

import java.util.*

fun main() {

    val queueFirst = Queue<String>()

    queueFirst.enqueue("Andrey")
    queueFirst.enqueue("Anatoliy")
    queueFirst.enqueue("Bobby")
    queueFirst.enqueue("Alex")
    queueFirst.enqueue("John")

    val sortedQueue = queueFirst.filter { x -> x.length > 6 }

    println(sortedQueue)
}

class Queue<T> {

    var queueList = mutableListOf<T>()
        private set

    fun enqueue(item: T) {
        queueList.add(item)
    }

    fun dequeue(): T? {

        if (queueList.isEmpty()) return null
        else return queueList.removeAt(0)
    }

    fun filter(lambda: (T) -> Boolean): Queue<T> {
        val newQueue = Queue<T>()
        val sortedQueueList = this.queueList.filter { lambda(it) }
        newQueue.queueList = sortedQueueList.toMutableList()
        return newQueue
    }

    override fun toString(): String {
        return "Queue(queueList=$queueList)"
    }


}