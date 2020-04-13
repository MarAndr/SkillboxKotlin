package com.example.skillboxkotlin.Warriors

import com.example.skillboxkotlin.Weapons.AbstractWeapon
import com.example.skillboxkotlin.Weapons.Weapons

class Recruit(
    maxHP: Int = 100,
    avoidanceChance: Int = 20,
    accuracy: Int = 30,
    weapon: AbstractWeapon = Weapons.TraumaticGun

) : AbstractWarrior(maxHP, avoidanceChance, accuracy, weapon) {

    override var hP: Int = maxHP

    override fun toString(): String {
        return "Recruit (maxHP = $maxHP, avoidanceChance = $avoidanceChance, " +
                "accuracy = $accuracy, weapon = $weapon)"
    }
}
