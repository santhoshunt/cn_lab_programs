import java.io.*;
import java.net.*;
import java.util.Scanner;
public class stopwaitsender
{
    public static void main(String args[]) throws Exception{
        stopwaitsender sws = new stopwaitsender();
        sws.run();
    }
    public void run() throws Exception
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter no of frames to be sent: ");
        int n=sc.nextInt();
        Socket myskt=new Socket("localhost",8080);
        PrintStream myps=new PrintStream(myskt.getOutputStream());
        for(int i=0;i<=n;){
            if(i==n){
                myps.println("exit");
                break;
            }
            System.out.print("Frame no "+i+" is sent\n");myps.println(i);
            BufferedReader bf=new BufferedReader(new
            InputStreamReader(myskt.getInputStream()));String ack=bf.readLine();
            if(ack!=null){
                System.out.print("Acknowledgement was Received from receiver\n");i++;
                Thread.sleep(2000);
            }
            else{
                myps.println(i);
            }
        }
    }
}