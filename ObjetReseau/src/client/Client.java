package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) {

		Socket socket;
		BufferedReader in;
		PrintWriter out;

		try {

			socket = new Socket("127.0.0.1", 6969);
			out = new PrintWriter(socket.getOutputStream(),true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner sc = new Scanner(System.in);
			String line;
			System.out.print("Client connected at 127.0.0.1:6969");
			while(true){
				System.out.print("Ecris un truc : ");
				line = sc.nextLine();
				out.println(line);
				out.flush();
				System.out.println(in.readLine());
				if(line.equals("exit")){
					break;
				}
			}
			sc.close();
			socket.close();

		}catch (UnknownHostException e) {
			System.out.println("UnknownHost :");
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("IOException :");
			e.printStackTrace();
		}
	}

}
