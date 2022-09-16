package Algorithms;

import org.json.simple.parser.JSONParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ImpossibleTravel{
	
    public static double velocity(float lat1, float long1, long time_diff, float lat2, float long2)
    {
        double dlon = Math.abs(long2 - long1);
    	double dlat = Math.abs(lat2 - lat1);
    	double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2),2);
        double c_ = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;
    	double dist=c_*r;
        double speed = dist/time_diff;
        return speed;
    }
    
    public static double Riskscore(float lat1, float long1, long time_diff, float lat2, float long2)
    {
        double speed = velocity(lat1, long1, time_diff, lat2, long2);
        double maxSpeed = 163.0;
        double R = 5*speed/maxSpeed;
        if(R>=5)
            R = 5;
        return R;
    }
    
    public static void checkUsers()
    {
		try {
			
			String URLvalue="http://localhost:8080/IdentityThreats/rest/getUsers";
			URL url = new URL(URLvalue);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-Type", "text/plain; charset=utf-8");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ conn.getResponseCode());
			}
			
			BufferedReader in = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			System.out.println(response.toString());
			
			return;

		  }
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return;
    }
    
    public static void main(String args[])
    {
        float lat1=5000;
        float lat2=5500;
        long time_diff = 100;
        double R = Riskscore(lat1, lat1, time_diff, lat2, lat2);
        //System.out.println(R);
        
        checkUsers();
    }
}