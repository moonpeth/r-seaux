package client;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP {

	//attributes
	private static byte[] buffer;
	private static String hostname;
	private static int hostport;
	private static String message;

	// Constructor 
	public ClientUDP(String adrIp, int port){   	
		hostname = adrIp;
		hostport = port;
		message = "";
		System.out.println("client initialised");
	}

	//main test
	public static void main(String[] args) {

		ClientUDP client = new ClientUDP("localhost", 8888);

		//add , by A
		System.out.println("\n----------A : add person-------------------------------");
		client.setMessage("add, Nicolassssssssssss, Nicos");
		buffer = client.getMessage().getBytes();
		envoi(client.getHostname(),client.getHostport(),buffer);

		//add another
		System.out.println("\n----------A : add another two persons------------------");
		client.setMessage("add, Nicolas, Nico");
		buffer = client.getMessage().getBytes();
		envoi(client.getHostname(),client.getHostport(),buffer);

		//add another
		client.setMessage("add, Jimmy, Jim");
		buffer = client.getMessage().getBytes();
		envoi(client.getHostname(),client.getHostport(),buffer);

		//get Jim
		System.out.println("\n----------A : get name ,get all,add nickname-----------");
		client.setMessage("get, Jim");
		buffer = client.getMessage().getBytes();
		envoi(client.getHostname(),client.getHostport(),buffer);

		//get all   
		client.setMessage("get");
		buffer = client.getMessage().getBytes();
		envoi(client.getHostname(),client.getHostport(),buffer);

		client.setMessage("adds, Nicoco, Ninico");
		buffer = client.getMessage().getBytes();
		envoi(client.getHostname(),client.getHostport(),buffer);

		client.setMessage("get, Ninico");
		buffer = client.getMessage().getBytes();
		envoi(client.getHostname(),client.getHostport(),buffer);

		System.out.println("\n----------A : EXIT--------------------------------------");
		//exit
		client.setMessage("exit");
		buffer = client.getMessage().getBytes();
		envoi(client.getHostname(),client.getHostport(),buffer);
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		ClientUDP.hostname = hostname;
	}

	public int getHostport() {
		return hostport;
	}

	public void setHostport(int hostport) {
		ClientUDP.hostport = hostport;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		ClientUDP.message = message;
	}

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

	static public void reception (int hostport){
		try{
			@SuppressWarnings("resource")
			DatagramSocket socket = new DatagramSocket(hostport);
			DatagramPacket paquet = new DatagramPacket(new byte[512],512);
			socket.receive(paquet);
			System.out.println("Paquet recu : "+ new String(paquet.getData()));
		}catch(Exception e){
			System.err.println("Erreur: "+e);
		}
	}

}