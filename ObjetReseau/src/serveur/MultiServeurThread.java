package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import protocol.Message;
import protocol.Type;
/*
 * The MultiServerThread object communicates to the client by reading from and writing to the socket.
 * 
 * */
public class MultiServeurThread extends Thread {
	private Socket clientSocket = null;

	// constructor
	public MultiServeurThread(Socket socket) {
		super("MultiServeurThread");
		this.clientSocket = socket;
	}

	// function communicate
	public void run() {
		try {
			ObjectOutputStream out = new ObjectOutputStream(
				clientSocket.getOutputStream());
		     ObjectInputStream in = new ObjectInputStream(
				clientSocket.getInputStream());
		     
			Message messageObject;
			
			while ((messageObject = (Message) in.readObject())!= null) {			
				if (messageObject.getRequete() == Type.EXIT) {
					Message exit = new Message("ok", new ArrayList<String>());
					out.writeObject(exit);
					System.out.println("serveur closing...");
					break;
				}
				Handmessage handmessage = new Handmessage(messageObject);
				System.out.println("envoie reponse");
				out.writeObject(handmessage.reponse);
			}
			clientSocket.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
