package com.fiap.tapgamefiap

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Debug
import android.util.Log
import kotlinx.android.synthetic.main.activity_game.*
import java.util.*
import kotlin.concurrent.schedule
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    private val colorWin = Color.BLUE
    private val colorLose = Color.RED

    private var win = false
    private var score = 0

    private var record = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val sharedPref = getSharedPreferences("TapGame", Context.MODE_PRIVATE)
        record = sharedPref.getInt("record", 0)

        llTap.setOnClickListener {
            if (win) {
                score++
                tvTaps.text = "Taps: $score"
            } else {
                saveRecord()
                finish()
            }
        }

        sortNewColor()
    }

    private fun sortNewColor() {
        win = Random.nextBoolean()

        if (win) {
            llTap.setBackgroundColor(colorWin)
        } else {
            llTap.setBackgroundColor(colorLose)
        }

        Log.d("TapGame", "Novo sorteio: $win")

        startTimer()
    }

    private fun startTimer() {
        val randomMiliseconds = Random.nextLong(700, 1200)
        Timer("SetColor", false).schedule(randomMiliseconds) {
            sortNewColor()
        }
    }

    private fun saveRecord() {
        val sharedPrefEditor = getSharedPreferences("TapGame", Context.MODE_PRIVATE).edit()

        // Save last score
        sharedPrefEditor.putInt("lastScore", score)

        if (score > record) {
            // Save the new record
            sharedPrefEditor.putInt("record", score)
        }
        sharedPrefEditor.apply()
    }
}
