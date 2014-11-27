package serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import protocol.Message;
import protocol.Type;

public class MultiServeurThread {
	private Socket clientSocket;
	Message messageObject;
	
	private ServerSocket serverSocket;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	private Handmessage handmessage;	
	
	//constructor
    MultiServeurThread(int port) throws IOException{
    	System.out.println("serveur launch");
    	serverSocket = new ServerSocket(port);
		clientSocket = serverSocket.accept();
		out = new ObjectOutputStream(clientSocket.getOutputStream());
		in = new ObjectInputStream(clientSocket.getInputStream());
    	
    }
    
    //function communicate
    public void run() throws IOException, ClassNotFoundException{
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
    }
    
    //main test
	public static void main(String[] args) throws IOException {
		try {
			MultiServeurThread server = new MultiServeurThread(8888); 
				server.run();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();

		}

	}
}
