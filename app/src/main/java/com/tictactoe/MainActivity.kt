package com.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.core.content.ContextCompat
import com.tictactoe.databinding.ActivityMainBinding
import java.util.ArrayDeque

class MainActivity : ComponentActivity() {
    private var flag = true
    private var playerXWinCount = 0
    private var playerOWinCount = 0
    private lateinit var binding: ActivityMainBinding
    private val buttonQueue = ArrayDeque<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.playerOLabel.alpha = 0.5f
        binding.playerXWinCount.text = "0"
        binding.playerOWinCount.text = "0"
    }

    fun check(view: View) {
        val currBtn = view as Button
        if (currBtn.text != "") {
            return
        }

        if (flag) {
            currBtn.text = "X"
            currBtn.setTextColor(ContextCompat.getColor(this, R.color.red))
            binding.playerXLabel.alpha = 0.5f
            binding.playerOLabel.alpha = 1f
        } else {
            currBtn.text = "O"
            currBtn.setTextColor(ContextCompat.getColor(this, R.color.blue))
            binding.playerXLabel.alpha = 1f
            binding.playerOLabel.alpha = 0.5f
        }
        buttonQueue.add(currBtn)
        flag = !flag

        val b1 = binding.btn1
        val b2 = binding.btn2
        val b3 = binding.btn3
        val b4 = binding.btn4
        val b5 = binding.btn5
        val b6 = binding.btn6
        val b7 = binding.btn7
        val b8 = binding.btn8
        val b9 = binding.btn9

        if (isWinner(b1, b2, b3)) {
            handleWinner(b1)
        } else if (isWinner(b4, b5, b6)) {
            handleWinner(b4)
        } else if (isWinner(b7, b8, b9)) {
            handleWinner(b7)
        } else if (isWinner(b1, b4, b7)) {
            handleWinner(b1)
        } else if (isWinner(b2, b5, b8)) {
            handleWinner(b2)
        } else if (isWinner(b3, b6, b9)) {
            handleWinner(b3)
        } else if (isWinner(b1, b5, b9)) {
            handleWinner(b3)
        } else if (isWinner(b3, b5, b7)) {
            handleWinner(b3)
        }

        if (buttonQueue.size > 6) {
            val poppedButton = buttonQueue.poll()
            if (poppedButton != null) {
                changeTextColorAlpha(poppedButton, 255)
                poppedButton.text = ""
            }
        }
        if (buttonQueue.size > 5) {
            val lastButton = buttonQueue.first()
            changeTextColorAlpha(lastButton, 100)
        }
    }

    private fun isWinner(btn1: Button, btn2: Button, btn3: Button): Boolean {
        val queueSize = buttonQueue.size
        val lastButton = buttonQueue.first()
        if (queueSize > 5 && lastButton == btn1 || lastButton == btn2 || lastButton == btn3) {
            return false
        }

        val b1 = btn1.text.toString()
        val b2 = btn2.text.toString()
        val b3 = btn3.text.toString()
        return b1 != "" && b1 == b2 && b2 == b3
    }

    private fun handleWinner(winnerBtn: Button) {
        val btnText = winnerBtn.text.toString()
        if (btnText == "X") {
            playerXWinCount++
            binding.playerXWinCount.text = "$playerXWinCount"
        } else {
            playerOWinCount++
            binding.playerOWinCount.text = "$playerOWinCount"
        }
        Toast.makeText(this@MainActivity, "Winner is $btnText", Toast.LENGTH_LONG).show()
        newGame()
    }

    private fun newGame() {
        while (buttonQueue.isNotEmpty()) {
            val poppedButton = buttonQueue.poll()
            if (poppedButton != null) {
                changeTextColorAlpha(poppedButton, 255)
                poppedButton.text = ""
            }
        }
    }

    private fun changeTextColorAlpha(btn: Button, alpha: Int) {
        val currentColor = btn.currentTextColor
        val newColor = Color.argb(
            alpha,
            Color.red(currentColor),
            Color.green(currentColor),
            Color.blue(currentColor)
        )
        btn.setTextColor(newColor)
    }
}