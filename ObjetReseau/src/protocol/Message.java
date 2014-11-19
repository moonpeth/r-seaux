package protocol;

import java.awt.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class Message implements Serializable{

	private static final long serialVersionUID = -6503571106963452874L;
	Type requete;
	ArrayList<String> arg = new ArrayList<>();;
	//constructer for client part
	public Message(Scanner commande){
		while (commande.hasNext()) {
			String fromUser = commande.nextLine();
			String[] parts = fromUser.split(",");		     
			this.requete=Type.getType(parts[0]);
			this.arg.add(parts[1]);
			this.arg.add(parts[2]);
			// this(Type.getType(parts[0]),parts[1],parts[2]);
			break;
		}
	}
	
	public String toString(){
		String ret="REQ : "+requete+" "+arg;
		return ret;
	}



}


