package com.example.skillboxkotlin.Warriors

import com.example.skillboxkotlin.Weapons.AbstractWeapon
import kotlin.math.roundToInt

abstract class AbstractWarrior(
    val maxHealthLevel : Int, override val avoidanceChance: Int,
    val accuracy : Int, val weapon: AbstractWeapon
                               ) : Warrior {

    override val isKilled: Boolean
        get() {
            return currentHealthLevel <= 0
        }

    open var currentHealthLevel : Int = maxHealthLevel

    override fun attack(enemy: Warrior) {
        if (!weapon.isHaveAmmo){
            weapon.recharge()
        } else{
            weapon.gettingAmmo()
            if (!((100 * Math.random()).roundToInt() > accuracy && (100 * Math.random()).roundToInt() > enemy.avoidanceChance)){
                val totalDamage = weapon.createAmmo().getCurrentDamage()
                enemy.takeDamage(totalDamage)
            }
        }
    }

    override fun takeDamage(damage: Int) {
        currentHealthLevel -= damage
    }

}