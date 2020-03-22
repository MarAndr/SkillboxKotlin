package com.example.skillboxkotlin.Warriors

import com.example.skillboxkotlin.Weapons.AbstractWeapon
import com.example.skillboxkotlin.Weapons.Weapons

class SkilledWarrior(
    maxHP: Int = 200, avoidanceChance: Int = 45,
    accuracy: Int = 60, weapon: AbstractWeapon = Weapons.Automatic

) : AbstractWarrior(maxHP, avoidanceChance, accuracy, weapon) {

    override var hP: Int = this.maxHP

    override fun toString(): String {
        return "SkilledWarrior (maxHP = $maxHP, avoidanceChance = $avoidanceChance, " +
                "accuracy = $accuracy, weapon = $weapon)"
    }
}