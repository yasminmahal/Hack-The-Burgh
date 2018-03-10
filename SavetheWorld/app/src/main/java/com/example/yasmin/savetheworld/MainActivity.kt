package com.example.yasmin.savetheworld

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var type = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        button.setOnClickListener{
            type = "Needs Help"
            switchToNeedsHelp()
        }
        button2.setOnClickListener{
            type = "Helping"
            switchToNeedsHelp()
        }
        button3.setOnClickListener{
            switchToMaps()
        }
    }

    private fun switchToNeedsHelp() {

        val intent = Intent (this, NeedsHelp::class.java)
        intent.putExtra("type", type)
        startActivity(intent)
    }

    private fun switchToMaps() {
        val intent = Intent (this, DisasterMap::class.java)
        startActivity(intent)
    }


}
