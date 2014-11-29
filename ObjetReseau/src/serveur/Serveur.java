package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import protocol.Message;
import protocol.Type;

public class Serveur {

	private static int DEFAULT_PORT = 8888;

	private ServerSocket serverSocket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Message messageObject;
	private Handmessage handmessage = new Handmessage();

	public Serveur(int port){
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("could not creat serveur");
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("serveur launch");
		Serveur ser = new Serveur(DEFAULT_PORT);
		ser.connectClient();
		ser.traitMessage();
		ser.close();
	}

	public void connectClient(){
		Socket clientSocket;
		try {
			clientSocket = serverSocket.accept();
			System.out.println("client connecte");
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void traitMessage(){
		while (true) {
			try {
				messageObject = (Message) in.readObject();
				if(messageObject.getRequete() == Type.EXIT){
					Message exit = new Message("ok", new ArrayList<String>());
					out.writeObject(exit);
					System.out.println("serveur closed");
					break;
				}
				System.out.println("traitement...");
				handmessage.hand(messageObject);
				System.out.println("reponse");
				out.writeObject(handmessage.reponse);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void close(){
		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}