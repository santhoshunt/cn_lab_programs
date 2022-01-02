import java.net.*;
import java.io.*;
import java.rmi.*;
public class slidsender{
    public static void main(String[] args)throws Exception{
        ServerSocket ser=new ServerSocket(1025); // Unix based systems declare ports<=1024 as previledged, this pgm need admin rights to start a server. If you're using mac or linuxuse ports >1025
        Socket s=ser.accept();
        DataInputStream in=new DataInputStream(System.in);
        DataInputStream in1=new DataInputStream(s.getInputStream());
        String sbuff[]=new String[8];
        PrintStream p;
        int sptr=0,sws=8,nf,ano,i;
        String ch;
        do
        {
            p=new PrintStream(s.getOutputStream());
            System.out.print("Enter the no. of frames : ");
            nf=Integer.parseInt(in.readLine());
            p.println(nf);
            if(nf<=sws-1){
                System.out.println("Enter "+nf+" Messages to be send\n");
                for(i=1;i<=nf;i++){
                    sbuff[sptr]=in.readLine();p.println(sbuff[sptr]);
                    sptr=++sptr%8;
                }
                sws-=nf;
                System.out.print("Acknowledgment received");
                ano=Integer.parseInt(in1.readLine());
                System.out.println(" for "+ano+" frames");
                sws+=nf;
            }
            else
            {
                System.out.println("The no. of frames exceeds window size");
                break;
            }
            System.out.print("\nDo you wants to send some more frames (y/n): ");
            ch=in.readLine().toLowerCase();
            p.println(ch);
        }while(ch.equals("y"));
        s.close();
    }
}