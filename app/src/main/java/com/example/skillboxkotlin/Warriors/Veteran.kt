package com.example.skillboxkotlin.Warriors

import com.example.skillboxkotlin.Weapons.AbstractWeapon
import com.example.skillboxkotlin.Weapons.Weapons

class Veteran(
    maxHP: Int = 300, avoidanceChance: Int = 70,
    accuracy: Int = 85, weapon: AbstractWeapon = Weapons.Bazooka

) : AbstractWarrior(maxHP, avoidanceChance, accuracy, weapon) {

    override var hP: Int = maxHP

    override fun toString(): String {
        return "Veteran (maxHP = $maxHP, avoidanceChance = $avoidanceChance," +
                " accuracy = $accuracy, weapon = $weapon)"
    }
}