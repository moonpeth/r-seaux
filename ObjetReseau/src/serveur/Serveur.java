package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import protocol.Message;

public class Serveur {

	private static String DEFAULT_IP = "127.0.0.1";
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
<<<<<<< HEAD
		try (ServerSocket serverSocket = new ServerSocket(port);
				Socket clientSocket = serverSocket.accept();
				ObjectOutputStream out = new ObjectOutputStream(
						clientSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(
						clientSocket.getInputStream());) {
			
             Message messageObject = (Message)in.readObject();
             Handmessage handmessage = new Handmessage(messageObject);
             System.out.println(messageObject.toString());
             
//			Object inputLine = 
//			Object outputLine =
//
//			// Initiate conversation with client
//			Protocol protocol = new Protocol();
//			outputLine = protocol.processInput(null);
//			out.writeObject(outputLine);
//
//			while ((inputLine = in.readObject()) != null) {
//				outputLine = protocol.processInput(inputLine);
//				out.writeObject(outputLine);
//				if (outputLine.equals("Bye."))
//					break;
			
		} catch(IOException e) {
			System.out
					.println("Exception caught when trying to listen on port "
							+ port + " or listening for a connection");
			System.out.println(e.getMessage());
		} catch(ClassNotFoundException e) {
			System.err.println("ClassNotFoundException"
					+ InetAddress.getLocalHost());
			System.exit(1);
=======
		
		try {
			Message messageObject;
			serverSocket = new ServerSocket(DEFAULT_PORT);
			Socket clientSocket = serverSocket.accept();
			out = new ObjectOutputStream(clientSocket.getOutputStream());
			in = new ObjectInputStream(clientSocket.getInputStream());
			messageObject = (Message)in.readObject();
			//Handmessage handmessage = new Handmessage(messageObject);
			System.out.println(messageObject.toString());
			serverSocket.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
>>>>>>> 772c9e373b24440280e2b410ea43f7fe25c54f5d
		}
		
	}
}


