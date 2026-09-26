package com.russia.game.gui

import com.russia.game.R
import com.russia.game.core.Samp
import com.russia.game.databinding.WarPointsBinding

class WarPoints : NativeGui<WarPointsBinding>(WarPointsBinding::class) {


    fun update(time: Int, myPoints: Int, enemyScore: Int) {
        Samp.activity.runOnUiThread {
            binding.myPointsText.text       = String.format("Your points: %d", myPoints)
            binding.enemyPointsText.text    = String.format("Enemy points: %d", enemyScore)

            binding.timeText.text = String.format("%d seconds", time)
        }
    }

    override fun receivePacket(actionId: Int, data: String) {
        TODO("Not yet implemented")
    }
}