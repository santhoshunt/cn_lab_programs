import java.io.*;
import java.net.*;

class TCPServer {

    public static void main(String argv[]) throws Exception {
        String clientMsg;
        String serverMsg;
        ServerSocket Server = new ServerSocket(5000);
        System.out.println("TCPServer Waiting for client on port 5000... ");
        
        while (true) {
            Socket connected = Server.accept();
            System.out.println("THE CLIENT " + connected.getInetAddress() + ":" + connected.getPort() + " IS CONNECTED");

            BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(connected.getInputStream()));
            
            PrintWriter toClient = new PrintWriter(connected.getOutputStream(), true);
            System.out.println("SEND(Type Q or q to Quit):");
            while (true) {

                serverMsg = fromUser.readLine();

                if (serverMsg.equals("q") || serverMsg.equals("Q")) {
                    toClient.println(serverMsg);
                    connected.close();
                    break;
                } else {
                    toClient.println(serverMsg);
                }

                clientMsg = fromClient.readLine();

                if (clientMsg.equals("q") || clientMsg.equals("Q")) {
					connected.close();
                	break;
                } else {
					System.out.println("RECIEVED:" + clientMsg);	
                }

            }

        }
    }
}

