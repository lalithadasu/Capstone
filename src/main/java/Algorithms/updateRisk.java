package Algorithms;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import net.codejava.Classes.User;
import Algorithms.genericFunctions;
import Algorithms.ImpossibleTravel;
import Algorithms.Location;
import Algorithms.Browser;
import Algorithms.Device;

public class updateRisk {
	
	public static void updateRiskConfidence()
    {
        String users=genericFunctions.getUsers();
        
        try
        {
        	JSONParser parser = new JSONParser();
            JSONArray json = (JSONArray) parser.parse(users);
            
            for(int i = 0; i < json.size(); i++)
            {
            	JSONObject object = (JSONObject) json.get(i);
            	String name=(String) object.get("username");
            	double travelRiskScore=ImpossibleTravel.getRiskScore(name);
            	
            	if(travelRiskScore!=-1)
            	{
            		User x=new User(name);
        			genericFunctions.addRisk(x,travelRiskScore,"TravelRisk");
            	}
            	
            	Location.locationConfidence(name);
            	Browser.browserConfidence(name);
            	Device.deviceConfidence(name);
            	
            }
        }
        
        catch(Exception e)
		{
			System.out.println(e);
		}
    }
	
	public static void main(String args[])
	{
		updateRiskConfidence();
	}
	
}
