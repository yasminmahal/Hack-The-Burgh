package com.example.yasmin.savetheworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.TextUtils
import android.text.method.ScrollingMovementMethod
import android.text.style.AbsoluteSizeSpan
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_more_information.*
import kotlinx.android.synthetic.main.content_service_list.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri

class MoreInformation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_information)

        val post = intent.getIntExtra("post",0)
        val typeOfService = intent.getStringExtra("type")
        var telephone = ""
        var name1 = ""
        var title1 = ""

        if (typeOfService == "MEDICAL") {
            val mDatabaseService = RetrofitClient.getInstance()
            mDatabaseService.getFullMedical(post).enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.d("getting services", t.toString())
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response!!.isSuccessful) {
                        val stringVersion = response.body()!!.string()
                        println("<<<<<<<<<" + stringVersion)
                        val item = stringVersion.split(",")
                        val name = SpannableString("Name: " + item[0])
                        name.setSpan(AbsoluteSizeSpan(40),0,item[0].length,0)
                        val title = SpannableString(item[1])
                        title.setSpan(AbsoluteSizeSpan(100),0,item[1].length,0)
                        val description = SpannableString(item[2])
                        description.setSpan(AbsoluteSizeSpan(60),0,item[2].length,0)
                        val number = SpannableString("Contact Number: " + item[3])
                        number.setSpan(AbsoluteSizeSpan(40),0,item[3].length,0)
                        val final = TextUtils.concat(title, "\n\n", description, "\n\n", name, "\n\n", number, "\n\n\n")
                        service.text = final
                        service.movementMethod = ScrollingMovementMethod()
                        telephone = item[3]
                        name1 = item[0]
                        title1 = item[1]
                    }
                }
            })
        } else if (typeOfService == "FOOD") {
            val mDatabaseService = RetrofitClient.getInstance()
            mDatabaseService.getFullFood(post).enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.d("getting services", t.toString())
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response!!.isSuccessful) {
                        val stringVersion = response.body()!!.string()
                        println("<<<<<<<<<" + stringVersion)
                        val item = stringVersion.split(",")
                        val name = SpannableString("Name: " + item[0])
                        name.setSpan(AbsoluteSizeSpan(40),0,item[0].length,0)
                        val title = SpannableString(item[1])
                        title.setSpan(AbsoluteSizeSpan(100),0,item[1].length,0)
                        val description = SpannableString(item[2])
                        description.setSpan(AbsoluteSizeSpan(60),0,item[2].length,0)
                        val number = SpannableString("Contact Number: " + item[3])
                        number.setSpan(AbsoluteSizeSpan(40),0,item[3].length,0)
                        val final = TextUtils.concat(title, "\n\n", description, "\n\n", name, "\n\n", number, "\n\n\n")
                        service.text = final
                        service.movementMethod = ScrollingMovementMethod()
                        telephone = item[3]
                        name1 = item[0]
                        title1 = item[1]

                    }
                }
            })
        } else if (typeOfService == "TRANSPORT") {
            val mDatabaseService = RetrofitClient.getInstance()
            mDatabaseService.getFullTransport(post).enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.d("getting services", t.toString())
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response!!.isSuccessful) {
                        val stringVersion = response.body()!!.string()
                        println("<<<<<<<<<" + stringVersion)
                        val item = stringVersion.split(",")
                        val name = SpannableString("Name: " + item[0])
                        name.setSpan(AbsoluteSizeSpan(40),0,item[0].length,0)
                        val title = SpannableString(item[1])
                        title.setSpan(AbsoluteSizeSpan(100),0,item[1].length,0)
                        val description = SpannableString(item[2])
                        description.setSpan(AbsoluteSizeSpan(60),0,item[2].length,0)
                        val number = SpannableString("Contact Number: " + item[3])
                        number.setSpan(AbsoluteSizeSpan(40),0,item[3].length,0)
                        val final = TextUtils.concat(title, "\n\n", description, "\n\n", name, "\n\n", number, "\n\n\n")
                        service.text = final
                        service.movementMethod = ScrollingMovementMethod()
                        telephone = item[3]
                        name1 = item[0]
                        title1 = item[1]

                    }
                }
            })
        } else {
            val mDatabaseService = RetrofitClient.getInstance()
            mDatabaseService.getFullHousing(post).enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onFailure(call: Call<ResponseBody>?, t: Throwable?) {
                    Log.d("getting services", t.toString())
                }

                override fun onResponse(call: Call<ResponseBody>?, response: Response<ResponseBody>?) {
                    if (response!!.isSuccessful) {
                        val stringVersion = response.body()!!.string()
                        println("<<<<<<<<<" + stringVersion)
                        val item = stringVersion.split(",")
                        val name = SpannableString("Name: " + item[0])
                        name.setSpan(AbsoluteSizeSpan(40),0,item[0].length,0)
                        val title = SpannableString(item[1])
                        title.setSpan(AbsoluteSizeSpan(100),0,item[1].length,0)
                        val description = SpannableString(item[2])
                        description.setSpan(AbsoluteSizeSpan(60),0,item[2].length,0)
                        val number = SpannableString("Contact Number: " + item[3])
                        number.setSpan(AbsoluteSizeSpan(40),0,item[3].length,0)
                        val final = TextUtils.concat(title, "\n\n", description, "\n\n", name, "\n\n", number, "\n\n\n")
                        service.text = final
                        service.movementMethod = ScrollingMovementMethod()
                        telephone = item[3]
                        name1 = item[0]
                        title1 = item[1]

                    }
                }
            })
        }

        floatingActionButton.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Would you like to contact this person")
                    .setPositiveButton("Yes", { _, _ ->
                        val mDatabaseService = RetrofitClient.getInstance()
                        mDatabaseService.deleteInfo(DeleteFromServer(name1, title1, typeOfService)).enqueue(object : retrofit2.Callback<String> {
                            override fun onResponse(call: Call<String>?, response: Response<String>?) {

                                if (response!!.isSuccessful) {
                                    Log.d("AddUserActivity", "sent")
                                } else {
                                    Log.d("AddUserActivity", "unsuccessful")
                                }
                            }

                            override fun onFailure(call: Call<String>?, t: Throwable?) {
                                Log.d("AddUserActivity", "error sending user info")
                            }
                        }
                        )
                        val intent1 = Intent(Intent(this, MainActivity::class.java))
                        startActivity(intent1)
                        val intent = Intent(Intent.ACTION_VIEW)
                        intent.data = Uri.parse("sms:" + telephone)
                        intent.putExtra("sms_body", "I would like your help please!")
                        startActivity(intent)
                    })
                    .setNegativeButton("No", null)
            builder.show()
        }

    }
}
