package com.tictactoe

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import com.tictactoe.databinding.ActivityMainBinding
import java.util.ArrayDeque

class MainActivity : ComponentActivity() {
    private var flag = false
    private var count = 0
    private lateinit var binding: ActivityMainBinding
    private val buttonQueue = ArrayDeque<Button>()

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
        buttonQueue.add(currBtn)
        flag = !flag
        if (count <= 6) {
            count++;
        }

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
        }

        if (count > 6) {
            val poppedButton = buttonQueue.poll()
            if (poppedButton != null) {
                Log.d("poppedButton ID:", "${poppedButton.id}")
                poppedButton.alpha = 1f
                poppedButton.text = ""
            }
        }
        if (count > 5) {
            val lastButton = buttonQueue.first()
            Log.d("lastButton ID:", "${lastButton.id}")
            lastButton.alpha = 0.3f
        }
    }

    private fun handleDraw() {
        Toast.makeText(this@MainActivity, "Draw!", Toast.LENGTH_LONG).show()
        newGame()
    }

    private fun handleWinner(winner: String) {
        Toast.makeText(this@MainActivity, "Winner is $winner", Toast.LENGTH_LONG).show()
        newGame()
    }

    private fun newGame() {
        binding.btn1.apply{
            text = ""
            alpha = 1f
        }
        binding.btn2.apply{
            text = ""
            alpha = 1f
        }
        binding.btn3.apply{
            text = ""
            alpha = 1f
        }
        binding.btn4.apply{
            text = ""
            alpha = 1f
        }
        binding.btn5.apply{
            text = ""
            alpha = 1f
        }
        binding.btn6.apply{
            text = ""
            alpha = 1f
        }
        binding.btn7.apply{
            text = ""
            alpha = 1f
        }
        binding.btn8.apply{
            text = ""
            alpha = 1f
        }
        binding.btn9.apply{
            text = ""
            alpha = 1f
        }
        flag = false
        count = 0
        buttonQueue.clear()
    }
}