package com.example.yasmin.savetheworld

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_needs_help.*
import kotlinx.android.synthetic.main.content_needs_help.*

class NeedsHelp : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_needs_help)
        setSupportActionBar(toolbar)

        val text = findViewById<TextView>(R.id.textView)  //the description text for each difficulty level
        val seekBar = findViewById<SeekBar>(R.id.seekBar)



        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{  //when the seekbar is moved check how far it has moved
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (progress == 0) { //each position on the seekbar represents a different difficulty
                    text.text = "SHELTER"
                    button4.setBackgroundColor(Color.parseColor("#ef534f"))

                } else if (progress ==1 ) {
                    text.text = "TRANSPORT"
                    button4.setBackgroundColor(Color.parseColor("#ffb74d"))

                } else if (progress == 2) {
                    text.text = "MEDICAL ATTENTION"
                    button4.setBackgroundColor(Color.parseColor("#fff176"))

                } else if (progress == 3 ) {
                    text.text = "FOOD"
                    button4.setBackgroundColor(Color.parseColor("#aee671"))

                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })


        button4.setOnClickListener { //when the play button is pressed, switch to the maps activity
            switchToMaps()
        }
    }

    private fun switchToMaps() {
        val intent = Intent (this, DisasterMap::class.java)
        startActivity(intent)
    }


}
