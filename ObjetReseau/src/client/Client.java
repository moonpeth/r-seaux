package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import protocol.Message;
//import java.util.Scanner;

// Object de communication
public class Client {
      //attributes
	private Socket socket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	Message messageObject;
	
      // Constructor 
    public Client(String adrIp, int port) throws ClassNotFoundException, IOException{   	
    	socket = new Socket(adrIp, port);
		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
		System.out.println("client arrive ;p \n");
    }
      //Function add person
    public void clientAdd(String a,String b) throws ClassNotFoundException, IOException{
    	out.writeObject(createAdd(a,b));
		messageObject = (Message)in.readObject();
		System.out.println(messageObject);
    }
      //Function add nickname
    public void clientAdds(String a,String b) throws ClassNotFoundException, IOException{
    	out.writeObject(createAdds(a,b));
		messageObject = (Message)in.readObject();
		System.out.println(messageObject);
    }
      //Function get all the nickname
    public void clientGetAll() throws ClassNotFoundException, IOException{
    	out.writeObject(createGets());
		messageObject = (Message)in.readObject();
		System.out.println(messageObject);
    }
      //Function get all the nickname of a person
    public void clientGetNickName(String a) throws ClassNotFoundException, IOException{
    	out.writeObject(createGet(a));
		messageObject = (Message)in.readObject();
		System.out.println(messageObject);
    }
      //Function exit
    public void clientExit() throws ClassNotFoundException, IOException{
    	out.writeObject(createExit());
		messageObject = (Message)in.readObject();
		socket.close();
		System.out.println(messageObject);
    }
      //Function close
    public void close() throws IOException{
    	socket.close();
    }
    	
	private static Message createAdd(String nom,String surnom) {
		ArrayList<String> add1 = new ArrayList<String>();
		add1.add(nom);add1.add(surnom);
		Message add = new Message("add", add1);
		System.out.println(add);
		return add;
	}
	
	private static Message createAdds(String surnom,String newSurnom) {
		ArrayList<String> add1 = new ArrayList<String>();
		add1.add(surnom);add1.add(newSurnom);
		Message add = new Message("adds", add1);
		System.out.println(add);
		return add;
	}
	
	private static Message createGets() {
		ArrayList<String> getl = new ArrayList<String>();
		Message get = new Message("get", getl);
		System.out.println(get);
		return get;
	}
	
	private static Message createGet(String surnom) {
		ArrayList<String> getl = new ArrayList<String>();
		getl.add(surnom);
		Message get = new Message("get", getl);
		System.out.println(get);
		return get;
	}
	
	private static Message createExit() {
		ArrayList<String> exitl = new ArrayList<String>();
		Message ex = new Message("exit", exitl);
		System.out.println(ex);
		return ex;
	}

	//main test
	public static void main(String[] args) {
        
		try {
			
            Client client = new Client("127.0.0.1", 8888);
            
            Client client2 = new Client("127.0.0.1", 8888);
			//add
			client.clientAdd("Nicolassssssssssss","Nicos");
			
			//add test exception
			client2.clientAdd("Nicolas","Nico");
			
			//add another
			client.clientAdd("Jimmy","Jim");
			
			//add nickname
			client2.clientAdds("Nico","Nicoco");

			//get Jim
			client.clientGetNickName("Jim");
			
 		    //get all   i don't know why it doesn't work, pas logique, bu ke xue!!! faut voir dans la class database
  	 	    client.clientGetAll();
			
			client.clientAdds("Nicoco","Ninico");
			
			//exit
            client.clientExit();
          

		} catch (UnknownHostException e) {
			System.err.println("Don't know about your host ");
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to the host");
			System.exit(1);
		} catch (ClassNotFoundException e) {
	
			e.printStackTrace();
		}
	}

}