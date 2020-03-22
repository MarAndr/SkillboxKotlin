package com.example.skillboxkotlin

import com.example.skillboxkotlin.Warriors.AbstractWarrior

fun main() {

    val team1 = Team(5)
    team1.teamList.forEach{ println(it)}
    println(team1.sumHP())

}