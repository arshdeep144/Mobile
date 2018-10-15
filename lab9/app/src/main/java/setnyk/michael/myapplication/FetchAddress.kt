package setnyk.michael.myapplication

import android.app.IntentService
import android.content.ContentValues.TAG
import android.content.Intent
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Bundle
import android.os.ResultReceiver
import android.text.TextUtils
import android.util.Log
import java.util.*

/**
 * Created by michael on 02/11/17.
 * This is meant for longer running background tasks
 * async tasks are meant for short operations
 */
class FetchAddress: IntentService("GPS_Intent_Service") {
    lateinit var mReceiver: ResultReceiver


    override fun onHandleIntent(intent: Intent) {
        Log.i("Fetch Address", "start of fetch address")
        val errorMessage = "";

        mReceiver = intent.getParcelableExtra(Constants().RECEIVER)
        // Get the location passed to this service through an extra.
        val location:Location = intent.getParcelableExtra(Constants().LOCATION_DATA_EXTRA)
        val geocoder = Geocoder(this, Locale.getDefault())

        val addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)

        // Handle case where no address was found.
        if (addresses == null || addresses.size == 0) {
            if (errorMessage.isEmpty()) {
                val errorMessage = getString(R.string.no_address_found);
                Log.e(TAG, errorMessage);
            }
            deliverResultToReceiver(Constants().FAILURE_RESULT, errorMessage,location);
        } else {
            val address = addresses[0]
            val addressFragments = ArrayList<String>()

            for (i in 0..address.maxAddressLineIndex) {
                addressFragments.add(address.getAddressLine(i))
            }

            Log.i(TAG, getString(R.string.address_found));
            deliverResultToReceiver(Constants().SUCCESS_RESULT,
                    TextUtils.join(System.getProperty("line.separator"),
                            addressFragments),location)
        }
    }


    private fun deliverResultToReceiver(resultCode: Int, message :String,location: Location) {
        val bundle = Bundle()

        bundle.putString(Constants().RESULT_DATA_KEY, message)
        bundle.putParcelable(Constants().LOCATION_DATA_EXTRA,location)
        mReceiver.send(resultCode, bundle)
    }




}