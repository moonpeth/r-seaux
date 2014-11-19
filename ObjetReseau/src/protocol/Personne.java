package protocol;

public class Personne {
	protected String nom;

    public Personne(String nom) {
	this.nom = nom;
    }

    public String toString(){
	return "Nom : " + nom ;
    }

    public String getNom() {
	return nom;
    }
}
    
