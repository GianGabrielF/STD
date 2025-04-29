package engtelecom.std;

import java.net.ServerSocket;
import java.net.Socket;

public class App {

    public static void main(String[] args) {
        System.out.println("..:: Servidor TCP ::..");
        int porta = 1234;

        try(ServerSocket serverSocket = new ServerSocket(porta)){
            while(true){
                //aguardando por conexões de clientes
                Socket cliente = serverSocket.accept();
                Thread t = null;
                switch(args[0]) {
                    case "1"-> {
                     t = new Thread(new ThreadServidorFiles(cliente));
                 }
                 default -> {
                     t = new Thread(new ThreadServidorConversa(cliente));
                 }
                }

//                 disparando a thread
                 t.start();


            }

        } catch (Exception e){
            System.err.println("Erro: " + e.toString());
        }
    }
}
