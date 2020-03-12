package com.example.skillboxkotlin

sealed class FireType {
    object SingleType : FireType()
    object BurstsType : FireType()
}