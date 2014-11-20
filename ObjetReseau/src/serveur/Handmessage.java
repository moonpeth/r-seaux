package serveur;

import java.util.ArrayList;

import exception.NicknameAlreadyExist;
import protocol.Message;

public class Handmessage {

	ArrayList<Personne> li = new ArrayList<>();
	Message reponse;

	String excep;
	ArrayList<String> retour = new ArrayList<String>();

	public Handmessage(Message messageObject){
		//test
		Personne test = new Personne("Nicolas", "Nico");
		li.add(test);

		switch (messageObject.getRequete()) {
		//ADD
		case ADD:{
			ArrayList<String>arg = messageObject.getArgs();
			Personne p = new Personne(arg.get(0), arg.get(1));
			if(li.contains(p)){
				excep = new NicknameAlreadyExist().toString();
				retour.add(excep);
				reponse = new Message("exception", retour);
			}else{
				li.add(p);
				reponse = new Message("ok", retour);
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
