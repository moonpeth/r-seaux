package RMI;

import java.rmi.RemoteException;
import java.rmi.server.*;

@SuppressWarnings("serial")
public class HelloWorldImpl extends UnicastRemoteObject implements HelloWorld  {

	String hello = "";
	
	public HelloWorldImpl() throws RemoteException {
		hello = "Hello World !!";
	}
	
	public String sayHello() throws RemoteException{
		return this.hello;
	}
}
