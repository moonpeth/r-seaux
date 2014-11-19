package serveur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
	private static int DEFAULTPORT = 8888;
	private ServerSocket socketServer;
	private Socket socketClient;
	private String line;
	private DataInputStream is;
	private PrintStream os;

	public Serveur(){
		this(DEFAULTPORT);
	}

	public Serveur(int port){
		try{
			socketServer = new ServerSocket(port);
		} catch (IOException e) {
			System.err.println("Server could not listen on port " + port);
			System.exit(-1);
		}
	}

	public void run() {
		while(true){
			try {
				//new MultiServerThread(socketServer.accept()).start();
				socketClient = socketServer.accept();
				is = new DataInputStream(socketClient.getInputStream());
				os = new PrintStream(socketClient.getOutputStream());
				while(true){
					line = is.readLine();
					os.print(line);
				}
			} catch (IOException e) {
				System.err.println("Server could not listen on port " + socketServer.getLocalPort());
				System.exit(-1);
			}
		}
	}

//	class MultiServerThread extends Thread{
//		private Socket socket = null ;
//
//		public MultiServerThread(Socket socket){
//			super("MultiServerThread");
//			this.socket = socket;
//		}
//
//		public void run() {
//			try {
//				//TODO : body
//			} catch (Exception e) {
//				try {
//					System.out.println("Client terminate");
//					socket.close();
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
//			}
//		}
//	}
	
	public static void main(String args[]) {
		System.out.println("Server launch at port 6969");
		new Serveur(6969).run();
		System.out.println("Server close");
	}

}
