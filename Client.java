package Socket;
import java.net.*;
import java.util.Scanner;
import java.io.*;
public class Client{
	private Socket socket;
	private DataInputStream input;
	private DataOutputStream out;
	public Client(String address, int port) {
		try {
			socket =  new Socket(address, port);
			System.out.println("Connected");
			input = new DataInputStream(System.in);
			out  = new DataOutputStream(socket.getOutputStream());
			
		}
		catch (UnknownHostException u) {
			System.out.println(u);
		}
		catch(IOException i) {
			System.out.println(i);
		}
		String line="";
		while(!(line.equals("Over"))) {
			try {
				System.out.println("ipipipip");
				line = input.readLine();
				out.writeUTF(line+"---");
				
			}
			catch(IOException i){
				System.out.println(i);
			}
			
		}
		try {
			input.close();
			out.close();
			socket.close();
		}
		catch(IOException i) {
			System.out.println(i);
		}
	}
	public static void main(String args[]) {
		Client client = new Client("127.0.0.1",5000);
	}
	
}


