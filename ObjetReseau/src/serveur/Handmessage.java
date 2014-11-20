package serveur;

import java.util.ArrayList;

import protocol.Message;
import exception.NicknameAlreadyExist;
import exception.ServeurEmpty;

public class Handmessage {

	private static ArrayList<Personne> li = new ArrayList<>();
	Message reponse;

	String excep;
	ArrayList<String> retour = new ArrayList<String>();

	public Handmessage(Message messageObject){
		//		//test
		//		Personne test = new Personne("Nicolas", "Nico");

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
			break;
		}

		//ADDS	
		case ADDS:{
			
			break;
		}

		//GET	
		case GET:{

			if(li.isEmpty()){
				excep = new ServeurEmpty().toString();
				retour.add(excep);
				reponse = new Message("exception", retour);
			}else{
				for(Personne personne : li)
					retour.add(personne.getNom());
				reponse = new Message("ok", retour);
			}
			break;
		}
		
		//EXCEPTION	
		default:

		}
	}
}

