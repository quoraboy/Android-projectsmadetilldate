package com.example.lenovo.googlemaps;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser  {

    private HashMap<String, String> getPlace(JSONObject googlePlaceJson){
    HashMap<String ,String> googleplacemap=new HashMap<>();
    String placeName="-NA-";
    String vinicity="-NA-";
    String latitude="";                    /*Breaking a data block into smaller
                                              chunks by following a set of rules,
                                       so that it can be more easily interpreted, managed,
                                       or transmitted by a computer. Spreadsheet programs,
                          for example, parse a data to fit it into a cell of certain size.*/
    String longitude="";
    String reference="";



           try {
               if (!googlePlaceJson.isNull("name"))
               {
               placeName=googlePlaceJson.getString("name");
               if(!googlePlaceJson.isNull("vinicity")){
                   vinicity=googlePlaceJson.getString("vinicity");
               }
               latitude=googlePlaceJson.getJSONObject("grometry").getJSONObject("location").getString("lat");

               longitude=googlePlaceJson.getJSONObject("geometry").getJSONObject("location").getString("lng");
               reference=googlePlaceJson.getString(reference);
                   googleplacemap.put("Place_name",placeName);
                   googleplacemap.put("vicinity",vinicity);
                   googleplacemap.put("latitude",latitude);
                   googleplacemap.put("longitude",longitude);
                   googleplacemap.put("reference",reference);



           }
           } catch (JSONException e) {
               e.printStackTrace();
           }
       return  googleplacemap;

       }
       private List<HashMap<String, String>>getPlacesss(JSONArray jsonArray)
       {

           int count = jsonArray.length();//***
            List<HashMap<String,String>> placeList=new ArrayList <>();
            HashMap<String, String> placeMap=null;
            for(int i=0;i<count;i++)
            {
                try {
                    placeMap=getPlace((JSONObject) jsonArray.get(i));
                    placeList.add(placeMap);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return placeList;
       }
       public List<HashMap<String,String>>parse(String jsonData)
       {
           JSONArray jsonArray=null;
           JSONObject jsonObject;
           try {
               jsonObject=new JSONObject(jsonData);
               jsonArray=jsonObject.getJSONArray("results");

           } catch (JSONException e) {
               e.printStackTrace();
           }
           return getPlacesss(jsonArray);

       }
     }

