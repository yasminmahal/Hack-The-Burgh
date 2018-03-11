package com.example.yasmin.savetheworld

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.graphics.drawable.LevelListDrawable
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.ContextCompat.checkSelfPermission
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*
import com.google.android.gms.location.places.ui.PlacePicker

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread
import org.json.JSONObject
import java.net.URL


class EmergencyMap : AppCompatActivity(), OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private lateinit var mMap: GoogleMap
    private lateinit var mGoogleApiClient: GoogleApiClient
    val PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1
    var mLocationPermissionGranted = false
    private var mLastLocation: Location? = null
    private var mCurrentLocation: Location? = null
    private lateinit var locationCallback: LocationCallback
    private val PLACE_PICKER_REQUEST = 999
    private var markersLoaded = false
    private var mapReady = false
    private lateinit var lm: LocationManager
    private lateinit var fusedLocationClient: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency_map)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations) {
                    mCurrentLocation = location
                }
            }
        }

        mGoogleApiClient = GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build()
    }



    override fun onStart() {
        super.onStart()
        mGoogleApiClient.connect()
    }

    override fun onStop() {
        super.onStop()
        if (mGoogleApiClient.isConnected) {
            mGoogleApiClient.disconnect()
        }
    }

    fun createLocationRequest() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.interval = 5000
        mLocationRequest.fastestInterval = 1000
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY


        val permissionCheck = checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, locationCallback, null)

        }
    }

    override fun onConnected(connectionHint: Bundle?) {
        try {
            createLocationRequest()
        } catch (ise: IllegalStateException) {
            println("IllegalStateException thrown [onConnected]")
        }
        if (checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient)
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    override fun onLocationChanged(current: Location?) {
        if (current == null) {
            println("[OnLocationChanged] Location unknown")
        } else {

            mCurrentLocation = current

            println(""" [onLocationChanged] Lat/long now
                (${current.getLatitude()},
                ${current.getLongitude()})"""
            )

            if (mapReady && !markersLoaded) {
                toast("loading markers")
                loadMarkers(current)
            }
        }
    }

    override fun onConnectionSuspended(flag: Int) {
        println(">>>> onConnectionSuspended")
    }

    override fun onConnectionFailed(result: ConnectionResult) {
        println(">>>> onConnectionFailed")
    }

    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mapReady = true

        try {
            mMap.isMyLocationEnabled = true
        } catch (se: SecurityException) {
            println("Security Exception thrown [onMapReady]")
        }

        mMap.uiSettings.isMyLocationButtonEnabled = true
        val start = LatLng(55.9445, -3.18736)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(start, 15.toFloat()))
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.uiSettings.isZoomGesturesEnabled = true



        fusedLocationClient.lastLocation
                .addOnSuccessListener { location : Location? ->
                    // Got last known location. In some rare situations this can be null.
                    if (location != null) {
                        loadMarkers(location)
                    }

                }


        println("SHould be loading markers now")
        if (mCurrentLocation != null) {
            println("Loading markers")
            loadMarkers(mCurrentLocation!!)
        }
        onLocationChanged(mCurrentLocation)
    }

    private fun loadMarkers(location: Location) {

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 15.toFloat()))

        val urlHospital = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${location.latitude},${location.longitude}&radius=5000&type=hospital&key=AIzaSyCVgLfSNS7-CMsfBVGROXGOwN3M8oWvrJg"
        val queue = Volley.newRequestQueue(this@EmergencyMap)

        val jsonObjectRequestHospital = JsonObjectRequest(Request.Method.GET, urlHospital, null,
                Response.Listener { response ->
                    val jsonObject = JSONObject(response.toString())
                    val places = jsonObject.getJSONArray("results")
                    val length = places.length()
                    val hospitalIcon = BitmapFactory.decodeResource(resources, R.drawable.hospital)
                    val hospitalResized = Bitmap.createScaledBitmap(hospitalIcon, 80, 80, false)
                    for (i in 0 until length) {
                        val place = places.getJSONObject(i)
                        val name = place.getString("name")
                        val lat = place.getJSONObject("geometry").getJSONObject("location").getDouble("lat")
                        val lng = place.getJSONObject("geometry").getJSONObject("location").getDouble("lng")
                        val marker = mMap.addMarker(MarkerOptions()
                                .position(LatLng(lat, lng))
                                .title(name)
                                .icon(BitmapDescriptorFactory.fromBitmap(hospitalResized))
                        )
                    }
                },
                Response.ErrorListener { error ->
                    println("JSON parse error")
                }
        )
        queue.add(jsonObjectRequestHospital)

        val urlPolice = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${location.latitude},${location.longitude}&radius=5000&type=police&key=AIzaSyCVgLfSNS7-CMsfBVGROXGOwN3M8oWvrJg"

        val jsonObjectRequestPolice = JsonObjectRequest(Request.Method.GET, urlPolice, null,
                Response.Listener { response ->
                    val jsonObject = JSONObject(response.toString())
                    val places = jsonObject.getJSONArray("results")
                    val length = places.length()
                    val policeIcon = BitmapFactory.decodeResource(resources, R.drawable.police)
                    val policeResized = Bitmap.createScaledBitmap(policeIcon, 80, 80, false)
                    for (i in 0 until length) {
                        val place = places.getJSONObject(i)
                        val name = place.getString("name")
                        val lat = place.getJSONObject("geometry").getJSONObject("location").getDouble("lat")
                        val lng = place.getJSONObject("geometry").getJSONObject("location").getDouble("lng")
                        val marker = mMap.addMarker(MarkerOptions()
                                .position(LatLng(lat, lng))
                                .title(name)
                                .icon(BitmapDescriptorFactory.fromBitmap(policeResized))
                        )
                    }
                },
                Response.ErrorListener { error ->
                    println("JSON parse error")
                }
        )
        queue.add(jsonObjectRequestPolice)

        val urlFire = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=${location.latitude},${location.longitude}&radius=5000&type=fire_station&key=AIzaSyCVgLfSNS7-CMsfBVGROXGOwN3M8oWvrJg"

        val jsonObjectRequestFire = JsonObjectRequest(Request.Method.GET, urlFire, null,
                Response.Listener { response ->
                    val jsonObject = JSONObject(response.toString())
                    val places = jsonObject.getJSONArray("results")
                    val length = places.length()
                    val fireIcon = BitmapFactory.decodeResource(resources, R.drawable.fire)
                    val fireResized = Bitmap.createScaledBitmap(fireIcon, 80, 80, false)
                    for (i in 0 until length) {
                        val place = places.getJSONObject(i)
                        val name = place.getString("name")
                        val lat = place.getJSONObject("geometry").getJSONObject("location").getDouble("lat")
                        val lng = place.getJSONObject("geometry").getJSONObject("location").getDouble("lng")
                        val marker = mMap.addMarker(MarkerOptions()
                                .position(LatLng(lat, lng))
                                .title(name)
                                .icon(BitmapDescriptorFactory.fromBitmap(fireResized))
                        )
                    }
                },
                Response.ErrorListener { error ->
                    println("JSON parse error")
                }
        )
        queue.add(jsonObjectRequestFire)



        markersLoaded = true
    }

    override fun onProviderDisabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onProviderEnabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //AppCompat.checkSelfPermission( requestCode,resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PLACE_PICKER_REQUEST) {
                val place = PlacePicker.getPlace(this, data)
                val placename = place.name
                val latlng = place.latLng
            }
        }
    }

}