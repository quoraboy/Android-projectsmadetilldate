package com.example.lenovo.locationsender;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Locale;
//import com.google.maps.DirectionsApiRequest;
//import com.google.maps.GeoApiContext;
//import com.google.maps.PendingResult;
//import com.google.maps.model.DirectionsResult;

public class MapsActivityfetchfromfirebase extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    boolean mLocationPermissionGranted = false;
    private int code = 101;
    FusedLocationProviderClient fusedLocationProviderclient;
    private DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    private Marker marker=null;
    double latpa;
    double longpa;
    String latitude;    //driver
    String longitude;   //driver
//    public GeoApiContext mgeoApiContext=null;
  private  Location currentlocation;
//         String TAG="s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps_activityfetchfromfirebase);
        getLocationPermission();

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
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

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

        if (mLocationPermissionGranted) {
            getDeviceLocation();

            mDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                     latitude= (String) dataSnapshot.child("Latitude").getValue();
                     longitude= (String) dataSnapshot.child("Longitude").getValue();


                   if(marker==null)
                   {
                       marker = mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude))).title("Current location"));

                   }
                   else
                   {
                       marker.remove();
                       marker = mMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude))).title("Current location"));

                   }

                   movecamera(new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude)),18f);

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }



            });


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
            //Toast.makeText(this, "01second activity01", Toast.LENGTH_SHORT).show();
        }
    }

    private void getDeviceLocation()
    {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        fusedLocationProviderclient = LocationServices.getFusedLocationProviderClient(this);


        try{
            if(mLocationPermissionGranted)
            {
                Task locationResult = fusedLocationProviderclient.getLastLocation();
                locationResult.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isComplete())
                        {

                             currentlocation= (Location) task.getResult();
                             latpa=currentlocation.getLatitude();
                             longpa=currentlocation.getLongitude();

                     String st=   getCompleteAddressString(latpa,longpa);
                          Toast.makeText(MapsActivityfetchfromfirebase.this, st, Toast.LENGTH_SHORT).show();



                     //   movecamera(new LatLng(Double.parseDouble(latitude),Double.parseDouble(longitude)),17f);


//                            mDatabase.child("Latitude").setValue(Double.toString(currentlocation.getLatitude()));
//                            mDatabase.child("Longitude").setValue(Double.toString(currentlocation.getLongitude()));
//                            assert currentlocation != null;
//                            String l=Double.toString(currentlocation.getLatitude());
//                            //Toast.makeText(MapsActivityfetchfromfirebase.this, l , Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(MapsActivityfetchfromfirebase.this, "Something went wrong", Toast.LENGTH_SHORT).show();

                        }
                    }
                });


            }
        }
        catch (SecurityException e)
        {
            Toast.makeText(this,"We are in getDeviceLocation>>catch",Toast.LENGTH_SHORT).show();

        }
    }

    private String getCompleteAddressString(double LATITUDE, double LONGITUDE) {
        String strAdd = "";
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            if (addresses != null) {
                Address returnedAddress = addresses.get(0);
                StringBuilder strReturnedAddress = new StringBuilder("");

                for (int i = 0; i <= returnedAddress.getMaxAddressLineIndex(); i++) {
                    strReturnedAddress.append(returnedAddress.getAddressLine(i)).append("\n");
                }
                strAdd = strReturnedAddress.toString();
     //           Log.w("My Current loction address", strReturnedAddress.toString());
            } else {
       //         Log.w("My Current loction address", "No Address returned!");
            }
        } catch (Exception e) {
            e.printStackTrace();
         //   Log.w("My Current loction address", "Canont get Address!");
        }
        return strAdd;
    }

    private void movecamera(LatLng latLng, float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom));
    }

}
