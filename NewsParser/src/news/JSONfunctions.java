package news;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import sun.net.www.http.HttpClient;
import sun.rmi.runtime.Log;

public class JSONfunctions {

	public static JSONObject getJSONfromURL(String url){
        InputStream is = null;
        String result = "";
        JSONObject jArray = null;
 
        // Download JSON data from URL
        try{
                DefaultHttpClient httpclient = new DefaultHttpClient();
                HttpGet httpget = new HttpGet(url);
                HttpResponse response = httpclient.execute(httpget);
                HttpEntity entity = response.getEntity();
                is = entity.getContent();
 
        }catch(Exception e){
                //Log.e("log_tag", "Error in http connection "+e.toString());
        }
 
        // Convert response to string
        try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
                StringBuilder sb = new StringBuilder();
                String line = null;
                while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                }
                is.close();
                result=sb.toString();
        }catch(Exception e){
               // Log.e("log_tag", "Error converting result "+e.toString());
        }
 
        try{
 
            jArray = new JSONObject(result);           
        }catch(JSONException e){
               // Log.e("log_tag", "Error parsing data "+e.toString());
        }
 
        return jArray;
    }
}
