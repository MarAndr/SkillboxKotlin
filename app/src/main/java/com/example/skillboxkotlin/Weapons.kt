package com.example.skillboxkotlin

object Weapons {
    object TraumaticGun : AbstractWeapon(6, FireType.SingleType) {
        override fun createAmmo(): Ammo {
            return Ammo.TRAUMATIC_BULLET
        }
    }

    object Gun : AbstractWeapon(26, FireType.SingleType) {
        override fun createAmmo(): Ammo {
            return Ammo.ORDINARY_BULLET
        }
    }

    object Automatic : AbstractWeapon(100, FireType.BurstsType) {
        override fun createAmmo(): Ammo {
            return Ammo.ORDINARY_BULLET
        }

    }

    object Bazooka : AbstractWeapon(1, FireType.SingleType) {
        override fun createAmmo(): Ammo {
            return Ammo.ARMOR_PIRCING_MISSILE
        }

    }
}