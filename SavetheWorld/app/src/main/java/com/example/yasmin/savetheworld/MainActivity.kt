package com.example.yasmin.savetheworld

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            switchToNeedsHelp()
        }
        button2.setOnClickListener{
            switchToHelping()
        }
        button3.setOnClickListener{
            switchToMaps()
        }
    }

    private fun switchToNeedsHelp() {
        val intent = Intent (this, NeedsHelp::class.java)
        startActivity(intent)
    }

    private fun switchToHelping() {
        val intent = Intent (this, Helping::class.java)
        startActivity(intent)
    }

    private fun switchToMaps() {
        val intent = Intent (this, DisasterMap::class.java)
        startActivity(intent)
    }


}
