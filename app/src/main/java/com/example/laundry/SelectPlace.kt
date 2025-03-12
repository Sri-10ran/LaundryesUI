//package com.example.laundry
//
//import android.Manifest
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.location.Location
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.ComponentActivity
//import androidx.activity.result.contract.ActivityResultContracts
//import androidx.core.app.ActivityCompat
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//import com.ola.mapsdk.interfaces.OlaMapCallback
//import com.ola.mapsdk.model.OlaLatLng
//import com.ola.mapsdk.view.OlaMap
//import com.ola.mapsdk.view.OlaMapView
//
//class SelectPlace : ComponentActivity() {
//
//    private lateinit var olaMap: OlaMap
//    private lateinit var olaMapView: OlaMapView
//    private lateinit var fetchCurrentLocation: FloatingActionButton
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_select_place)
//
//        olaMapView = findViewById(R.id.mapview)
//        fetchCurrentLocation = findViewById(R.id.floatingActionButton)
//
//        // Initialize the map
//        olaMapView.getMap(
//            apiKey = "RbRrjc37fwWse8LQOsgjNh2IFHSEvMrITPTct8Gb",
//            object : OlaMapCallback {
//                override fun onMapReady(olaMap: OlaMap) {
//                    this@SelectPlace.olaMap = olaMap
//                    fetchUserLocation() // Fetch location when map is ready
//                }
//
//                override fun onMapError(error: String) {
//                    Toast.makeText(this@SelectPlace, "Map Error: $error", Toast.LENGTH_SHORT).show()
//                }
//            })
//
//        // Set up click listener to fetch current location
//        fetchCurrentLocation.setOnClickListener {
//            fetchUserLocation()
//        }
//    }
//
//    private fun fetchUserLocation() {
//        if (!hasLocationPermission()) {
//            requestLocationPermission.launch(
//                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
//            )
//            return
//        }
//
//        val currentLocation: OlaLatLng? = olaMap.getCurrentLocation() // Ola Maps does not provide a callback method
//        if (currentLocation != null) {
//            olaMap.moveCameraToLatLong(currentLocation, 15.0, 2000)
//            checkIfWithinSivakasi(currentLocation)
//        } else {
//            Toast.makeText(this, "Failed to get location. Ensure GPS is enabled.", Toast.LENGTH_SHORT).show()
//        }
//    }
//
//    private fun checkIfWithinSivakasi(location: OlaLatLng) {
//        val sivakasiLat = 9.45
//        val sivakasiLng = 77.8
//        val radius = 10.0 // Approximate boundary radius in km
//
//        val results = FloatArray(1)
//        Location.distanceBetween(location.latitude, location.longitude, sivakasiLat, sivakasiLng, results)
//
//        if (results[0] / 1000 <= radius) {
//            // ✅ User is inside Sivakasi, redirect to TimeDateActivity
//            Toast.makeText(this, "Location confirmed! Proceeding to order...", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this, TimeDateActivity::class.java)
//            startActivity(intent)
//            finish()
//        } else {
//            // ❌ User is outside Sivakasi, show alert and redirect to MainActivity
//            Toast.makeText(this, "Our service is only within Sivakasi.", Toast.LENGTH_LONG).show()
//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }
//
//    // Check if location permission is granted
//    private fun hasLocationPermission(): Boolean {
//        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
//                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
//    }
//
//    // Activity Result API for requesting location permission
//    private val requestLocationPermission = registerForActivityResult(
//        ActivityResultContracts.RequestMultiplePermissions()
//    ) { permissions ->
//        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
//            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
//            fetchUserLocation()
//        } else {
//            Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
//        }
//    }
//}
package com.example.laundry

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.ola.mapsdk.interfaces.OlaMapCallback
import com.ola.mapsdk.model.OlaLatLng
import com.ola.mapsdk.view.OlaMap
import com.ola.mapsdk.view.OlaMapView

class SelectPlace : ComponentActivity() {

    private lateinit var olaMap: OlaMap
    private lateinit var olaMapView: OlaMapView
    private lateinit var fetchCurrentLocation: FloatingActionButton
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_place)

        olaMapView = findViewById(R.id.mapview)
        fetchCurrentLocation = findViewById(R.id.floatingActionButton)

        // Initialize FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // Initialize Ola Map
        olaMapView.getMap(
            apiKey = "RbRrjc37fwWse8LQOsgjNh2IFHSEvMrITPTct8Gb",
            object : OlaMapCallback {
                override fun onMapReady(olaMap: OlaMap) {
                    this@SelectPlace.olaMap = olaMap
                    fetchUserLocation() // Fetch location when map is ready
                }

                override fun onMapError(error: String) {
                    Toast.makeText(this@SelectPlace, "Map Error: $error", Toast.LENGTH_SHORT).show()
                }
            })

        // Set up click listener to fetch current location
        fetchCurrentLocation.setOnClickListener {
            fetchUserLocation()
        }
    }

    @SuppressLint("MissingPermission")
    private fun fetchUserLocation() {
        if (!hasLocationPermission()) {
            requestLocationPermission.launch(
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
            )
            return
        }

        if (!isGPSEnabled()) {
            Toast.makeText(this, "GPS is disabled. Please enable GPS.", Toast.LENGTH_LONG).show()
            startActivity(Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS))
            return
        }

        // Get location using FusedLocationProviderClient
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                val currentLocation = OlaLatLng(location.latitude, location.longitude)
                olaMap.moveCameraToLatLong(currentLocation, 15.0, 2000)
                checkIfWithinSivakasi(currentLocation)
            } else {
                Toast.makeText(this, "Failed to get location. Try again.", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error getting location: ${it.localizedMessage}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkIfWithinSivakasi(location: OlaLatLng) {
        val sivakasiLat = 9.45
        val sivakasiLng = 77.8
        val radius = 10.0 // Approximate boundary radius in km

        val results = FloatArray(1)
        Location.distanceBetween(location.latitude, location.longitude, sivakasiLat, sivakasiLng, results)

        if (results[0] / 1000 <= radius) {
            // ✅ Inside Sivakasi → Redirect to TimeDateActivity
            Toast.makeText(this, "Location confirmed! Proceeding to order...", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, TimeDateActivity::class.java))
            finish()
        } else {
            // ❌ Outside Sivakasi → Show alert and redirect to MainActivity
            Toast.makeText(this, "Our service is only within Sivakasi.", Toast.LENGTH_LONG).show()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    // Check if GPS is enabled
    private fun isGPSEnabled(): Boolean {
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
    }

    // Check if location permission is granted
    private fun hasLocationPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
    }

    // Activity Result API for requesting location permission
    private val requestLocationPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true ||
            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true) {
            fetchUserLocation()
        } else {
            Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
        }
    }
}
