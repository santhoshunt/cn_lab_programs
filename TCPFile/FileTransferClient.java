import java.io.*;
import java.net.*;

public class FileTransferClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 9999);
        if (socket.isConnected())
            System.out.println("Connection successful!");
        FileOutputStream fout = new FileOutputStream("data2.txt");
        DataInputStream din = new DataInputStream(socket.getInputStream());
        int b;
        while ((b = din.read()) != -1)
            fout.write((char) b);

        System.out.println("File transfer completed!");
        // the rest of the code is optional
        fout.close();
        socket.close();
    }
}
