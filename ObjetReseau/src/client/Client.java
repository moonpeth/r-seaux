package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import protocol.Message;

// Object de communication
public class Client {

	public static void main(String[] args) throws IOException {

		// if (args.length != 2) {
		// System.err.println(
		// "Usage: java EchoClient <host name> <port number>");
		// System.exit(1);
		// }
		//
		// String hostName = args[0];
		// int portNumber = Integer.parseInt(args[1]);
		//
		// The client automatically closes its input and output streams
		// and the socket because they were created in the try-with-resources
		// statement.
		
		try (Socket kkSocket = new Socket("127.0.0.1",6356);
				ObjectOutputStream out = new ObjectOutputStream(
						kkSocket.getOutputStream());
				// reading from the input stream attached to the socket
				ObjectInputStream in = new ObjectInputStream(
						kkSocket.getInputStream());) {
			System.out.println("client launch\n");
			// listen from the client,who types into he standard input
			Scanner stdIn=new Scanner(System.in);			
			Message request = new Message(stdIn);
	
			out.writeObject(request);
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host "
					+ InetAddress.getLocalHost());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to "
					+ InetAddress.getLocalHost());
			System.exit(1);
		}

	}

}