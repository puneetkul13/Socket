package Socket;
import java.io.*;
import java.net.*;
import java.util.*;
public class ChatClient {
	final static int server_port = 1234;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		InetAddress ip = InetAddress.getByName("localhost");
		Socket s = new Socket(ip, server_port);
		DataInputStream dis = new DataInputStream(s.getInputStream());
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		Thread send_message = new Thread(new Runnable() {
			

			@Override
			public void run() {
				String message = sc.nextLine();
				while(true) {
					
					try {
						dos.writeUTF(message);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				}
				// TODO Auto-generated method stub
				
				
			}
			
		});
		Thread receive_message = new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				while(true) {
					
					try {
						String msg = dis.readUTF();
						System.out.println(msg);
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					
				}
				
			}
			
		});
		
		send_message.start();
		receive_message.start();

	}

}
