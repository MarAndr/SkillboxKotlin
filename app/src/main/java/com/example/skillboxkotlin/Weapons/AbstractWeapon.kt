package com.example.skillboxkotlin.Weapons

abstract class AbstractWeapon (private val maxMagazineCapacity : Int, private val fireType: FireType) {

    private var currentListOfAmmo : MutableList<Ammo> = mutableListOf()

     val isHaveAmmo : Boolean
    get() {return currentListOfAmmo.isNotEmpty()}

    abstract fun createAmmo() : Ammo


    fun recharge (){
        val newListOfAmmo = mutableListOf<Ammo>()
        val range = 1 .. maxMagazineCapacity
        for (currentAmmo in range){
            newListOfAmmo.add(createAmmo())
        }
        currentListOfAmmo = newListOfAmmo
    }

    fun gettingAmmo(){
        if (fireType is FireType.SingleType){
            if (isHaveAmmo){
                currentListOfAmmo.removeAt(currentListOfAmmo.lastIndex)
                println("Вы использовали один патрон. В магазине осталось ${currentListOfAmmo.size}")
            }
            else println("У вас закончились патроны. Перезарядите магазин!")
        } else {
            for (currentElem in 1..10){
                if (isHaveAmmo){
                    currentListOfAmmo.removeAt(currentListOfAmmo.lastIndex)
                    println("Вы использовали 10 патронов. В магазине осталось ${currentListOfAmmo.size}")
                }
                else println("У вас закончились патроны. Перезарядите магазин!")
            }
        }

    }




}