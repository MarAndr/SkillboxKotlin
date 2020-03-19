package com.example.skillboxkotlin.Weapons

import com.example.skillboxkotlin.Weapons.AbstractWeapon
import com.example.skillboxkotlin.Weapons.Ammo
import com.example.skillboxkotlin.Weapons.FireType

object Weapons {
    object TraumaticGun : AbstractWeapon(6, FireType.SingleType) {
        override fun createAmmo(): Ammo {
            return Ammo.TRAUMATIC_BULLET
        }

        override fun toString(): String {
            return "TraumaticGun"
        }
    }

    object Gun : AbstractWeapon(26, FireType.SingleType) {
        override fun createAmmo(): Ammo {
            return Ammo.ORDINARY_BULLET
        }

        override fun toString(): String {
            return "Gun"
        }
    }

    object Automatic : AbstractWeapon(100, FireType.BurstsType) {
        override fun createAmmo(): Ammo {
            return Ammo.ORDINARY_BULLET
        }

        override fun toString(): String {
            return "Automatic"
        }

    }

    object Bazooka : AbstractWeapon(1, FireType.SingleType) {
        override fun createAmmo(): Ammo {
            return Ammo.ARMOR_PIRCING_MISSILE
        }

        override fun toString(): String {
            return "Bazooka"
        }

    }
}