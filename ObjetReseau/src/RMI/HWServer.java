package RMI;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;

public class HWServer {

	public static void main(String[] args) {
		try{
			System.out.println("Creation objet server");
			LocateRegistry.createRegistry(8888);
			//creation d'une implementation d'helloworld
			HelloWorld hello = new HelloWorldImpl();
			System.out.println("Reference RMI");
			// binding URL, objet
//			Naming.rebind("Helloworld", hello);
			Naming.rebind("//localhost:8888/HelloWorld", hello);
			System.out.println("Attente invocation");
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
