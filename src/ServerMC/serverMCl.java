package ServerMC;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;

public class serverMCl {
    public static void main(String[] args){
        try {
            ServerSocket server = new ServerSocket(8090);
            Random random=new Random();
            //int nbrMagic=random.nextInt(100);
            ArrayList clients=new ArrayList();
            int nbrMagic=random.nextInt(100);
            System.out.println(nbrMagic);
            while(true){

                Socket client=server.accept();
                clients.add(client);
                ServerThread st=new ServerThread(client,nbrMagic,clients);
                st.start();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
