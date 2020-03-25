package com.example.skillboxkotlin

import com.example.skillboxkotlin.Team
import com.example.skillboxkotlin.Warriors.Veteran

class Battle(val teamSize: Int) {


    val team1 = Team(teamSize)
    val team2 = Team(teamSize)
    var isBattleFinished: Boolean = false
    val sumHPteam1 = team1.sumHP()
    val sumHPteam2 = team2.sumHP()


    fun getBattleState(): BattleState {

        var numberOfLivingWarriorsTeam1 = 0
        var numberOfLivingWarriorsTeam2 = 0

        for (item1 in team1.teamList) {
            if (item1.isKilled) continue
            numberOfLivingWarriorsTeam1 += 1

        }

        for (item2 in team2.teamList) {
            if (item2.isKilled) continue
            numberOfLivingWarriorsTeam2 += 1

        }

        if (numberOfLivingWarriorsTeam1 != 0 && numberOfLivingWarriorsTeam2 != 0)
            return BattleState.Progress
        else if (numberOfLivingWarriorsTeam1 == 0 && numberOfLivingWarriorsTeam2 != 0) {
            isBattleFinished = true
            return BattleState.TeamTwoWon
        } else if (numberOfLivingWarriorsTeam1 != 0 && numberOfLivingWarriorsTeam2 == 0) {
            isBattleFinished = true
            return BattleState.TeamOneWon
        } else {
            isBattleFinished = true
            return BattleState.Draw
        }
    }


    fun nextIteration() {
        println("new iteration")
        shuffled()
        for (elem in 0 until teamSize) {
            if (!team1.teamList[elem].isKilled && !team2.teamList[elem].isKilled) {
                team1.teamList[elem].attack(team2.teamList[elem])
                team2.teamList[elem].attack(team1.teamList[elem])
            } else continue
        }
    }

    private fun shuffled() {
        team1.teamList.shuffle()
        team2.teamList.shuffle()

    }


}




