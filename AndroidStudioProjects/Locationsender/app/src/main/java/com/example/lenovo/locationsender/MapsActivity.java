package com.example.lenovo.locationsender;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {



    private GoogleMap mMap;
    boolean mLocationPermissionGranted = false;
    private int code = 101;
    FusedLocationProviderClient fusedLocationProviderclient;
    private DatabaseReference    mDatabase = FirebaseDatabase.getInstance().getReference();
   Location lStart, lEnd;
   double speed =0, distance;
   TextView spd;
   TextView dst;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        getLocationPermission();
  spd= (TextView) findViewById(R.id.speed);
  dst = (TextView) findViewById(R.id.distance);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        //Toast.makeText(this,"We are in getLocationPermission",Toast.LENGTH_SHORT).show();

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                        code);
            }
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    code);
        }

    }

    // when the app in install for the first time than a dialog is open, the permission is asked
    // if allow than zero is pass if deny than -1 is pass.
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //Toast.makeText(this,"We are in onRequestPermission",Toast.LENGTH_SHORT).show();

        mLocationPermissionGranted = false;
        switch (requestCode) {
            case 101: {
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            mLocationPermissionGranted = true;
                            return;
                        }
                    }

                }
            }
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //Toast.makeText(this,"We are in onMapReady",Toast.LENGTH_SHORT).show();
        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(20.2333, 85.8333);  //latitude longitude
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        if (mLocationPermissionGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mMap.setMyLocationEnabled(true);
        }
    }

    private void getDeviceLocation()
    {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        LocationRequest request=new LocationRequest();
        request.setInterval(5000);
        request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        fusedLocationProviderclient = LocationServices.getFusedLocationProviderClient(this);
      // Toast.makeText(this,"11111111111111111111we are in getdevicelocation111",Toast.LENGTH_LONG).show();

   try{
      if(mLocationPermissionGranted)
      {

          fusedLocationProviderclient.requestLocationUpdates(request,new LocationCallback(){

          @Override
          public void onLocationResult(LocationResult locationresult)
          {
              Location location=locationresult.getLastLocation();
              movecamera(new LatLng(location.getLatitude(),location.getLongitude()),15f);
              if (lStart == null) {
                  lStart = location;
                  lEnd = location;
              } else {
                  lEnd = location;
              }

              distance = distance + (lStart.distanceTo(lEnd) / 1000.00);
             dst.setText(new DecimalFormat("#.###").format(distance) + " Km's.");
              speed=location.getSpeed()*18/5;
               spd.setText( new DecimalFormat("#.##").format(speed) + " km/hr");
              lStart = lEnd;

              mDatabase.child("Latitude").setValue(Double.toString(location.getLatitude()));
              mDatabase.child("Longitude").setValue(Double.toString(location.getLongitude()));
//              Toast.makeText(MapsActivity.this, Double.toString(speed), Toast.LENGTH_SHORT).show();

          }},null);
      }
      }
   catch (SecurityException e)
   {
   Toast.makeText(this,"We are in getDeviceLocation>>catch",Toast.LENGTH_SHORT).show();

   }
   }

    private void movecamera(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
    }
}
