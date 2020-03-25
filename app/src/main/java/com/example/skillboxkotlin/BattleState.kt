package com.example.skillboxkotlin

sealed class BattleState {

    object Progress : BattleState(){
        override fun toString(): String {
            return "Progress ( team1HP = ${Battle().sumHPteam1}, team2HP = ${Battle().sumHPteam2} )"
        }
    }
    object TeamOneWon : BattleState(){
        override fun toString(): String {
            return "Team1 won"
        }
    }
    object TeamTwoWon : BattleState() {
        override fun toString(): String {
            return "Team2 won"
        }
    }
    object Draw : BattleState(){
        override fun toString(): String {
            return "Draw"
        }
    }
}