package com.example.skillboxkotlin.Warriors

import com.example.skillboxkotlin.Weapons.AbstractWeapon
import com.example.skillboxkotlin.Weapons.Weapons

class Veteran(
    maxHealthLevel: Int = 300, avoidanceChance: Int = 70,
    accuracy: Int = 85, weapon: AbstractWeapon = Weapons.Bazooka

) : AbstractWarrior(maxHealthLevel,avoidanceChance, accuracy, weapon ) {

    override var currentHealthLevel : Int = maxHealthLevel

    override fun toString(): String {
        return "Veteran (maxHealthLevel = $maxHealthLevel, avoidanceChance = $avoidanceChance, accuracy = $accuracy, weapon = $weapon)"
    }
}