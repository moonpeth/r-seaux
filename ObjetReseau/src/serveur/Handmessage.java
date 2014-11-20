package serveur;

import java.util.ArrayList;
import protocol.Message;
import exception.NicknameAlreadyExist;
import exception.NicknameNotFound;
import exception.ServeurEmpty;

public class Handmessage {

	private static ArrayList<Personne> li = new ArrayList<>();
	Message reponse;

	String excep;
	ArrayList<String> retour = new ArrayList<String>();
	ArrayList<String> nulle = new ArrayList<String>();

	public Handmessage(Message messageObject) {

		ArrayList<String> arg = messageObject.getArgs();

		switch (messageObject.getRequete()) {
		// ADD
		case ADD: {

			Personne p = new Personne(arg.get(0), arg.get(1));
			if (li.contains(p)) {
				excep = new NicknameAlreadyExist().toString();
				retour.add(excep);
				reponse = new Message("exception", retour);
			} else {
				li.add(p);
				//retour.add("add person "+arg.get(0)+" "+arg.get(1));
				reponse = new Message("ok", retour);
			}
			break;
		}

		//ADDS
		case ADDS: {
			for (Personne p : li) {
				for (String surnom : p.getSurnoms()) {
					if (arg.get(0)==surnom) {
						p.getSurnoms().add(arg.get(1));
						//retour.add("add "+arg.get(1)+" to "+arg.get(0));
						reponse = new Message("ok", retour);
						return;
					}                 
				}
			}
			excep = new NicknameNotFound().toString();
			retour.add(excep);
			reponse = new Message("exception", retour);
			break;
		}

		//GET	
		case GET:{
			if(li.isEmpty()){
				excep = new ServeurEmpty().toString();
				retour.add(excep);
				reponse = new Message("exception", retour);
				break;

			}else if(!arg.isEmpty()){
				for(Personne p : li){
					for(String sn : p.getSurnoms()){
						if(sn == arg.get(0)){
							retour.add(p.getNom());
						}
					}
				}
				
			} else {
				for(Personne personne : li){
					retour.add(personne.getNom());
				}
			}
			reponse = new Message("ok", retour);
			break;
		}

		//EXCEPTION
		default:

		}
	}
}
