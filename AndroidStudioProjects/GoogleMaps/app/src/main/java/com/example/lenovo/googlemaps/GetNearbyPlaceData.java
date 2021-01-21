package com.example.lenovo.googlemaps;

import android.os.AsyncTask;

import com.google.android.gms.maps.model.Marker;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class GetNearbyPlaceData extends AsyncTask<Object, String, String>{

     String googlePlacesData;
     GoogleMap mMap;
     String url;

    public GetNearbyPlaceData() {
    }

    @Override
    protected String doInBackground(Object... object) {
        mMap = (GoogleMap) object[0];
        url = object[1].toString();

        DownloadUrl downloadUrl = new DownloadUrl();
        try {
            googlePlacesData = downloadUrl.readurl(url); // response is send to GetNearbyPlaceData class through DownloadUrl class
        } catch (IOException e) {
            e.printStackTrace();
        }
        return googlePlacesData;  //the data is return to onPoastExecute meathod
    }

    @Override
    protected void onPostExecute(String s) { // here s capture the value of googlePlacesData
        List<HashMap<String, String>>nearbyPlacelist=null;
        DataParser parser=new DataParser();
        nearbyPlacelist=parser.parse(s);  //now the data is send to DataParser class
        showNearbyPlaces(nearbyPlacelist);
        }


    private void showNearbyPlaces(List<HashMap<String, String>>nearbyPlaceList)
    {
        for(int i=0;i<nearbyPlaceList.size();i++)
        {
            MarkerOptions markerOptions=new MarkerOptions();
            HashMap<String, String> googlePlace=nearbyPlaceList.get(i);

            String placeName=googlePlace.get("place_name");
            String vicinity=googlePlace.get("vicinity");
            double lat=Double.parseDouble(googlePlace.get("lat"));
            double lng=Double.parseDouble(googlePlace.get("lng"));

            LatLng latLng=new LatLng(lat,lng);
            markerOptions.position(latLng);
            markerOptions.title(placeName+":"+vicinity);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));



            mMap.addMarker(markerOptions);

            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomBy(10));

        }
    }

}
