package com.example.skillboxkotlin

import com.example.skillboxkotlin.Warriors.*
import kotlin.math.roundToInt

class Team (val teamSize : Int) {

        var teamList = mutableListOf<Warrior>()

        private fun teamConstructor(){
            val range = 1 .. teamSize
            for (item in range){
                when(val chance : Int = (100 * Math.random()).roundToInt()){
                    in 0 .. 50 -> teamList.add(Recruit())
                    in 51 .. 85 -> teamList.add(SkilledWarrior())
                    in 86 .. 100 -> teamList.add(Veteran())
                }
            }
        }
    }





