package com.tictactoe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.tictactoe.databinding.ActivityMainBinding

class MainActivity : ComponentActivity() {
    private var flag = false
    private var count = 0
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun check(view: View) {
        val currBtn = view as Button
        if (currBtn.text != "") {
            return
        }

        if (flag) {
            currBtn.text = "X"
        } else {
            currBtn.text = "O"
        }
        flag = !flag
        count++

        val b1 = binding.btn1.text.toString()
        val b2 = binding.btn2.text.toString()
        val b3 = binding.btn3.text.toString()
        val b4 = binding.btn4.text.toString()
        val b5 = binding.btn5.text.toString()
        val b6 = binding.btn6.text.toString()
        val b7 = binding.btn7.text.toString()
        val b8 = binding.btn8.text.toString()
        val b9 = binding.btn9.text.toString()

        Log.d("TicTacToe", "b1: $b1, b2: $b2")
        if (b1 != "" && b1 == b2 && b2 == b3) {
            handleWinner(b1)
        } else if (b4 != "" && b4 == b5 && b5 == b6) {
            handleWinner(b4)
        } else if (b7 != "" && b7 == b8 && b8 == b9) {
            handleWinner(b7)
        } else if (b1 != "" && b1 == b4 && b4 == b7) {
            handleWinner(b1)
        } else if (b2 != "" && b2 == b5 && b5 == b8) {
            handleWinner(b2)
        } else if (b3 != "" && b3 == b6 && b6 == b9) {
            handleWinner(b3)
        } else if (b1 != "" && b1 == b5 && b5 == b9) {
            handleWinner(b3)
        } else if (b3 != "" && b3 == b5 && b5 == b7) {
            handleWinner(b3)
        } else if (count == 9) {
            handleDraw()
        }
    }

    private fun handleDraw() {
        Toast.makeText(this@MainActivity, "Draw!", Toast.LENGTH_SHORT).show()
        newGame()
    }

    private fun handleWinner(winner: String) {
        Toast.makeText(this@MainActivity, "Winner is $winner", Toast.LENGTH_SHORT).show()
        newGame()
    }

    private fun newGame() {
        binding.btn1.text = ""
        binding.btn2.text = ""
        binding.btn3.text = ""
        binding.btn4.text = ""
        binding.btn5.text = ""
        binding.btn6.text = ""
        binding.btn7.text = ""
        binding.btn8.text = ""
        binding.btn9.text = ""
        flag = false
        count = 0
    }
}