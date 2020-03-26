package com.example.skillboxkotlin.Warriors

import com.example.skillboxkotlin.Weapons.AbstractWeapon
import com.example.skillboxkotlin.Weapons.FireType
import kotlin.math.roundToInt

abstract class AbstractWarrior(
    val maxHP: Int, override val avoidanceChance: Int,
    val accuracy: Int, val weapon: AbstractWeapon
) : Warrior {

    override val isKilled: Boolean
        get() {
            return hP <= 0
        }

    open var hP: Int = maxHP

    override fun attack(enemy: Warrior) {
        if (!weapon.isHaveAmmo) {
            weapon.recharge()
        } else {
            if (weapon.fireType is FireType.SingleType) {
                weapon.gettingAmmo()
                if (!((100 * Math.random()).roundToInt() > accuracy &&
                            (100 * Math.random()).roundToInt() > enemy.avoidanceChance)
                ) {
                    val totalDamage = weapon.createAmmo().getCurrentDamage()
                    enemy.takeDamage(totalDamage)
                }
            } else {
                weapon.gettingAmmo()
                var totalDamageSum = 0
                for (currentElem in 1..FireType.BurstsType.shotsAmount) {
                    if (!((100 * Math.random()).roundToInt() > accuracy &&
                                (100 * Math.random()).roundToInt() > enemy.avoidanceChance)
                    ) {
                        val totalDamage = weapon.createAmmo().getCurrentDamage()
                        totalDamageSum += totalDamage

                    }
                }

                enemy.takeDamage(totalDamageSum)

            }
        }
    }

    override fun takeDamage(damage: Int) {
        if ((hP - damage) > 0)
            hP -= damage
        else
            hP = 0
    }

}