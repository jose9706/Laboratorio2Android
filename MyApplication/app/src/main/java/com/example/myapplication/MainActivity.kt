package com.example.myapplication


import android.graphics.Color
import android.media.MediaActionSound
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import java.util.*
import kotlin.concurrent.schedule


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val buttonInit = findViewById<Button>(R.id.button7)
        button.isClickable = false
        button2.isClickable = false
        button3.isClickable = false
        button4.isClickable = false
        button5.isClickable = false
        button6.isClickable = false
        fun disableAllButtons() {
            button.isClickable = false
            button2.isClickable = false
            button3.isClickable = false
            button4.isClickable = false
            button5.isClickable = false
            button6.isClickable = false
            buttonInit.isClickable = false
        }
        fun enableAllButtons() {
            button.isClickable = true
            button2.isClickable = true
            button3.isClickable = true
            button4.isClickable = true
            button5.isClickable = true
            button6.isClickable = true
            buttonInit.isClickable = true
        }
        val soundList: MutableList<Int> = ArrayList()
        soundList.add(R.raw.sound1)
        soundList.add(R.raw.sound2)
        soundList.add(R.raw.sound3)
        soundList.add(R.raw.sound4)
        soundList.add(R.raw.sound5)
        soundList.add(R.raw.sound6)
        soundList.add(R.raw.sound7)
        var listOfButtons: MutableList<Int> = ArrayList()
        var sequenceDone : Boolean = false
        var counter : Int = 0
        var userListOfButtons : MutableList<Int> = ArrayList()
        fun resetEverything() {
            counter = -1
            sequenceDone = false
            listOfButtons.clear()
            userListOfButtons.clear()
        }
        fun checkProgress(counter : Int) {
            if(listOfButtons.isNotEmpty() and userListOfButtons.isNotEmpty()) {
                if(listOfButtons[counter] != userListOfButtons[counter]) {
                    Toast.makeText(this@MainActivity, "You failed the sequence", Toast.LENGTH_SHORT).show()
                    resetEverything()
                }
                if(counter >= 3) {
                    Toast.makeText(this@MainActivity, "Success!!", Toast.LENGTH_SHORT).show()
                    resetEverything()
                }
            }

        }

        buttonInit.setOnClickListener(View.OnClickListener { view ->
            disableAllButtons()
            for(i in 1..4) {
                val buttonToPress: Int = Random.nextInt(6)
                listOfButtons.add(buttonToPress)
                if(buttonToPress == 0) {
                    displayNumber(buttonToPress, button, 0, i, soundList)
                }
                if(buttonToPress == 1) {
                    displayNumber(buttonToPress, button2, 1, i, soundList)
                }
                if(buttonToPress == 2) {
                    displayNumber(buttonToPress, button3, 2, i, soundList)
                }
                if(buttonToPress == 3) {
                    displayNumber(buttonToPress, button4, 3, i, soundList)
                }
                if(buttonToPress == 4) {
                    displayNumber(buttonToPress, button5, 4, i, soundList)
                }
                if(buttonToPress == 5) {
                    displayNumber(buttonToPress, button6, 5, i, soundList)
                }
                Timer().schedule(8000) {
                    enableAllButtons()
                    sequenceDone = true
                }
            }
        })
        button.setOnClickListener(View.OnClickListener { view ->
            if(sequenceDone) {
                userListOfButtons.add(0)
                checkProgress(counter)
                counter += 1
            }
        })
        button2.setOnClickListener(View.OnClickListener { view ->
            if(sequenceDone) {
                userListOfButtons.add(1)
                checkProgress(counter)
                counter += 1
            }
        })
        button3.setOnClickListener(View.OnClickListener { view ->
            if(sequenceDone) {
                userListOfButtons.add(2)
                checkProgress(counter)
                counter += 1
            }
        })
        button4.setOnClickListener(View.OnClickListener { view ->
            if(sequenceDone) {
                userListOfButtons.add(3)
                checkProgress(counter)
                counter += 1
            }
        })
        button5.setOnClickListener(View.OnClickListener { view ->
            if(sequenceDone) {
                userListOfButtons.add(4)
                checkProgress(counter)
                counter += 1
            }
        })
        button6.setOnClickListener(View.OnClickListener { view ->
            if(sequenceDone) {
                userListOfButtons.add(5)
                checkProgress(counter)
                counter += 1
            }
        })

    }


    private fun playRandomSound(soundList: MutableList<Int>) {
        val randomInt: Int = Random.nextInt(soundList.size)
        val sound: Int = soundList[randomInt]
        val mp = MediaPlayer.create(this, sound)
        mp.start()
    }

    private fun playSound(soundNumber : Int, soundList: MutableList<Int>) {
        val sound: Int = soundList[soundNumber]
        val mp = MediaPlayer.create(this, sound)
        mp.start()
    }

    private fun displayNumber(number: Int, button: Button, id: Int, iterCounter: Int, soundList: MutableList<Int>) {
        val delay : Int = 1500*iterCounter
        var delay2 = delay.toLong()
        if(iterCounter == 1) {
            playSound(number, soundList)
            button.setBackgroundColor(Color.GRAY)
            button.setTextColor(Color.WHITE)
            button.text = iterCounter.toString()
        } else {
            Timer().schedule(delay2) {
                playSound(number, soundList)
                button.setBackgroundColor(Color.GRAY)
                button.setTextColor(Color.WHITE)
                button.text = iterCounter.toString()
            }
        }

        if(iterCounter != 1) {
            delay2 += 1500
        }
        Timer().schedule(delay2) {
            button.text = ""
            if(id == 0) {
                button.setBackgroundColor(Color.BLACK)
            }
            if(id == 1) {
                button.setBackgroundColor(Color.parseColor("#B00020"))
            }
            if(id == 2) {
                button.setBackgroundColor(Color.parseColor("#FFBB33"))
            }
            if(id == 3) {
                button.setBackgroundColor(Color.parseColor("#3700B3"))
            }
            if(id == 4) {
                button.setBackgroundColor(Color.parseColor("#AA66CC"))
            }
            if(id == 5) {
                button.setBackgroundColor(Color.parseColor("#018786"))
            }
        }
    }
}


