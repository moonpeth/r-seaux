package serveur;

import java.util.ArrayList;
import protocol.Message;
import exception.NicknameAlreadyExist;
import exception.NicknameNotFound;
import exception.ServeurEmpty;

public class Handmessage {
	Message reponse;
	Database db;

	//constructor
	public Handmessage() {
		db = Database.instance();
		}
    //function handmessage
	public void hand(Message messageObject){
		ArrayList<String> arg = messageObject.getArgs();
		ArrayList<String> retour = new ArrayList<String>();
		switch (messageObject.getRequete()) {
		
		// ADD NAME NICKNAME
		case ADD: {
			try{
				db.add(arg.get(0), arg.get(1));
				reponse = new Message("ok", retour);
			}
			catch(NicknameAlreadyExist e){
				retour.add(e.toString());
				reponse = new Message("exception", retour);
			}
			break;}
		

		// ADDS NICKNAME NICKNAME
		case ADDS: {
			try{
				db.adds(arg.get(0), arg.get(1));
				reponse = new Message("ok", retour);
			}
			catch(NicknameNotFound e){
				retour.add(e.toString());
				reponse = new Message("exception", retour);
			}
			break;}
		

		// GET
		case GET: {
			try{
				if(arg.isEmpty()){
					reponse =new Message("ok",db.get(null));
				}else{	
				reponse = new Message("ok", db.get(arg.get(0)));}
			}catch(ServeurEmpty e){
				retour.add(e.toString());
				reponse = new Message("exception", retour);
			}
			break;
		}

		// EXCEPTION
		default:

		}
	}
}
