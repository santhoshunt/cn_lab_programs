import java.io.*;
import java.net.*;

public class FileTransferServer {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(9999);
        Socket socket = server.accept();
        System.out.println("Connection establised");
        FileInputStream fin = new FileInputStream("./data1.txt");
        DataOutputStream dataOut = new DataOutputStream(socket.getOutputStream());
        int b;
        while ((b = fin.read()) != -1)
            dataOut.write(b);

        System.out.println("File transfer completed!");
        // the rest of the code is optional
        fin.close();
        socket.close();
        server.close();
    }
}
