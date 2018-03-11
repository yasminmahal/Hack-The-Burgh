package com.example.yasmin.savetheworld

/**
 * Created by arano on 10/03/2018.
 */
import android.graphics.Bitmap
import okhttp3.ResponseBody
import retrofit2.http.*

interface DatabaseService {

    @PUT("/save_medical_info")
    fun saveMedicalInfo(@Body service: ServiceForServer): retrofit2.Call<String>

    @PUT("/save_housing_info")
    fun saveHousingInfo(@Body service: ServiceForServer): retrofit2.Call<String>

    @PUT("/save_food_info")
    fun saveFoodInfo(@Body service: ServiceForServer): retrofit2.Call<String>

    @PUT("/save_transport_info")
    fun saveTransportInfo(@Body service: ServiceForServer): retrofit2.Call<String>

    @GET("/get_medical_info")
    fun getMedicalInfo(): retrofit2.Call<ResponseBody>

    @GET("/get_housing_info")
    fun getHousingInfo(): retrofit2.Call<ResponseBody>

    @GET("/get_food_info")
    fun getFoodInfo(): retrofit2.Call<ResponseBody>

    @GET("/get_transport_info")
    fun getTransportInfo(): retrofit2.Call<ResponseBody>

    @GET("/get_full_medical")
    fun getFullMedical(@Query("position") pos: Int): retrofit2.Call<ResponseBody>

    @GET("/get_full_transport")
    fun getFullTransport(@Query("position") pos: Int): retrofit2.Call<ResponseBody>

    @GET("/get_full_food")
    fun getFullFood(@Query("position") pos: Int): retrofit2.Call<ResponseBody>

    @GET("/get_full_housing")
    fun getFullHousing(@Query("position") pos: Int): retrofit2.Call<ResponseBody>

    @PUT("/delete_info")
    fun deleteInfo(@Body service: DeleteFromServer): retrofit2.Call<String>
}