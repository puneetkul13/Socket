package Socket;
import java.net.*;
import java.io.*;

	
public class Server{
	private Socket socket;
	private ServerSocket server;
	private DataInputStream in;
	public Server(int port) {
		try {
			server = new ServerSocket(port);
			System.out.println("server started");
			System.out.println("waiting for client");
			socket = server.accept();
			System.out.println("Client Accepted");
			in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
			String line="";
			while(!(line.equals("Over"))) {
				try {
					System.out.println("opopop");
					line = in.readLine();
					System.out.println(line+"---");
					
				}
				catch(IOException i) {
					System.out.println(i);
				}
			}
			System.out.println("closing connection");
			socket.close();
			in.close();
			
					
		}
		catch(IOException i) {
			System.out.println(i);
		}
	}
	public static void main(String args[]) {
		Server server = new Server(5000);
	}
}

