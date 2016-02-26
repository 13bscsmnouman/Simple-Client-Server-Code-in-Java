package com.example;


import java.io.*;
import java.net.*;
import java.util.Scanner;
public class EchoClient {
  public static void main(String[] args){
		
		
		try{
			Socket sSocket = new Socket("localhost", 8080);
			DataOutputStream ToServer = new DataOutputStream(sSocket.getOutputStream());
			
			InputStream fromServer = sSocket.getInputStream();
			OutputStream toServer = sSocket.getOutputStream();
			DataInputStream in = new DataInputStream(fromServer);
			ObjectOutputStream oos = new ObjectOutputStream(toServer);
			
			Scanner scanf = new Scanner(System.in);
                        System.out.print("Enter 1 to archive to server\n Enter 2 to Download Note ");
			String choice = scanf.nextLine();
			if(choice.equals("1")){
                            String name,sec,reg;
				System.out.println("Enter Name: ");
				name = scanf.nextLine();
				
				System.out.println("Enter Reg Number: ");
				reg = scanf.nextLine();
                                
                                System.out.println("Enter Section: ");
				sec = scanf.nextLine();
				toServer.write(1); //Tell server that we're archiving
				Notes tempNote = new Notes(name,reg,sec);
				oos.writeObject(tempNote);
				
			}
			if(choice.equals("2")){
			//Download Notes
                                String name;
                                //Search by Nam,e
				System.out.println("Enter Name To search for your Notes: ");
				name = scanf.nextLine();
				
				toServer.write(2); 
				ToServer.writeUTF(name);

				
				System.out.println("Received Response from Server: ");
				System.out.println(in.readUTF());
				
			}
			

			
			sSocket.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
