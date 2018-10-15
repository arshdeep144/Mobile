package ca.uoit.csci4100u.mapsdemo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends Activity implements LocationListener {
    private LocationManager locationManager;

    private double latitude, longitude;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // get access to geolocation services
        setupGeolocation();
    }

    private void setupGeolocation() {
        // check to ensure I have permission
        verifyGeolocationPermission();

        // check to ensure that geolocation is enabled

        // request updates

    }

    private static final int REQUEST_GEOLOCATION_PERMS = 1;

    private void verifyGeolocationPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                // TODO:  Show the user rationale and ask for permission
            }

            String[] perms = new String[] { Manifest.permission.ACCESS_FINE_LOCATION };
            requestPermissions(perms, REQUEST_GEOLOCATION_PERMS);
        } else {
            // geolocation permission granted, so request location updates
            verifyGeolocationEnabled();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] perms,
                                           int[] results) {
        if (requestCode == REQUEST_GEOLOCATION_PERMS) {
            if (results[0] == PackageManager.PERMISSION_GRANTED) {
                // geolocation permission granted, so request location updates
                verifyGeolocationEnabled();
            }
        }
    }

    private void verifyGeolocationEnabled() {
        locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

        // check if geolocation is enabled in settings
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            // TODO:  Request location updates
            requestLocationUpdates();
        } else {
            // show the settings app to let the user enable it
            String locationSettings = Settings.ACTION_LOCATION_SOURCE_SETTINGS;
            Intent enableGeoloc = new Intent(locationSettings);
            startActivity(enableGeoloc);

            // Note:  startActivityForResult() may be better here
        }
    }

    private void requestLocationUpdates() {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setPowerRequirement(Criteria.POWER_MEDIUM);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setSpeedRequired(false);
        criteria.setCostAllowed(false);

        String recommendedProvider = locationManager.getBestProvider(criteria, true);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(recommendedProvider,
                    5000, 10, this);
            Log.i("MapsDemo", "requestLocationUpdates()");
        }
    }

    public void search(View source) {
        EditText txtLocation = (EditText)findViewById(R.id.txtLocation);
        String locationName = txtLocation.getText().toString();

        Log.i("MapsDemo", "search()::locationName == " + locationName);

        Intent showMapIntent = new Intent(this, MapsActivity.class);
        showMapIntent.putExtra("locationName", locationName);
        showMapIntent.putExtra("backupLatitude", latitude);
        showMapIntent.putExtra("backupLongitude", longitude);
        startActivity(showMapIntent);
    }

    private String geocode(double latitude, double longitude) {
        if (Geocoder.isPresent()) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            try {
                List<Address> results = geocoder.getFromLocation(latitude, longitude, 1);

                if (results.size() > 0) {
                    return results.get(0).getAddressLine(0);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public void onLocationChanged(Location location) {
        Log.i("MapsDemo", "Location changed: " + location);

        // remember the coordinates for the map activity's backup location
        latitude = location.getLatitude();
        longitude = location.getLongitude();

        // geocode the result - get the location name
        String locationName = geocode(location.getLatitude(), location.getLongitude());

        // show the location in the search UI
        EditText txtLocation = (EditText)findViewById(R.id.txtLocation);
        txtLocation.setText(locationName);
    }

    public void onProviderEnabled(String provider) {
        Log.i("MapsDemo", "Provider enabled: " + provider);
    }

    public void onProviderDisabled(String provider) {
        Log.i("MapsDemo", "Provider disabled: " + provider);
    }

    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i("MapsDemo", "Provider ("+provider+") status changed: " + status);
    }
}
