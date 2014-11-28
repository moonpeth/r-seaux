package serveur;

import java.io.IOException;
import java.net.ServerSocket;
/*
 * MultiServer loops forever, listening for client connection requests on a ServerSocket. 
 * When a request comes in, MultiServer accepts the connection, creates a new MultiServerThread object to process it, 
 * hands it the socket returned from accept, and starts the thread.
 * Then the server goes back to listening for connection requests
 * */
public class MultiServeur {
	
    public static void main(String[] args) throws IOException {
  
        int port = 8888;
        boolean listening = true;
         
        try (ServerSocket serverSocket = new ServerSocket(port)) {
        	System.out.println("multiserveur lanch,waiting for client ;)");
            while (listening) {
            	System.out.println("waiting for connection...");
                new MultiServeurThread(serverSocket.accept()).start();
                System.out.println("client connected");
            }
//            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Could not listen on port " + port);
            System.exit(-1);
        }
    }
}