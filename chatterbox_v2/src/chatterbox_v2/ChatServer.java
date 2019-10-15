package chatterbox_v2;
import java.io.*;
import java.net.*;

public class ChatServer {
	
	private Socket socket = null;
	private ServerSocket server = null;
	private DataInputStream input = null;
	private DataOutputStream output = null;
	
	
	public ChatServer(int port) 
	{
		try {
			System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);  
        
			System.out.println("Server started: " + server);
			System.out.println("Waiting for a client ..."); 
        
			socket = server.accept();
			System.out.println("Client accepted: " + socket);
        
			open();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			
			String msgin="", msgout="";
			while(!msgin.equals("end"))
			{
				msgin = input.readUTF();
				System.out.println(msgin);
				msgout = br.readLine();
				output.writeUTF(msgout);;
				output.flush();
			}
			server.close();
		}
		catch(IOException e) { System.out.println(e); }
	}
	
	public void open() throws IOException
	{
		input = new DataInputStream(socket.getInputStream());
		output = new DataOutputStream(socket.getOutputStream());
	}
	
		public static void main (String[] args) 
		{	
			ChatServer chatServer = null;
			if (args.length != 1) { System.out.println("Usage: java ChatServer port"); }
			else { chatServer = new ChatServer(Integer.parseInt(args[0])); }
		}
		
		
	
	
}
