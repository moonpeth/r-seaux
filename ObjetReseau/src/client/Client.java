package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
//import java.util.Scanner;

import java.util.ArrayList;

import protocol.Message;

// Object de communication
public class Client {

	private static String DEFAULT_IP = "127.0.0.1";
	private static int DEFAULT_PORT = 8888;

	private static Socket socket;
	private static ObjectInputStream in;
	private static ObjectOutputStream out;

	public static void main(String[] args) throws UnknownHostException, ClassNotFoundException {
           
		try {
			Message messageObject;
			socket = new Socket(DEFAULT_IP, DEFAULT_PORT);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
			System.out.println("client launch\n");

			//add
			out.writeObject(createAdd("Nicolas","Nico"));
			messageObject = (Message)in.readObject();
			System.out.println(messageObject);
			
			//add test exception
			out.writeObject(createAdd("Nicolas","Nico"));
			messageObject = (Message)in.readObject();
			System.out.println(messageObject);
			
			//autre add
			out.writeObject(createAdd("Jimmy","Jim"));
			messageObject = (Message)in.readObject();
			System.out.println(messageObject);
			
			//add surnom
			out.writeObject(createAdds("Nico","Nicoco"));
			messageObject = (Message)in.readObject();
			System.out.println(messageObject);
			
			//get all
			out.writeObject(createGet());
			messageObject = (Message)in.readObject();
			System.out.println(messageObject);
			
			//get Jim
			out.writeObject(createGet("Jimmy","Jim"));
			messageObject = (Message)in.readObject();
			System.out.println(messageObject);
			
			//exit
			out.writeObject(createExit());
			messageObject = (Message)in.readObject();
			System.out.println(messageObject);

			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + InetAddress.getLocalHost());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + InetAddress.getLocalHost());
			System.exit(1);
		}
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
	
	private static Message createGet() {
		ArrayList<String> getl = new ArrayList<String>();
		Message get = new Message("get", getl);
		System.out.println(get);
		return get;
	}
	
	private static Message createGet(String nom,String surnom) {
		ArrayList<String> getl = new ArrayList<String>();
		getl.add(nom);getl.add(surnom);
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

}