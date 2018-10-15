package com.example.jclarens.timefighter

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.AbsListView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton : Button
    internal lateinit var gameScoreTextView: TextView
    internal lateinit var timeLeftTextView: TextView
    internal var score = 0
    internal var gameStarted = false
    internal lateinit var countDownTimer: CountDownTimer
    internal val initialCountDown : Long = 60000
    internal val countDownInterval: Long = 1000
    internal  val TAG = MainActivity::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG, "onCreate called. Score is: $score")

        tapMeButton = findViewById<Button>(R.id.tap_me_button)
        gameScoreTextView = findViewById<TextView>(R.id.game_score_text_view)
        timeLeftTextView = findViewById<TextView>(R.id.time_left_text_view)

//     1. gameScoreTextView.text = getString(R.string.your_score,score.toString())
//     2. val a = getString(R.string.your_score,score.toString())
//        gameScoreTextView.setText(a)
        resetGame()
        tapMeButton.setOnClickListener { view ->
            incrementScore()
        }

    }

    fun resetGame(){
            score = 0
            gameScoreTextView.setText(getString(R.string.your_score,score.toString()))

            val initialTimeLeft = initialCountDown / 1000
            timeLeftTextView.setText(getString(R.string.time_left,initialTimeLeft.toString()))

            countDownTimer = object: CountDownTimer (initialCountDown,countDownInterval){
                override fun onTick(p0: Long) {
                    val timeLeft = p0/1000
                    timeLeftTextView.setText(getString(R.string.time_left,timeLeft.toString()))
                }

                override fun onFinish() {
                    endGame()
                }
            }
            gameStarted = false
        }
    private fun startGame(){
        countDownTimer.start()
        gameStarted = true
    }

    private fun endGame(){
        Toast.makeText(this, getString(R.string.game_over_message,score.toString()), Toast.LENGTH_LONG).show()
        resetGame()
    }

    private fun incrementScore() {
        if (!gameStarted){
            startGame()
        }
        score = score+1
        val newScore = getString(R.string.your_score,score.toString())
        gameScoreTextView.setText(newScore)
    }


}
