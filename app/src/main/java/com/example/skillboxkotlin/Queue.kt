package com.example.skillboxkotlin

fun main() {

    val queue = Queue<String>()
    queue.enqueue("Andrey")
    queue.enqueue("Anatoliy")
    queue.enqueue("Bobby")
    println(queue.queueList)
    queue.dequeue()
    println(queue.queueList)
    queue.enqueue("Slava")
    println(queue.queueList)

}

class Queue<T> {

    var queueList = mutableListOf<T>()
        private set

    fun enqueue(item: T) {
        queueList.add(item)
    }

    fun dequeue(): T? {

        return if (queueList.isEmpty())
            null
        else
            queueList.removeAt(0)
    }
}

