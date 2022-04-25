package Socket;
import java.util.*;
import java.net.*;
import java.io.*;
public class ClientThread {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		InetAddress ip = InetAddress.getByName("localhost");
		Scanner sc = new Scanner(System.in);
		Socket s = new Socket(ip,5056);
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		while(true) {
			System.out.println(dis.readUTF());
			String request = sc.nextLine();
			dos.writeUTF(request);
			if(request.equals("Exit")) {
				System.out.println("Closing connection");
				s.close();
			}
			System.out.println(dis.readUTF());
			sc.close();
			dis.close();
			dos.close();
			
		}

	}

}
