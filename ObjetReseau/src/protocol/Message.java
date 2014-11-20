package protocol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;


public class Message implements Serializable{

	private static final long serialVersionUID = -6503571106963452874L;
	private Type requete;
	private ArrayList<String> args = new ArrayList<>();

	public Message(Scanner commande){
		while (commande.hasNext()) {
			String fromUser = commande.nextLine();
			String[] parts = fromUser.split(" ");		     
			this.requete=Type.getType(parts[0]);
			this.args.add(parts[1]);
			this.args.add(parts[2]);
			break;
		}
	}
	
	public Message(String req, ArrayList<String> arg){	     
		this.requete = Type.getType(req);
		this.args = arg;
	}

	public String toString(){
		String ret="REQ : "+requete+" "+args;
		return ret;
	}



}


