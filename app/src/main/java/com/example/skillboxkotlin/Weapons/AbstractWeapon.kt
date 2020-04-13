package com.example.skillboxkotlin.Weapons

import com.example.skillboxkotlin.NoAmmoException

abstract class AbstractWeapon(
    private val maxMagazineCapacity: Int,
    val fireType: FireType
) {

    private var currentListOfAmmo: MutableList<Ammo> = mutableListOf()

    val isHaveAmmo: Boolean
        get() = currentListOfAmmo.isNotEmpty()

    abstract fun createAmmo(): Ammo

    fun recharge() {
        val newListOfAmmo = mutableListOf<Ammo>()
        val range = 1..maxMagazineCapacity
        for (currentAmmo in range) {
            newListOfAmmo.add(createAmmo())
        }
        currentListOfAmmo = newListOfAmmo
    }

    fun gettingAmmo() {
        if (fireType is FireType.SingleType) {
            if (isHaveAmmo) {
                currentListOfAmmo.removeAt(currentListOfAmmo.lastIndex)
            } else throw NoAmmoException()
        } else {
            for (currentElem in 1..FireType.BurstsType.shotsAmount) {
                if (isHaveAmmo) {
                    currentListOfAmmo.removeAt(currentListOfAmmo.lastIndex)
                } else throw NoAmmoException()
            }
        }
    }
}
