package com.example.rockpaperscissors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.rockpaperscissors.databinding.ResultFragmentBinding

class ResultFragment(private val playerThrow: String, private val aiThrow: String, private val result: String, private val listener: ScoreManager): Fragment() {
    private lateinit var binding: ResultFragmentBinding

    interface ScoreManager {
        fun updateScore(value: Int)
        fun playAgain()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.result_fragment, container, false)

        when (playerThrow) {
            "rock" -> {
                binding.playerThrow.setBackgroundResource(R.drawable.icon_border_red)
                binding.playerThrow.setImageResource(R.drawable.icon_rock)
            }
            "paper" -> {
                binding.playerThrow.setBackgroundResource(R.drawable.icon_border_blue)
                binding.playerThrow.setImageResource(R.drawable.icon_paper)
            }
            "scissors" -> {
                binding.playerThrow.setBackgroundResource(R.drawable.icon_border_yellow)
                binding.playerThrow.setImageResource(R.drawable.icon_scissors)
            }
        }

       when (aiThrow) {
            "rock" -> {
                binding.aiThrow.setBackgroundResource(R.drawable.icon_border_red)
                binding.aiThrow.setImageResource(R.drawable.icon_rock)
            }
            "paper" -> {
                binding.aiThrow.setBackgroundResource(R.drawable.icon_border_blue)
                binding.aiThrow.setImageResource(R.drawable.icon_paper)
            }
            "scissors" -> {
                binding.aiThrow.setBackgroundResource(R.drawable.icon_border_yellow)
                binding.aiThrow.setImageResource(R.drawable.icon_scissors)
            }
        }

        when (result) {
            "win" -> {
                binding.status.text = "YOU WIN"
                listener.updateScore(5)
            }
            "lose" -> {
                binding.status.text = "YOU LOSE"
                listener.updateScore(-2)
            }
            "draw" -> {
                binding.status.text = "DRAW"
            }
        }

        binding.playAgain.setOnClickListener {
            listener.playAgain()
        }

        return binding.root
    }
}