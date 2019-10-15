package chatterbox_v2;
import java.io.*;
import java.net.*;

public class ChatClient {
	
	private Socket socket = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	
	public ChatClient(String serverName, int serverPort) 
	{
		System.out.println("Establishing connection. Please wait ...");
		try
	     { 
			 socket = new Socket(serverName, serverPort);
	         System.out.println("Connected: " + socket);
	         start();
	     }
		catch(UnknownHostException uhe)
	     {  
			 System.out.println("Host unknown: " + uhe.getMessage());
	     }
	     catch(IOException ioe)
	     {  
	    	 System.out.println("Unexpected exception: " + ioe.getMessage());
	     }
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String msgin="" , msgout="";
		
		while(!msgin.contentEquals("end")) {
			try {
			msgout = br.readLine();
			output.writeUTF(msgout);;
			msgin = input.readUTF();
			System.out.println(msgin);
			}
			catch(IOException ioe) 
			{ 
				System.out.println("Sending error: " + ioe.getMessage());
			}
		}
		stop();
	}
	
	public void start() throws IOException
	{
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
	}
	
	public void stop()
	{
		try {
			socket.close();
		}
		catch(IOException e) { System.out.println("Error closing ..."); }
	}
	
	public static void main (String[] args){
		ChatClient client = null;
	      if (args.length != 2)
	      {
	    	  System.out.println("Usage: java ChatClient host port");
	      }
	      else
	      {
	    	  client = new ChatClient(args[0], Integer.parseInt(args[1]));
	      }
	}
}
