package Socket;
import java.util.*;
import java.io.*;
import java.net.*;
public class ChatServer {
	static ArrayList<ClientHandlers> client_handler_list = new ArrayList<>();
	static int i=0;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss = new ServerSocket(1234);
		Socket s;
		while(true) {
			s = ss.accept();
			System.out.println("new client accepted "+ s);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			System.out.println("Creating Handler for this client");
			ClientHandlers client_handler = new ClientHandlers(s,"client"+i, dis,dos);
			System.out.println("adding the client to the clients list");
			client_handler_list.add(client_handler);
			Thread t = new Thread(client_handler);
			t.start();
			i++;
			
			
		}

	}}
	class ClientHandlers implements Runnable{
		Scanner scn = new Scanner(System.in);
		private Socket s;
		private String name;
		private DataInputStream dis;
		private DataOutputStream dos;
		private boolean is_logged_in;
		public ClientHandlers(Socket s, String name, DataInputStream dis, DataOutputStream dos) {
			this.s = s;
			this.name = name;
			this.dis = dis;
			this.dos = dos;
			this.is_logged_in = true;
			
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String received;
			while(true) {
				try {
					received = dis.readUTF();
					System.out.println(received);
					if(received.equals("logout")) {
						this.is_logged_in = false;
						break;
					}
					StringTokenizer st = new StringTokenizer(received,"#");
					String message_to_send = st.nextToken();
					String receipent = st.nextToken();
					for(ClientHandlers ch:ChatServer.client_handler_list) {
						if(ch.name.equals(receipent) && ch.is_logged_in==true) {
							ch.dos.writeUTF(message_to_send);
							break;
						}
					}
				} catch (IOException e) {
					
					System.out.println(e);
				}
				try {
					this.dis.close();
					this.dos.close();
				}
				catch(IOException e) {
					System.out.println(e);
				}
				
			}
			
		}
		
	}


