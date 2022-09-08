package net.codejava.Classes;

import org.json.simple.JSONObject;
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

public class User {
	String Username;
	String Mail;
	String displayName;
	String firstname;
	String lastname;
	long phonenumber;
	String password;
	
	public User(String Username,String Mail,String displayName,String firstname,String lastname,long phonenumber, String password)
	{
		this.Username=Username;
		this.Mail=Mail;
		this.displayName=displayName;
		this.firstname=firstname;
		this.lastname=lastname;
		this.phonenumber=phonenumber;
		this.password=password;
	}
	
	public User()
	{
		
	}
	
	public String jsonToString (JSONObject record )
	{
		return record.toJSONString();
	}
	
	public void pushToDB()
	{         
         HashMap<String,String> userDetails = new HashMap<String,String>();
         userDetails.put("username", this.Username);
         userDetails.put("mail",this.Mail);
         userDetails.put("displayName",this.displayName);
         userDetails.put("firstname", this.firstname);
         userDetails.put("lastname", this.lastname);
         userDetails.put("phonenumber", String.valueOf(this.phonenumber));
         userDetails.put("password", this.password);
         
         JSONObject user = new JSONObject(userDetails);
         
         try
         {
        	 /*URL url = new URL ("http://localhost:8080/IdentityThreats/rest/add_user");
        	 HttpURLConnection con = (HttpURLConnection)url.openConnection();
        	 con.setRequestMethod("POST");
        	 con.setRequestProperty("Content-Type","application/json");
        	 con.setRequestProperty("Accept","application/json");
        	 
        	 HttpURLConnection.setFollowRedirects(true);
        	 con.setInstanceFollowRedirects(false);
        	 con.setDoOutput(true);
        	 
        	 OutputStream out = con.getOutputStream();
        	 
        	 out.write(user.toString().getBytes());
             InputStream ip = con.getInputStream();
             
             BufferedReader br1 = new BufferedReader(new InputStreamReader(ip));
                
             System.out.println("Response Code:" + con.getResponseCode());
             System.out.println("Response Message:" + con.getResponseMessage());
             System.out.println("InstanceFollowRedirects:" + con.getInstanceFollowRedirects());
             System.out.println("Header field 1:" + con.getHeaderField(1));
             System.out.println("Using proxy:" + con.usingProxy());
             
             StringBuilder response = new StringBuilder();
             String responseSingle = null;

             while ((responseSingle = br1.readLine())!= null) 
             {
            	 response.append(responseSingle);
             }
         
             String xx = response.toString();
             System.out.println(xx);*/
        	 
        	 String requestBody= user.toJSONString();
        	 
        	 HttpClient client = HttpClient.newHttpClient();
             HttpRequest request = HttpRequest.newBuilder()
                     .uri(URI.create("http://localhost:8080/IdentityThreats/rest/add_User"))
                     .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                     .build();

             HttpResponse<String> response = client.send(request,
                     HttpResponse.BodyHandlers.ofString());

             System.out.println(response.body());
             
             return;
         }
         
         catch (Exception e) 
         {
             System.out.println(e);
         }
         
         return;
	}
	
	public String readUserInfo()
	{
		try {
			
			String name=this.Username;
			String URLvalue="http://localhost:8080/IdentityThreats/rest/get_userInfo/" + name;
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
			
			return response.toString();

		  }
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return "error";
	}
	
	public JSONObject getUserInfo()
	{
		String info=readUserInfo();
		JSONParser jsonParser = new JSONParser();  
		
		try
		{
			JSONObject json = (JSONObject) jsonParser.parse(info);
			return json;
		}
		
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return null;
	}
}
