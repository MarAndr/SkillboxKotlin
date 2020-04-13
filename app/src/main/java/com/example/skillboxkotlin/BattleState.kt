package com.example.skillboxkotlin

sealed class BattleState {

    class Progress(var hpTeam1: Int, var hpTeam2: Int) : BattleState() {
        override fun toString(): String {
            return "Progress ( team1HP = $hpTeam1, team2HP = $hpTeam2 )"
        }
    }
    object TeamOneWon : BattleState() {
        override fun toString(): String {
            return "Team1 won"
        }
    }
    object TeamTwoWon : BattleState() {
        override fun toString(): String {
            return "Team2 won"
        }
    }
    object Draw : BattleState() {
        override fun toString(): String {
            return "Draw"
        }
    }
}
