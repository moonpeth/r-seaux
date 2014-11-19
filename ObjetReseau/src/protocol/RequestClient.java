package protocol;

import java.io.BufferedReader;
import java.io.IOException;


public class RequestClient{
    Type requete;
    String arg1,arg2;

	public RequestClient(BufferedReader commande){
		try {
			while ((commande.readLine()) != null) {
			      String fromUser = commande.readLine();
			      String[] parts = fromUser.split(",");
			    	this.requete=Type.getType(parts[0]);
			    	this.arg1=parts[1];
			    	this.arg2=parts[2];
			    // this(Type.getType(parts[0]),parts[1],parts[2]);
			          	
			      }
		} catch (IOException e) {
			e.printStackTrace();
		}
          }
	}


