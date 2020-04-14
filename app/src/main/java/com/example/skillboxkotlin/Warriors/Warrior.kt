package com.example.skillboxkotlin.Warriors

interface Warrior {
    val isKilled: Boolean
    val avoidanceChance: Int

    fun attack(warrior: Warrior)
    fun takeDamage(damage: Int)
}
