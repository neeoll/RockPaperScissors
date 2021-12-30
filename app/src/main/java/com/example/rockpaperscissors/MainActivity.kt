package com.example.rockpaperscissors

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rockpaperscissors.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), GameFragment.RPSListener, ResultFragment.ScoreManager {

    private lateinit var binding: ActivityMainBinding
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        val gameFragment = GameFragment(this)
        transaction.replace(R.id.fragment_container, gameFragment).commit()

        binding.rules.setOnClickListener {
            inflateRulesWindow(it)
        }
    }

    private fun inflateRulesWindow(view: View?) {

        // inflate the layout of the popup window
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView: View = inflater.inflate(R.layout.rule_window, null)

        // create the popup window
        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.MATCH_PARENT
        val focusable = true // lets taps outside the popup also dismiss it
        val popupWindow = PopupWindow(popupView, width, height, focusable)

        // show the popup window
        // which view you pass in doesn't matter, it is only used for the window token
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0)

        // dismiss the popup window when touched
        popupView.setOnTouchListener { _, _ ->
            popupWindow.dismiss()
            true
        }
    }

    override fun checkWinner(playerThrow: String, aiThrow: String, result: String) {
        val transaction = supportFragmentManager.beginTransaction()
        val resultFragment = ResultFragment(playerThrow, aiThrow, result, this)
        transaction.replace(R.id.fragment_container, resultFragment).commit()
    }

    override fun updateScore(value: Int) {
        score = 0.coerceAtLeast((score + value))
        binding.score.text = this.score.toString()
    }

    override fun playAgain() {
        val transaction = supportFragmentManager.beginTransaction()
        val gameFragment = GameFragment(this)
        transaction.replace(R.id.fragment_container, gameFragment).commit()
    }
}