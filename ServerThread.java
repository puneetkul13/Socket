package Socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.io.*;
import java.text.*;
import java.util.*;
import java.net.*;

public class ServerThread {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket ss = new ServerSocket(5056);
		
		while(true) {
			Socket s = ss.accept();
			System.out.println("A client is connected "+s);
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dos = new DataOutputStream(s.getOutputStream());
			System.out.println("Assigning new thread to the client");
			Thread t = new ClientHandler(s,dis,dos);
			t.start();
		}

	}

}
class ClientHandler extends Thread{
	DateFormat fordate = new SimpleDateFormat("yyyy/mm/dd");
	DateFormat fortime = new SimpleDateFormat("hh:mm;ss");
	Socket s;
	DataInputStream dis;
	DataOutputStream dos;
	public ClientHandler(Socket s,DataInputStream dis,DataOutputStream dos) {
		this.s = s;
		this.dis = dis;
		this.dos = dos;
		
		
	}
	@Override
	public void run() {
		String received;
		String toReturn;
		while(true) {
			try {
				dos.writeUTF("Date, Time or Exit?");
				received = dis.readUTF();
				if(received.equals("Exit")) {
					System.out.println("Closing Connection. Exit Entered");
					this.s.close();
					break;
				}
				LocalDateTime date = java.time.LocalDateTime.now();
				if(received.equals("Date")) {
					toReturn = "Date----";
					dos.writeUTF(toReturn);
					break;
					
				}
				else if(received.equals("Time")) {
					toReturn = "Time----";
					dos.writeUTF(toReturn);
					break;
				}
				else  {
					dos.writeUTF("Invalid Input");
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			this.dis.close();
			this.dos.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
	
	
}
