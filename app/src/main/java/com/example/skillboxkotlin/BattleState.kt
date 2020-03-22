package com.example.skillboxkotlin

sealed class BattleState {
    class Progress : BattleState()
    class TeamOneWon : BattleState()
    class TeamTwoWon : BattleState()
    class Draw : BattleState()
}