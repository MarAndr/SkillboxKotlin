package com.example.skillboxkotlin.Weapons

import kotlin.math.roundToInt

enum class Ammo(
    private val damage: Int,
    private val criticalDamageChance: Int, private val coeffCriticDamage: Int
) {

    TRAUMATIC_BULLET(5, 5, 1),
    ORDINARY_BULLET(15, 25, 2),
    ARMOR_PIRCING_MISSILE(20, 40, 5);

    fun getCurrentDamage(): Int {
        return if ((Math.random() * 100).roundToInt() > criticalDamageChance)
            damage
        else
            damage * coeffCriticDamage
    }
}