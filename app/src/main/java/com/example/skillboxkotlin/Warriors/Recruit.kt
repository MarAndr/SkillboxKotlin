package com.example.skillboxkotlin.Warriors

import com.example.skillboxkotlin.Weapons.AbstractWeapon
import com.example.skillboxkotlin.Weapons.Weapons

class Recruit(maxHealthLevel: Int = 100, avoidanceChance: Int = 20,
              accuracy: Int = 30, weapon: AbstractWeapon = Weapons.TraumaticGun

) : AbstractWarrior(maxHealthLevel,avoidanceChance, accuracy, weapon ) {

    override fun toString(): String {
        return "Recruit (maxHealthLevel = $maxHealthLevel, avoidanceChance = $avoidanceChance, accuracy = $accuracy, weapon = $weapon)"
    }
}