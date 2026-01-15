package org.example

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import java.util.*

class LocationActivity : AppCompatActivity() {

    lateinit var tvDannye: TextView
    lateinit var manager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location)

        tvDannye = findViewById(R.id.tvDannye)
        manager = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        } else {
            nachatPoisk()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            nachatPoisk()
        }
    }

    @SuppressLint("MissingPermission")
    private fun nachatPoisk() {
        val poslednyaya = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
        if (poslednyaya != null) {
            pokazatIZapisat(poslednyaya)
        }

        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1f, object : LocationListener {
            override fun onLocationChanged(loc: Location) {
                pokazatIZapisat(loc)
            }
            override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {}
        })
    }

    private fun pokazatIZapisat(loc: Location) {
        val lat = loc.latitude
        val lon = loc.longitude
        val alt = loc.altitude
        val vrm = Date(loc.time).toString()

        val vivod = "Широта: $lat\nДолгота: $lon\nВысота: $alt\nВремя: $vrm"
        tvDannye.text = vivod

        val strokaFayla = "{ \"lat\": $lat, \"lon\": $lon, \"alt\": $alt, \"time\": \"$vrm\" }\n"
        try {
            val fail = File(filesDir, "log.json")
            fail.appendText(strokaFayla)
        } catch (e: Exception) { }
    }
}