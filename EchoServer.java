package com.example;
import java.net.*;
import java.util.*;
import java.io.*;


public class EchoServer {
  
	
	public static void main(String[] args) throws IOException, ClassNotFoundException{
		int port = 8080;
		ServerSocket listener = null;

                Notes stored;
                stored= new Notes();
		
		try {
			listener = new ServerSocket(port);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Server is Running...");
		try{
			while(true)
                        {
				Socket cSocket = listener.accept();
				
				InputStream fromClient = cSocket.getInputStream();
				DataInputStream FromClient = new DataInputStream(cSocket.getInputStream());
				DataOutputStream out = new DataOutputStream(cSocket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(cSocket.getInputStream());

				int option = fromClient.read();
				
				if(option==1){
					Notes coming = (Notes)in.readObject();
						System.out.println("Storing Coming Information: \n");
						stored.name=coming.name;
                                                stored.Reg=coming.Reg;
                                                stored.section=coming.section;
                                                
                                                System.out.println("Following Info Have been Stored:\n" + coming.name );
				}	
				if(option==2){
					String name = FromClient.readUTF();
					System.out.println("yeh naam aya ha " + name);
					String sendToClient;
                                        if(stored.name.equals(name)){
//			
                                                sendToClient="Name: "+stored.name+"\nSection: "+stored.section+"\n Reg Number: "+stored.Reg+"\n";
//                                                System.out.println("In here    " +stored.section);
					}
                                        else
                                        {
                                            sendToClient="Name you entered Is Not Found\n";
                                        }
					//System.out.println("sendToClient: hhahah " +sendToClient);
					
					out.writeUTF(sendToClient);
					
					
				}
				
				cSocket.close();
				}			
		} catch(IOException e){
			e.printStackTrace();
		}
		
		listener.close();
	}
}
