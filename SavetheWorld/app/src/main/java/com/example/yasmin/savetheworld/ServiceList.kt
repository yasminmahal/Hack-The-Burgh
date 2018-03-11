package com.example.yasmin.savetheworld

import android.app.Application
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter


import kotlinx.android.synthetic.main.activity_service_list.*
import kotlinx.android.synthetic.main.content_service_list.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class ServiceList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_list)
        setSupportActionBar(toolbar)

        val typeOfService = intent.getStringExtra("service")
        var servicesAvailable = ""

        if (typeOfService == "MEDICAL") {
            val mDatabaseService = RetrofitClient.getInstance()
            mDatabaseService.getMedicalInfo().enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.d("getting services", t.toString())
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response!!.isSuccessful) {
                        servicesAvailable = response.body()!!.string()
                        println("<<<<<<<<<" + servicesAvailable)
                        if (servicesAvailable == "") {
                            val parentLayout = findViewById<View>(android.R.id.content)
                            Snackbar.make(parentLayout, "None available, try again later!", Snackbar.LENGTH_LONG).show()
                        } else {
                            val posts = servicesAvailable.split(",")
                            val adapter: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, posts)
                            serviceList.adapter = adapter
                        }

                    }
                }
            })
        } else if (typeOfService == "FOOD") {
            val mDatabaseService = RetrofitClient.getInstance()
            mDatabaseService.getFoodInfo().enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.d("getting services", t.toString())
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response!!.isSuccessful) {
                        servicesAvailable = response.body()!!.string()
                        println("<<<<<<<<<" + servicesAvailable)
                        if (servicesAvailable == "") {
                            val parentLayout = findViewById<View>(android.R.id.content)
                            Snackbar.make(parentLayout, "None available, try again later!", Snackbar.LENGTH_LONG).show()
                        } else {
                            val posts = servicesAvailable.split(",")
                            val adapter: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, posts)
                            serviceList.adapter = adapter
                        }

                    }
                }
            })
        } else if (typeOfService == "TRANSPORT") {
            val mDatabaseService = RetrofitClient.getInstance()
            mDatabaseService.getTransportInfo().enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.d("getting services", t.toString())
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response!!.isSuccessful) {
                        servicesAvailable = response.body()!!.string()
                        println("<<<<<<<<<" + servicesAvailable)
                        if (servicesAvailable == "") {
                            val parentLayout = findViewById<View>(android.R.id.content)
                            Snackbar.make(parentLayout, "None available, try again later!", Snackbar.LENGTH_LONG).show()
                        } else {
                            val posts = servicesAvailable.split(",")
                            val adapter: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, posts)
                            serviceList.adapter = adapter
                        }

                    }
                }
            })
        } else {
            val mDatabaseService = RetrofitClient.getInstance()
            mDatabaseService.getHousingInfo().enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.d("getting services", t.toString())
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response!!.isSuccessful) {
                        servicesAvailable = response.body()!!.string()
                        println("<<<<<<<<<" + servicesAvailable)
                        if (servicesAvailable == "") {
                            val parentLayout = findViewById<View>(android.R.id.content)
                            Snackbar.make(parentLayout, "None available, try again later!", Snackbar.LENGTH_LONG).show()
                        } else {
                            val posts = servicesAvailable.split(",")
                            val adapter: ArrayAdapter<String> = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, posts)
                            serviceList.adapter = adapter
                        }

                    }
                }
            })
        }

        serviceList.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
            switchToMoreInformation(position, typeOfService)
        }
    }

    private fun switchToMoreInformation(post: Int, typeOfService: String) {
        val intent = Intent(this, MoreInformation::class.java)
        intent.putExtra("post", post)
        intent.putExtra("type", typeOfService)
        startActivity(intent)
    }

}
