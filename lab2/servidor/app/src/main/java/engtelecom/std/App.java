package engtelecom.std;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
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

                 Thread t = new Thread(new ThreadServidor(cliente));

//                 disparando a thread
                 t.start();


            }

        } catch (Exception e){
            System.err.println("Erro: " + e.toString());
        }
    }
}
