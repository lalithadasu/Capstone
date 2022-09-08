package net.codejava;
import net.codejava.Classes.*;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
 
@Path("/bonjour")
public class HelloResource {
 
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String direBonjour() {
    	User_Mitigation example=new User_Mitigation();
        return "Bonjour, tout le monde!";
        
        
    }
}
