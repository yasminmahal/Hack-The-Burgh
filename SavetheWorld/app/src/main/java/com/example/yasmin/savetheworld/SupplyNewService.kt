package com.example.yasmin.savetheworld

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import retrofit2.Call
import retrofit2.Response
import android.util.Log
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_supply_new_service.*

/**
 * A login screen that offers login via email/password.
 */
class SupplyNewService : AppCompatActivity() {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    // private var mAuthTask: UserLoginTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supply_new_service)
        // Set up the login form.
        val typeOfService = intent.extras.getString("service")



        done.setOnClickListener {
            val name = name.text.toString()
            val title = titleInput.text.toString()
            val description = description.text.toString()
            val number = telephone.text.toString()
            saveService(name, title, description, number, typeOfService)

            Toast.makeText(this,"Submitted, thank you for helping your community",Toast.LENGTH_LONG).show()
            switchToMain()
        }

    }

    private fun saveService(name: String, title: String, description: String, number: String, typeofService: String){

        val mDatabaseService = RetrofitClient.getInstance()
        if (typeofService == "FOOD") {
            mDatabaseService.saveFoodInfo(ServiceForServer(name, title, description, number)).enqueue(object : retrofit2.Callback<String> {
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
        } else if (typeofService == "MEDICAL") {
            mDatabaseService.saveMedicalInfo(ServiceForServer(name, title, description, number)).enqueue(object : retrofit2.Callback<String> {
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
        } else if (typeofService == "TRANSPORT") {
            mDatabaseService.saveTransportInfo(ServiceForServer(name, title, description, number)).enqueue(object : retrofit2.Callback<String> {
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
        } else if (typeofService == "HOUSING") {
            mDatabaseService.saveHousingInfo(ServiceForServer(name, title, description, number)).enqueue(object : retrofit2.Callback<String> {
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
        }

    }

    private fun switchToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
