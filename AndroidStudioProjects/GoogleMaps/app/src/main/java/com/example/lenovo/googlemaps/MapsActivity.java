package com.example.lenovo.googlemaps;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest; // I added this class
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

import java.io.IOException;
import java.util.List;


import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,    //I added this line
        GoogleApiClient.OnConnectionFailedListener,    //I added this line
        LocationListener {                              //I added this line


    private GoogleMap mMap;
    private GoogleApiClient client;
    private LocationRequest locationRequest;
     Location lastLocation;
    private Marker currentLocationMarker;
     Task<Void> fusedLocationProviderClient;
     LocationListener locationListener;
    public  static final int REQUEST_loCation_CODE=99;
    EditText txtenter;
    int PROXIMITY_RADIUS=1000000;
    double latitude,longitude;
//STEP NO. 1     //THERE IS A CONFUSION BETWEEN STEP 1 AND 2 WHICH RUNS FIRST DON'T KNOW
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M)     //checking system version
        {
            checkLocationPermission();  //THE RETURN VALUE OF THE FUNTION IS NEVER USED
            // checkLocationPermission meathod is asking for location permission through up.

        }
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

    //STEP NO. 5
    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        //   LatLng sydney = new LatLng(-34, 151);
        //  mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        if (ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED )
        {       // TODO: Consider calling
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
    lastLocation=location;
    if(currentLocationMarker!=null)
    {
        currentLocationMarker.remove();
    }
    LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
    MarkerOptions markerOptions=new MarkerOptions();
    markerOptions.position(latLng);
    markerOptions.title("Current Location");
    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
    currentLocationMarker=mMap.addMarker(markerOptions);
    mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    mMap.animateCamera(CameraUpdateFactory.zoomBy(100));
    if(client!=null)
    {
        LocationServices.getFusedLocationProviderClient(this).removeLocationUpdates((LocationCallback) locationListener);
    }
    }

   public void onClick(View v)
   {   GetNearbyPlaceData getNearbyPlaceData = new GetNearbyPlaceData();
       Object datatransfer[]=new Object[2];
       String url;

       switch (v.getId()) {
           case R.id.btnsearch:
               txtenter=(EditText)findViewById(R.id.txtenter);
               String location=txtenter.getText().toString().trim();
               List<Address> addressList = null;
               MarkerOptions mo=new MarkerOptions();
               if(!location.equals(""))
               {
                   Geocoder geocoder=new Geocoder(this); //convert an address in to latitude, longitude cordinate
                   try {
                       addressList=geocoder.getFromLocationName(location,5);
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                  for(int i=0;i<addressList.size();i++)
                   {
                       Address myAddress=addressList.get(i);
                       LatLng latLng=new LatLng(myAddress.getLatitude(),myAddress.getLongitude());
                       mo.position(latLng);
                       mo.title("your search result");
                       mMap.addMarker(mo);
                       mMap.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                   }
               }
               break;


           case R.id.hospital:
               mMap.clear();
              String hospital="hospital";
               latitude =20.296059;
               longitude=85.824539;
               url=getUrl(latitude,longitude,hospital);

                mMap.addMarker(new MarkerOptions()
                        .position(new LatLng(latitude,longitude))
                        .title("Sum"));
              datatransfer[0]=mMap;
              datatransfer[1]=url;

              getNearbyPlaceData.execute((Object) datatransfer[0], (Object) datatransfer[1]);
               Toast.makeText(this, "Showing nearby hospital", Toast.LENGTH_SHORT).show();
               break;
           case R.id.restaurant:
               mMap.clear();
              String restaurant="restaurant";
               url = getUrl(latitude, longitude,restaurant);

              datatransfer[0]=mMap;
              datatransfer[1]=url;

               getNearbyPlaceData.execute((Object) datatransfer[0], (Object) datatransfer[1]);
               Toast.makeText(this, "Showing nearby restaurant", Toast.LENGTH_SHORT).show();
               break;

           case R.id.school:
               mMap.clear();
               String school="school";
               url = getUrl(latitude, longitude, school);

               datatransfer[0]=mMap;
               datatransfer[1]=url;

              getNearbyPlaceData.execute((Object) datatransfer[0], (Object) datatransfer[1]);
               Toast.makeText(this, "Showing nearby schools", Toast.LENGTH_SHORT).show();
               break;

       }
   }
   private String getUrl(double latitude, double longitude, String nearbyplaces)
   {
       StringBuilder googleplacesUrl= new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
       googleplacesUrl.append("location="+latitude+","+longitude);
       googleplacesUrl.append("&radius="+PROXIMITY_RADIUS);
       googleplacesUrl.append("&type="+nearbyplaces);
       googleplacesUrl.append("&sensor=true");
       googleplacesUrl.append("&key="+"AIzaSyAnE9G4rGhlv9X4QeihT1KS4OXc3tzvilk");  //My project
       return  googleplacesUrl.toString();

   }
   //STEP NO. 4
    @Override
    public void onConnected(@Nullable Bundle bundle) // in onConnect meathod the request is send
                                                    // through API to get the location of the device
    {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(1000);   //1000 is in millisecond
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
            fusedLocationProviderClient=LocationServices
                    .getFusedLocationProviderClient(this)
                    .requestLocationUpdates(locationRequest,new LocationCallback(){
                @Override
                public void onLocationResult(LocationResult locationResult) {
                    // do work here
                    onLocationChanged(locationResult.getLastLocation());
                }},Looper.myLooper());


            return;
        }

    }
    // checkLocationPermission meathod is asking for location permission through up.
 public boolean checkLocationPermission(){
    if(ContextCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
    {
     if(ActivityCompat.shouldShowRequestPermissionRationale(this,ACCESS_FINE_LOCATION))
     {
         ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_loCation_CODE);

     }
     else{
         ActivityCompat.requestPermissions(this,new String[]{Manifest.permission
                 .ACCESS_FINE_LOCATION},REQUEST_loCation_CODE);
     }
    return false;
    }
    else{
        return  true;
    }
 }
  int t=0;

//STEP NO. 2     //THERE IS A CONFUSION BETWEEN STEP 1 AND 2 WHICH RUNS FIRST DON'T KNOW
    @Override
        public void onRequestPermissionsResult ( int requestCode, @NonNull String[] permissions,
                                                 @NonNull int[] grantResults){
        // switch (requestCode){
        //   case REQUEST_loCation_CODE:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            // when the app in install for the first time than a dialog is open, the permission is asked if allow than zero is pass if deny than -1 is pass.
        {
            //permission granted
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
            {
                if (client == null) {
                    buildgoogleapiclient();
                }
                mMap.setMyLocationEnabled(true);// the button on the up-right corner is created by this line.
             }
        } else {
            //permission denied
            Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
            t = -1;

        }
        return;
        //}
    }

//STEP NO. 3
    protected synchronized void buildgoogleapiclient()    // GoogleApiClient successfully connect to Google service
                                                          // it cannot get the full information at a blink,
                                                          // you should be aware of that.
                                                          // So basically, You will want to make a request to what API you want to use
                                                           //(FuseLocation API, Google Fit API ...).
                                                          // And onConnected() method is where you start the request.
    {
        client = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

        client.connect();
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
