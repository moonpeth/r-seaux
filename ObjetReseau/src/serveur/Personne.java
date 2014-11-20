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
	public ArrayList<String> getSurnoms(){
		return surnoms;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		result = prime * result + ((surnoms == null) ? 0 : surnoms.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personne other = (Personne) obj;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (surnoms == null) {
			if (other.surnoms != null)
				return false;
		} else if (!surnoms.equals(other.surnoms))
			return false;
		return true;
	}
}
