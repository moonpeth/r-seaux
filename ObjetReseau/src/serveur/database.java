package serveur;

import java.util.ArrayList;

import exception.NicknameAlreadyExist;
import exception.NicknameNotFound;
import exception.ServeurEmpty;

/*
 * store and trait the informations of all the persons
 * */
public class database {
	private static ArrayList<Personne> list = new ArrayList<>();

	// add name nickname, means add a new person
	public void add(String nom, String surnom) throws NicknameAlreadyExist {
		Personne p = new Personne(nom, surnom);
		if (!list.contains(p)) {
			list.add(p);
		} else {
			throw new NicknameAlreadyExist();
		}
	}

	// add nickname nicknameNew 
	public void adds(String surnom, String surnomN) throws NicknameNotFound {
		for (Personne p : list) {
			for (String s : p.getSurnoms()) {
				if (surnom == s) {
					p.getSurnoms().add(surnomN);
					return;
				}
			}		
		}
		throw new NicknameNotFound();
	}
    
	// two types of GET
	public ArrayList<String> get(String atr) throws ServeurEmpty{
		ArrayList<String> retour = new ArrayList<String>();
		if(list.isEmpty()){
			throw new ServeurEmpty();
			
		}//c->s GET ,s->c all the name
		else if(atr==null){
			for (Personne p : list) {
				retour.add(p.getNom());
			}
			return retour;
		}
		//c->s  GET nickname  ,s-> name of the nickname
		else{
			for (Personne p : list) {
				for (String s : p.getSurnoms()) {
					if (s==atr) {
						retour.add(p.getNom());
						return retour;
					}
				}
			}
		}
		return retour;
	}
    
}
