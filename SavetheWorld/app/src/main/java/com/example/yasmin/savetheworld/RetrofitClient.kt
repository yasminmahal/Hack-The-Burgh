package com.example.yasmin.savetheworld

/**
 * Created by arano on 10/03/2018.
 */
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object {
        private val client = Retrofit.Builder().baseUrl("http://80c6dd9c.ngrok.io").addConverterFactory(GsonConverterFactory.create()).build() !!
        private val service = client.create(DatabaseService::class.java) !!
        fun getInstance() : DatabaseService {
            return service
        }
    }
}