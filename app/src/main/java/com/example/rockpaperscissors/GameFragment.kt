package com.example.rockpaperscissors

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.rockpaperscissors.databinding.GameFragmentBinding

class GameFragment(private val listener: RPSListener) : Fragment() {
    private lateinit var binding: GameFragmentBinding

    interface RPSListener {
        fun checkWinner(playerThrow: String, aiThrow: String, result: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.game_fragment, container, false)

        binding.rock.setOnClickListener {
            when ((0..3).random()) {
                0 -> { listener.checkWinner("rock", "rock", "draw") }
                1 -> { listener.checkWinner("rock", "paper", "lose") }
                2 -> { listener.checkWinner("rock", "scissors", "win") }
            }
        }
        binding.paper.setOnClickListener {
            when ((0..3).random()) {
                0 -> { listener.checkWinner("paper", "rock", "win") }
                1 -> { listener.checkWinner("paper", "paper", "draw") }
                2 -> { listener.checkWinner("paper", "scissors", "lose") }
            }
        }
        binding.scissors.setOnClickListener {
            when ((0..3).random()) {
                0 -> { listener.checkWinner("scissors", "rock", "lose") }
                1 -> { listener.checkWinner("scissors", "paper", "win") }
                2 -> { listener.checkWinner("scissors", "scissors", "draw") }
            }
        }

        return binding.root
    }
}