package serveur;

import java.util.ArrayList;

public class Personne {
	private String nom;
	private ArrayList<String> surnoms = new ArrayList<String>();

	public Personne(String nom, String surnom) {
		this.nom = nom;
		this.surnoms.add(surnom);
	}

	public String toString() {
		String personneString = "Nom : " + nom + " Prenom : " + surnoms;
		return personneString;
	}

	public String getNom() {
		return nom;
	}
	public ArrayList<String> getsurnoms(){
		return surnoms;
	}
}
