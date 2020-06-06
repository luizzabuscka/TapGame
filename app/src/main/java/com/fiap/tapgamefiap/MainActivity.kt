package com.fiap.tapgamefiap

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btStartGame.setOnClickListener {
            val intent = Intent(this@MainActivity, GameActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        val sharedPref = getSharedPreferences("TapGame", Context.MODE_PRIVATE)

        val record = sharedPref.getInt("record", 0)
        tvRecord.text = "Record: $record"

        val lastScore = sharedPref.getInt("lastScore", 0)
        tvLastScore.text = "Última pontuação: $lastScore"
    }
}
