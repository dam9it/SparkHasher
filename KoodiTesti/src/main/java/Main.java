import static spark.Spark.*;

import java.io.Console;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.*;
import org.json.simple.JSONObject;

public class Main {
    public static void main(String[] args) {
        
        post("/hash", "application/json", (req,res) -> {
        	try {
        	String[] parts = req.body().split("\"");
        	String[] parts2 = parts[2].split("-");
        	String lines[] = parts2[0].split("\\r?\\n");

        	String input = lines[2];
        	String sha256hex = DigestUtils.sha256Hex(input);
        	
        	JSONObject obj = new JSONObject();
        	  obj.put("hash", sha256hex);
        	  obj.toString(); 
        	
        	return obj;
        	}catch (Exception e) {
        		JSONObject obj = new JSONObject();
          	  obj.put("Error", "Invalid input");
          	  obj.toString(); 
        		return obj;
			}
        	});
    }
}