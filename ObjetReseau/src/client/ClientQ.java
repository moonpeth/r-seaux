package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//10.212.99.216
public class ClientQ {
	private static String DEFAULT_IP = "10.212.99.216";
	private static int DEFAULT_PORT = 1234;
	private static Socket socket;
	private static BufferedReader in;
	private static PrintWriter out;

	public static void main(String[] args) {
		try {			
			socket = new Socket(DEFAULT_IP, DEFAULT_PORT);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			System.out.println("client launch\n");
			out.println("HELO\nNAME:Michel Dupuit\nGET\nEND");
			out.flush();
			
			String s=in.readLine();
				
			while(!s.equals("END")){
				System.out.println(s);
				s=in.readLine();
			}
			
			
			socket.close();
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("error");
		}
	}
}
