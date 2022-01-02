import javax.swing.*;
import java.net.*;
import java.awt.image.*;
import javax.imageio.*; 
import java.io.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
public class HTTPClient{
    public static void main(String args[]) throws Exception{
        Socket soc;
        BufferedImage img = null;
        soc=new Socket("localhost",4000);
        System.out.println("Client is running. ");
        try {
            System.out.println("Reading image from disk. ");
            img = ImageIO.read(new File("/home/gane-detoxx/projects/cn_lab_programs/webpg_upload_n_download/RickRoll.png")); //change path according to your system.
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "png", baos);
            baos.flush();
            byte[] bytes = baos.toByteArray();
            baos.close();
            System.out.println("Sending image to server. ");
            OutputStream out = soc.getOutputStream();
            DataOutputStream dos = new DataOutputStream(out);
            dos.writeInt(bytes.length);
            dos.write(bytes, 0, bytes.length);
            System.out.println("Image sent to server. ");
            dos.close();
            out.close();
        }catch (Exception e){
            System.out.println("Exception: " + e.getMessage());
            soc.close();
        }
        soc.close();
    }
}