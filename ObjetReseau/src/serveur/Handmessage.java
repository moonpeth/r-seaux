package serveur;

import java.util.ArrayList;
import protocol.Message;

public class Handmessage {
	ArrayList<Personne> li = new ArrayList<>();
	Message reponse;

	public Handmessage(Message messageObject){
		switch (messageObject.getRequete()) {
		//ADD
		case ADD:{
			ArrayList<String>arg = messageObject.getArgs();
			Personne p = new Personne(arg.get(0), arg.get(1));
			//TODO : ameliorer le test
			if(li.contains(p)){
				
			}else{
				li.add(p);
				reponse = new Message("ok", new ArrayList<String>());
			}
		}

		//ADDS	
		case ADDS:

			//GET	
		case GET:

			//OK
		case OK:

			//EXCEPTION	
		default:

		}
	}

}
