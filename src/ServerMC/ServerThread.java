package ServerMC;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
public class ServerThread extends Thread{
    private Socket socket=new Socket();
    private int nbrMagic;
    private String Name;
    ArrayList<Socket> clients;

    public ServerThread(Socket socket,int nbrMagic,ArrayList clients){
        this.socket=socket;
        this.nbrMagic=nbrMagic;
        this.clients=clients;
    }

    @Override
    public void run(){
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader buffer = new BufferedReader(isr);
            PrintWriter printWriter = new PrintWriter(os, true);

            //handlerForMsg
            String str="";
            //get Name of gamer
            printWriter.println("Enter Your Name Plz : ");
            this.Name=buffer.readLine();
            printWriter.println(Name+" Guess Number : ");

            //handle for guessing number
            int nbr=0;
            do{
                try {
                    String nbrStr=buffer.readLine();
                    if(nbrStr!=null) {
                        //get number
                        nbr = Integer.parseInt(nbrStr);
                        //when number guessing is the same of magic number
                        if (nbr == nbrMagic) {
                            printWriter.println("Amazing that's true:)");
                        } else {
                            if (nbr > nbrMagic)
                                printWriter.println("Try Again Less Than " + nbr + " :");
                            else
                                printWriter.println("Try Again Greater Than " + nbr + " :");
                        }
                    }
                }//when gamer enter not number
                catch (NumberFormatException e){
                    printWriter.println(Name+" Guess Number : ");
                }catch (Exception e){
                }
            }while(nbr!=nbrMagic);
            //send a msg to all gamer and quit the game
            for(Socket c : clients){
                if(c!=this.socket){
                    PrintWriter p=new PrintWriter(c.getOutputStream(),true);
                    p.println(this.Name+" Wiiiiin!!! Try In Another Game :>");
                    c.close();
                }
                else{
                    printWriter.println(Name+" Wiiiiiin!!!!");
                }
            }
            socket.close();
        }catch (Exception e){
        }
    }
}
