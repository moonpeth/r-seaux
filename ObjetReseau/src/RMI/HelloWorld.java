package RMI;

import java.rmi.*;

public interface HelloWorld extends Remote{

	//methode de Hello
	public String sayHello() throws RemoteException;
	
}
