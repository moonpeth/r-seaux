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

	private static ServerSocket serverSocket;
	private static ObjectInputStream in;
	private static ObjectOutputStream out;

	public static void main(String[] args) throws IOException {

		// if (args.length != 1) {
		// System.err.println("Usage: java KnockKnockServer <port number>");
		// System.exit(1);
		// }
		//
		// int portNumber = Integer.parseInt(args[0]);
		System.out.println("serveur launch");

		try {
			Message messageObject;
			Handmessage handmessage;
			serverSocket = new ServerSocket(DEFAULT_PORT);
			Socket clientSocket = serverSocket.accept();

			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			while (true) {
				messageObject = (Message) in.readObject();
				if(messageObject.getRequete() == Type.EXIT){
					Message exit = new Message("ok", new ArrayList<String>());
					out.writeObject(exit);
					break;
				}
				handmessage = new Handmessage(messageObject);
				out.writeObject(handmessage.reponse);
			}
			serverSocket.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}

	}
}
