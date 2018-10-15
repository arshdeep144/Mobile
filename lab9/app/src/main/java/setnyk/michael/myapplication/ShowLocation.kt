package setnyk.michael.myapplication

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.location.*
import android.media.browse.MediaBrowser
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.ResultReceiver
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AlertDialog
import android.util.Log
import android.widget.TextView
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.System.out
import java.util.*
import com.google.android.gms.maps.CameraUpdateFactory



class ShowLocation : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var mMap: GoogleMap


    class AddressResultsReciver(handler: Handler, val mMap:GoogleMap):ResultReceiver(handler){

        override fun onReceiveResult(resultCode: Int, resultData: Bundle) {
            super.onReceiveResult(resultCode, resultData)
            val address = resultData.getString(Constants().RESULT_DATA_KEY)
            val location = resultData.getParcelable<Location>(Constants().LOCATION_DATA_EXTRA)
            val position = LatLng(location.latitude, location.longitude)
            mMap.clear()
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(location.latitude, location.longitude), 12.0f))
           mMap.addMarker(MarkerOptions().position(position).title(address))

            println("results recieved ")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val locationManager :LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val gpsProvider = LocationManager.GPS_PROVIDER
        if(!locationManager.isProviderEnabled(gpsProvider)){
            showAlertDialogue()
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)

        }else{
            mMap = googleMap
            mMap.mapType = GoogleMap.MAP_TYPE_SATELLITE
            mMap.uiSettings.isZoomControlsEnabled=true
            // Add a marker in Sydney and move the camera
            val lastKnownLocation = locationManager.getLastKnownLocation(gpsProvider)
            if(lastKnownLocation != null) {
                val lastKnowLatLong = LatLng(lastKnownLocation.latitude, lastKnownLocation.longitude)
                mMap.addMarker(MarkerOptions().position(lastKnowLatLong))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(lastKnowLatLong))
                startIntentService(lastKnownLocation)
            }
               mMap.isMyLocationEnabled = true
               locationManager.requestLocationUpdates(gpsProvider, 0, 0f, locationListener)
            }
        }


    //define the listener
    private val locationListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            println("location changed")
            startIntentService(location)
        }
        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
        override fun onProviderEnabled(provider: String) {}
        override fun onProviderDisabled(provider: String) {}
    }

    fun startIntentService(location: Location?) {
        Log.i("ShowLocation", "Started service intent")
        val addressReciever = AddressResultsReciver(Handler(),mMap)
        //addressReciever.mMap = mMap

        val intent = Intent(this, FetchAddress::class.java);
        intent.putExtra(Constants().RECEIVER, addressReciever);
        intent.putExtra(Constants().LOCATION_DATA_EXTRA, location);
        startService(intent)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
         if(grantResults.isNotEmpty()  && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
             if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                 //1 request permission to use location data
                 val locationManager: LocationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                 val gpsProvider = LocationManager.GPS_PROVIDER

                 if (!locationManager.isProviderEnabled(gpsProvider)) {
                     showAlertDialogue()
                 }
                 locationManager.requestLocationUpdates(gpsProvider, 0, 0f, locationListener)
                 var lastLocation = locationManager.getLastKnownLocation(gpsProvider)
                 startIntentService(lastLocation)
             }
         }
    }


    fun showAlertDialogue(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(R.string.Location_Alert)
        builder.setMessage(R.string.location_alert)
        builder.setNegativeButton(R.string.No,DialogInterface.OnClickListener { dialogInterface, i ->
            finish()
        })
        builder.setPositiveButton(R.string.Yes,DialogInterface.OnClickListener { dialogInterface, i ->
            val localConfig = Settings.ACTION_LOCATION_SOURCE_SETTINGS
            val enableGpsIntent = Intent(localConfig)
            startActivity(enableGpsIntent)
        })
        builder.create()
        builder.show()


    }


}
