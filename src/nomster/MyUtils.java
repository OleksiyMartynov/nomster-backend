package nomster;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;

import models.MyPair;

import com.google.appengine.labs.repackaged.org.json.JSONException;
import com.google.appengine.labs.repackaged.org.json.JSONObject;

public class MyUtils 
{
	public static String httpGetRequest(String urlStr,String data, List<MyPair<String,String>> properties)throws IOException
	{
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    conn.setRequestMethod("GET");
	    conn.setDoOutput(true);
	    if(properties!=null)
	    {
		    for(MyPair<String,String> pair : properties)
		    {
		    	conn.setRequestProperty (pair.getOne(), pair.getTwo());
		    }
	    }
	    if(data!=null)
	    {
		    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		    writer.write(data);
		    writer.flush();
		    writer.close();
	    }
	    String line;
	    String response="";
	    BufferedReader reader = new BufferedReader(new 
	                                     InputStreamReader(conn.getInputStream()));
	    while ((line = reader.readLine()) != null) {
	      response+=line;
	    }	    
	    reader.close();
	    return response;
	}
	public static String httpPostRequest(String urlStr,String data, List<MyPair<String,String>> properties) throws IOException
	{
		URL url = new URL(urlStr);
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
	    conn.setRequestMethod("POST");
	    conn.setDoOutput(true);
	    if(properties!=null)
	    {
		    for(MyPair<String,String> pair : properties)
		    {
		    	conn.setRequestProperty (pair.getOne(), pair.getTwo());
		    }
	    }
	    if(data!=null)
	    {
		    OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
		    writer.write(data);
		    writer.flush();
		    writer.close();
	    }
	    String line;
	    String response="";
	    BufferedReader reader = new BufferedReader(new 
	                                     InputStreamReader(conn.getInputStream()));
	    while ((line = reader.readLine()) != null) {
	      response+=line;
	    }	    
	    reader.close();
	    return response;
	}
	public static String getStringFromJSONString(String json,String key) 
	{
		JSONObject jObj;
		try {
			jObj = new JSONObject(json);
			return jObj.getString(key);
		} catch (JSONException e) {
			return null;
		}
		
	}
}
