package com.example.lenovo.googlemaps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadUrl   {
   //fetching of data starts
    public String readurl(String myurl) throws IOException {
     String data="";
        InputStream inputStream=null;  //InputStream is used for reading.//OutputStream for writing.
        HttpURLConnection urlConnection=null;  //data return from web will be on jason format and HttpURLConnection get this data.
        try {
            URL url= new URL(myurl);
            urlConnection=(HttpURLConnection)url.openConnection();  //line **
            urlConnection.connect();
            inputStream=urlConnection.getInputStream();
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));
            StringBuffer sb=new StringBuffer();

            String line="";
            while((line=br.readLine())!=null)
            {
                sb.append(line);
            }
            data=sb.toString();
            br.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e)    //this catch is added because of line **
        {
            e.printStackTrace();
        }
        finally {
        inputStream.close();     //because of this, Throw IOException is added
        urlConnection.disconnect();
        }

       return  data;
    }
}
