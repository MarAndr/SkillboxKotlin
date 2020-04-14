package com.example.skillboxkotlin.Weapons

sealed class FireType {
    object SingleType : FireType() {
        val shotsAmount = 1
    }
    object BurstsType : FireType() {
        val shotsAmount = 10
    }
}
