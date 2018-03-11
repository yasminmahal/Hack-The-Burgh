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
        toolbar.setTitleTextColor(Color.WHITE)

        val text = findViewById<TextView>(R.id.textView) //the description text for each difficulty level
        val seekBar = findViewById<SeekBar>(R.id.seekBar)
        seekBar.progress = 0
        var title = findViewById<TextView>(R.id.textView2)
        val type = intent.getStringExtra("type")
        var service = "HOUSING"


        if (type == "Needs Help"){
            title.text = "I Need:"
            button4.setBackgroundColor(Color.parseColor("#009688"))
        } else {
            title.text = "I Can Help With:"
            button5.visibility = (View.INVISIBLE)
            button4.setBackgroundColor(Color.parseColor("#fb8c00"))
        }



        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{  //when the seekbar is moved check how far it has moved
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (progress == 0) { //each position on the seekbar represents a different difficulty
                    text.text = "Shelter"
                    service = "HOUSING"
                    if (type == "Needs Help"){
                        button4.setBackgroundColor(Color.parseColor("#009688"))
                    } else {
                        button4.setBackgroundColor(Color.parseColor("#fb8c00"))
                    }


                } else if (progress ==1 ) {
                    text.text = "Transport"
                    service = "TRANSPORT"
                    if (type == "Needs Help"){
                        button4.setBackgroundColor(Color.parseColor("#00897b"))
                    } else {
                        button4.setBackgroundColor(Color.parseColor("#f57c00"))
                    }


                } else if (progress == 2) {
                    text.text = "Medical Attention"
                    service = "MEDICAL"
                    if (type == "Needs Help"){
                        button4.setBackgroundColor(Color.parseColor("#00796b"))
                    } else {
                        button4.setBackgroundColor(Color.parseColor("#ef6c00"))
                    }


                } else if (progress == 3 ) {
                    text.text = "Food"
                    service = "FOOD"
                    if (type == "Needs Help"){
                        button4.setBackgroundColor(Color.parseColor("#00695c"))
                    } else {
                        button4.setBackgroundColor(Color.parseColor("#e65100"))
                    }

                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {

            }
        })


        button4.setOnClickListener {
            if (type == "Needs Help") {
                switchToList(service)
            } else {
                switchToInfo(service)
            }
        }

        button5.setOnClickListener {
            switchtoEmergency()
        }
    }
    private fun switchToInfo(service: String) {
        val intent = Intent(this,SupplyNewService:: class.java)
        intent.putExtra("service", service)
        startActivity(intent)
    }

    private fun switchToList(service: String) {
        val intent = Intent(this, ServiceList::class.java)
        intent.putExtra("service", service)
        startActivity(intent)
    }

    private fun switchtoEmergency() {
        val intent = Intent(this, EmergencyMap:: class.java)
        startActivity(intent)
    }

}
