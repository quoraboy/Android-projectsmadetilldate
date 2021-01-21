package com.example.lenovo.gpslocationsend;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public abstract class MapsActivity1 extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
GoogleApiClient.OnConnectionFailedListener,
        LocationListener {
    Location lastLocation;
    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
     // Location location;
    private Marker currentlocationMarker;
     Task<Void> fusedLocationProviderclient;
      LocationListener locationListener;
    public  static final int REQUEST_loCation_CODE=99;
     double latitude,longitue;
    LatLng latlng;
    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference(); //databaseReference is pointing "fir-project2nddatabase"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps1);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)     //checking system version
        {
            checkLocationPermission();  //THE RETURN VALUE OF THE FUNTION IS NEVER USED
            // checkLocationPermission meathod is asking for location permission through up.
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);




    }
    int t=0;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED)
        {        // when the app in install for the first time than a dialog is open, the permission is asked if allow than zero is pass if deny than -1 is pass.

            if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
            {
                if(client==null)
                {
                    buildgoogleapiclient();
                }
                mMap.setMyLocationEnabled(true);
            }
        }else{
            //permission denied
            Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
        t=-1;
        }

            }

    private synchronized void buildgoogleapiclient() {
        // GoogleApiClient successfully connect to Google service
        // it cannot get the full information at a blink,
        // you should be aware of that.
        // So basically, You will want to make a request to what API you want to use
        //(FuseLocation API, Google Fit API ...).
        // And onConnected() method is where you start the request.
   client=new GoogleApiClient.Builder(this)
           .addConnectionCallbacks(this)
           .addOnConnectionFailedListener(this)
           .addApi(LocationServices.API)
           .build();
   client.connect();


    }

    private boolean checkLocationPermission() {
        if(ContextCompat.checkSelfPermission(this,ACCESS_FINE_LOCATION)!=PackageManager.PERMISSION_GRANTED)
        {
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, ACCESS_FINE_LOCATION))
            {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_loCation_CODE);

            }
            else{
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_loCation_CODE);

            }
      return false;
        }
        else{
            return true;
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

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
       if(ContextCompat.checkSelfPermission(this,ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED)
       {
           // TODO: Consider calling
           //    ActivityCompat#requestPermissions
           // here to request the missing permissions, and then overriding
           //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
           //                                          int[] grantResults)
           // to handle the case where the user grants the permission. See the documentation
           // for ActivityCompat#requestPermissions for more details.
           buildgoogleapiclient();
           mMap.setMyLocationEnabled(true);



       }

    }

    @Override
    public void onLocationChanged(Location location) {
        lastLocation = location;
        String longitude = new Double(lastLocation.getLongitude()).toString();
        String latitude = new Double(lastLocation.getLatitude()).toString();
        databaseReference.child("Lat").setValue(latitude);
        databaseReference.child("long").setValue(longitude);
         Toast.makeText(this,"onlocationchanged",Toast.LENGTH_SHORT);
        if(currentlocationMarker!=null)
        {
            currentlocationMarker.remove();
        }
         latlng=new LatLng(location.getLatitude(),location.getLongitude());
        MarkerOptions markerOptions=new MarkerOptions();
        markerOptions.position(latlng);
        markerOptions.title("current location");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        currentlocationMarker=mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlng));
        mMap.animateCamera(CameraUpdateFactory.zoomBy(100));
        if(client!=null)
        {
            LocationServices.getFusedLocationProviderClient(this).removeLocationUpdates((LocationCallback) locationListener);

        }




}
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        // in onConnect meathod the request is send
        // through API to get the location of the device
       locationRequest=new LocationRequest();
       locationRequest.setInterval(1000); //1000 milli seconds
        locationRequest.setFastestInterval(1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //  LocationServices.FusedLocationApi.requestLocationUpdates(client, locationRequest, this);
            fusedLocationProviderclient = LocationServices
                    .getFusedLocationProviderClient(this)
                    .requestLocationUpdates(locationRequest, new LocationCallback() {
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            // do work here
                            onLocationChanged(locationResult.getLastLocation());
                        }
                    }, Looper.myLooper());


            return;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,"we are in onConnectionSuspend",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "we are in onConnectionFailed", Toast.LENGTH_SHORT).show();

    }
}
