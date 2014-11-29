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
	private Socket socket = null;

	// constructor
	public MultiServeurThread(Socket socket) {
		super("MultiServeurThread");
		this.socket = socket;
	}

	// function communicate
	public void run() {		         
		try(ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream());){
			Handmessage handmessage = new Handmessage();
			Message messageObject = null;
			while ((messageObject=(Message)in.readObject())!= null) {	
				handmessage.hand(messageObject);
				out.writeObject(handmessage.reponse);
				if(messageObject.getRequete() == Type.EXIT){
					Message exit = new Message("ok", new ArrayList<String>());
					out.writeObject(exit);
					break;
				}
			}						
			socket.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
