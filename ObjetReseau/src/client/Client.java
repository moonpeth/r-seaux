package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws ClassNotFoundException {

		Socket socket;
		ObjectInputStream is;
		ObjectOutputStream os;

		try {

			socket = new Socket("134.59.214.216", 6969);
			os = new ObjectOutputStream(socket.getOutputStream());
			is = new ObjectInputStream(socket.getInputStream());
			Scanner sc = new Scanner(System.in);
			String line;
			System.out.println("Client connected at 127.0.0.1:6969");
			while(true){
				System.out.print("Ecris un truc : ");
				line = sc.nextLine();
				os.writeObject((Serializable)line);
				os.flush();
				System.out.println(is.readObject());
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
