package com.example.skillboxkotlin.Warriors

import com.example.skillboxkotlin.Weapons.AbstractWeapon
import com.example.skillboxkotlin.Weapons.Weapons

class SkilledWarrior(maxHealthLevel: Int = 200, avoidanceChance: Int = 45,
                     accuracy: Int = 60, weapon: AbstractWeapon = Weapons.Automatic

) : AbstractWarrior(maxHealthLevel,avoidanceChance, accuracy, weapon ) {

    override fun toString(): String {
        return "SkilledWarrior (maxHealthLevel = $maxHealthLevel, avoidanceChance = $avoidanceChance, accuracy = $accuracy, weapon = $weapon)"
    }
}