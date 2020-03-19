package com.example.skillboxkotlin.Weapons

sealed class FireType {
    object SingleType : FireType()
    object BurstsType : FireType()
}