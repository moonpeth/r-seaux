package serveur;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServeurUDP {

	static int hostport = 8532;
	static String hostname = "localhost";
	static byte[] buffer ;


	public static void envoi (String hostname, int hostport, byte[] buffer){
		try{
			InetAddress host_address = InetAddress.getByName(hostname);
			@SuppressWarnings("resource")
			DatagramSocket socket = new DatagramSocket();
			DatagramPacket paquet = new DatagramPacket(buffer, buffer.length,host_address,hostport);
			socket.send(paquet);
		}catch(Exception e){
			System.err.println("Erreur: "+e);
		}

	}

	static public DatagramPacket reception (int hostport){
		try{
			@SuppressWarnings("resource")
			DatagramSocket socket = new DatagramSocket(hostport);
			// while(true){
			DatagramPacket paquet = new DatagramPacket(new byte[512],512);
			socket.receive(paquet);
			System.out.println("Addresse Serveur:"+ paquet.getAddress());
			System.out.println("Donnees recues :"+ new String(paquet.getData()));
			return paquet;
			//}
		}catch(Exception e){
			System.err.println("Erreur: "+e);
			return null;
		}

	}

	/*    static public void reception (int hostport){
            try{
		DatagramSocket socket = new DatagramSocket(hostport);
                    while(true){
                    DatagramPacket paquet = new DatagramPacket(new byte[512],512);
                    socket.receive(paquet);
                    System.out.println("Contenu du paquet Addresse Serveur:"+ paquet.getAddress());
                    System.out.println("Contenu du paquet Donnees:"+ new String(paquet.getData()));
                    }
                  }catch(Exception e){
		System.err.println("Erreur: "+e);
                 }

    } */ 


	public static void main(String[] args) throws IOException{
		while(true){
			//Reception du paquet
			DatagramPacket paquet = reception(hostport); //pb:recupration du paquet pour le renvoyer au clientle serveur s arrete apres la first reception

			//reception(hostport);

			//envoi du paquet
			hostname = paquet.getAddress().getHostAddress();
			hostport = paquet.getPort();
			buffer = paquet.getData();
			envoi(hostname,hostport,buffer);
		}
	}
}