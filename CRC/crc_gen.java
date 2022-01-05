import java.io.*;
class crc_gen{
    public static void main(String args[]) throws IOException{
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int[] data,dividend,crc,divisor,remainder;
        String dataString,divisorString,crcString;
        int data_bits, divisor_bits, tot_length;
        System.out.print("Enter number of data bits: ");
        data_bits=Integer.parseInt(br.readLine());
        data=new int[data_bits];
        System.out.print("Enter data bits : ");
        dataString = br.readLine();
        for(int i=0; i<data_bits; i++)
        data[i]=Integer.parseInt(dataString.substring(i,i+1));
        System.out.print("Enter number of bits in divisor: ");
        divisor_bits=Integer.parseInt(br.readLine());
        divisor=new int[divisor_bits];
        System.out.print("Enter Divisor bits :");
        divisorString =  br.readLine();
        for(int i=0; i<divisor_bits; i++)
        divisor[i]=Integer.parseInt(divisorString.substring(i, i+1));
        tot_length = data_bits + divisor_bits-1;
        dividend=new int[tot_length];
        remainder=new int[tot_length];
        crc=new int[tot_length];
        /* CRCGENERATION */
        for(int i=0;i<data_bits;i++)
            dividend[i]=data[i];
        for(int i=0;i<divisor_bits-1;i++)
            dividend[data_bits+i]=0;

        System.out.print("Dividend (after appending 0's) are : ");
        
        for(int i=0; i< dividend.length; i++)
            System.out.print(dividend[i]);
        
        System.out.println();
        for(int j=0; j<dividend.length; j++){
            remainder[j] = dividend[j];
        }
        remainder=divide(dividend, divisor, remainder);

        for(int i=0;i<dividend.length;i++){
            crc[i]=(dividend[i]^remainder[i]);
        }
        System.out.print("CRC code : ");
        for(int i=0;i<crc.length;i++)
        System.out.print(crc[i]);

        /* ERRORDETECTION */
        System.out.println();
        System.out.println("Enter CRC code of "+tot_length+" bits: ");
        crcString=br.readLine();
        for(int i=0; i<crc.length; i++)
            crc[i]=Integer.parseInt(crcString.substring(i,i+1));

        for(int j=0; j<crc.length;j++){
            remainder[j] =crc[j];
        }
        remainder=divide(crc,divisor,remainder);

        for(int i=0; i< remainder.length; i++){
            if(remainder[i]!=0){
                System.out.println("Error! Code transmitted was tampered.");
                break;
            }
            if(i==remainder.length-1)
            System.out.println("No Error!! The transmission was successful.");
        }
    }
    static int[] divide(int div[],int divisor[], int rem[])
    {
        int cur=0; 
        while(true){
        for(int i=0;i<divisor.length;i++)
            rem[cur+i] = (rem[cur+i]^divisor[i]);
        while(rem[cur]==0 && cur!=rem.length-1)
            cur++;
            if((rem.length - cur)<divisor.length)
            break;
        }
        return rem;
    }
}