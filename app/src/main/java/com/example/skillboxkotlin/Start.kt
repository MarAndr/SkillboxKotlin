package com.example.skillboxkotlin

fun main() {
    print("Введите количество воинов в командах: ")
    val teamSize = readLine()?.toIntOrNull()?: 0
    val battle = Battle(teamSize)
    println("Начало битвы")
    while (!battle.isBattleFinished){
        println(battle.getBattleState())
        battle.nextIteration()
    }

}


//Примерное содержание файла:
//
//
//
//Введите количество воинов: 10
//
//Начало битвы
//
//Progress(commandAHealth=1370, commandBHealth=680)
//
//...
//
//Progress(commandAHealth=1100, commandBHealth=50)
//
//Progress(commandAHealth=1100, commandBHealth=50)
//
//Progress(commandAHealth=1100, commandBHealth=50)
//
//Победила первая команда

