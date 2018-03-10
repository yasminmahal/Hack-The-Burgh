package com.example.yasmin.savetheworld

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.SeekBar
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_needs_help.*
import kotlinx.android.synthetic.main.content_needs_help.*
import org.w3c.dom.Text

class NeedsHelp : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_needs_help)
        setSupportActionBar(toolbar)

        val text = findViewById<TextView>(R.id.textView) //the description text for each difficulty level
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        var title = findViewById<TextView>(R.id.textView2)
        val type = intent.getStringExtra("type")


        if (type == "Needs Help"){
            title.text = "I NEED:"
        } else {
            title.text = "I CAN HELP WITH:"
            button5.setVisibility(View.INVISIBLE)
        }



        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{  //when the seekbar is moved check how far it has moved
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (progress == 0) { //each position on the seekbar represents a different difficulty
                    text.text = "SHELTER"
                    if (type == "Needs Help"){
                        button4.setBackgroundColor(Color.parseColor("#b2dfdb"))
                    } else {
                        button4.setBackgroundColor(Color.parseColor("#ffccbc"))
                    }


                } else if (progress ==1 ) {
                    text.text = "TRANSPORT"
                    if (type == "Needs Help"){
                        button4.setBackgroundColor(Color.parseColor("#4db6ac"))
                    } else {
                        button4.setBackgroundColor(Color.parseColor("#ff8a65"))
                    }


                } else if (progress == 2) {
                    text.text = "MEDICAL ATTENTION"
                    if (type == "Needs Help"){
                        button4.setBackgroundColor(Color.parseColor("#009688"))
                    } else {
                        button4.setBackgroundColor(Color.parseColor("#ff5722"))
                    }


                } else if (progress == 3 ) {
                    text.text = "FOOD"
                    if (type == "Needs Help"){
                        button4.setBackgroundColor(Color.parseColor("#00796b"))
                    } else {
                        button4.setBackgroundColor(Color.parseColor("#e64a19"))
                    }

                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })


        button4.setOnClickListener {
            switchToInfo()
        }
    }
    private fun switchToInfo() {
        val intent= Intent(this,DisasterMap:: class.java)


        startActivity(intent)
    }

}
