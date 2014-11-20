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

	public static void main(String[] args) throws UnknownHostException {

		try {
			socket = new Socket(DEFAULT_IP, DEFAULT_PORT);
			out = new ObjectOutputStream(socket.getOutputStream());
			// reading from the input stream attached to the socket
			in = new ObjectInputStream(socket.getInputStream());
			System.out.println("client launch\n");
			// listen from the client,who types into he standard input
			//Scanner stdIn = new Scanner(System.in);
			//Message request = new Message(stdIn);
			Message add = createAdd();
			out.writeObject(add);
			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + InetAddress.getLocalHost());
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " + InetAddress.getLocalHost());
			System.exit(1);
		}
	}

	private static Message createAdd() {
		ArrayList<String> add1 = new ArrayList<String>();
		add1.add("Nicolas");add1.add("Nico");
		Message add = new Message("add", add1);
		return add;
	}
	
	private static Message createGet() {
		ArrayList<String> getl = new ArrayList<String>();
		Message get = new Message("get", getl);
		return get;
	}

}