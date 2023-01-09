import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try{
            Socket socket1=new Socket("localhost",8090);
            InputStream inputStream=socket1.getInputStream();
            OutputStream outputStream=socket1.getOutputStream();

            //ObjectWriter
            PrintWriter objectWriter=new PrintWriter(outputStream,true);
            //ObjectReader
            InputStreamReader objectReader=new InputStreamReader(inputStream);
            BufferedReader bufReader=new BufferedReader(objectReader);


            Scanner scanner=new Scanner(System.in);
            String msg;
            String bufMsg;

            //System.out.print("Veuillez Saisir Un nbr : ");


            while ((bufMsg=bufReader.readLine())!=null){
                System.out.println(bufMsg);
                msg=scanner.nextLine();
                objectWriter.println(msg);
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
