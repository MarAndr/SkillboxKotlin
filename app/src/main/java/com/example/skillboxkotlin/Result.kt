package com.example.skillboxkotlin

fun main() {
    val return1 = returnObject()
    val return2: Result<Number, String> = returnObject()
    val return3: Result<Any, String> = returnObject()
    val return4: Result<Int, CharSequence> = returnObject()
    val return5: Result<Int, Any> = returnObject()
}

sealed class Result<out T, R> {
    data class Success<T, R>(val success: T) : Result<T, R>()
    data class Error<T, R>(val error: R) : Result<T, R>()

    override fun toString(): String {
        return "Result()"
    }


}

fun returnObject(): Result<Int, String> {
    return Result.Error("")
}
