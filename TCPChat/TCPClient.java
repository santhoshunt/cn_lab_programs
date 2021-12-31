import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String serverMsg;
        String clientMsg;
        Socket clientSocket = new Socket("localhost", 5000);
        BufferedReader fromUser = new BufferedReader(new InputStreamReader(System.in));
		BufferedReader fromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        PrintWriter toServer = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("SEND(Type Q or q to Quit):");

        while (true) {

            serverMsg = fromServer.readLine();

            if (serverMsg.equals("q") || serverMsg.equals("Q")) {
                clientSocket.close();
                break;

            } else {
                System.out.println("RECIEVED:" + serverMsg);
            }
                
            clientMsg = fromUser.readLine();
            if (clientMsg.equals("Q") || clientMsg.equals("q")) {
                toServer.println(clientMsg);
                clientSocket.close();
                break;
			} else {
                toServer.println(clientMsg);
            }
           
        }
    }
}

