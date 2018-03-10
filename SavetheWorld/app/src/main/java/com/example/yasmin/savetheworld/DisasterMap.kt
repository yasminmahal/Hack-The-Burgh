package com.example.yasmin.savetheworld

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.webkit.WebSettings

import kotlinx.android.synthetic.main.activity_disaster_map.*
import android.webkit.WebView



class DisasterMap : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disaster_map)
        setSupportActionBar(toolbar)

        val settings = myWebView.settings
        settings.domStorageEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.javaScriptEnabled = true
        settings.cacheMode = WebSettings.LOAD_NO_CACHE
        myWebView.loadUrl("http://hisz.rsoe.hu/alertmap/index2.php")

    }

}
