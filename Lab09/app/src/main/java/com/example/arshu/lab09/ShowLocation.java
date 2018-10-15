package com.example.arshu.lab09;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.location.LocationListener;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class ShowLocation extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private LocationManager locationManager;
    private GoogleMap mMap;

    private double latitude, longitude;
    private String locationName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_location);

        setupGeolocation();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // here I do *not* have a map

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


    private String geocode(double latitude, double longitude) {
        if (Geocoder.isPresent()) {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());

            try {
                List<Address> results = geocoder.getFromLocation(latitude, longitude, 1);

                if (results.size() > 0) {
                    return results.get(0).getAddressLine(0) +" "+ results.get(0).getLocality()
                            +" "+ results.get(0).getAdminArea() +" "+ results.get(0).getCountryName()
                            +" "+ results.get(0).getPostalCode() +" "+ results.get(0).getPhone()
                            +" "+ results.get(0).getUrl();
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
        locationName = geocode(location.getLatitude(), location.getLongitude());
        LatLng position = new LatLng(latitude, longitude);

        // centre the map around the specified location
        mMap.animateCamera(CameraUpdateFactory.newLatLng(position));

        // add a marker at the specified location
        MarkerOptions options = new MarkerOptions();
        mMap.addMarker(options.position(position).title(locationName));

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
//
//    private Address forwardGeocode(String locationName) {
//        if (Geocoder.isPresent()) {
//            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
//
//            try {
//                List<Address> results = geocoder.getFromLocationName(locationName, 1);
//
//                if (results.size() > 0) {
//                    return results.get(0);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return null;
//    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng position = new LatLng(latitude, longitude);

        // centre the map around the specified location
        mMap.animateCamera(CameraUpdateFactory.newLatLng(position));

        // add a marker at the specified location
        MarkerOptions options = new MarkerOptions();
        mMap.addMarker(options.position(position).title(locationName));

        // configure the map settings
        mMap.setTrafficEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // enable the zoom controls
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setZoomGesturesEnabled(true);
    }
}
