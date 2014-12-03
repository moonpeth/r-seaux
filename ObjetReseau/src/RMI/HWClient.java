package RMI;

import java.rmi.*;

public class HWClient {

	public static void main(String[] args) {
		try{
			System.out.println("Recherche objet serveur");
			//creation d'un helloworld, initialisation avec l'objet distant
			HelloWorld hello = (HelloWorld)Naming.lookup("//localhost:8888/HelloWorld");
//			HelloWorld hello = (HelloWorld)Naming.lookup("Helloworld");
			System.out.println("Invocation methode sayHello");
			//appel d'une methode sur objet distant
			String result = hello.sayHello();
			System.out.println("Affichage du resultat :");
			System.out.println(result);
			
			System.exit(0);
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
