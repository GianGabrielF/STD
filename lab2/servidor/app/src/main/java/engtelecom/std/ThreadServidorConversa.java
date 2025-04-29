package engtelecom.std;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ThreadServidorConversa implements Runnable{

    private Socket cliente;

    public ThreadServidorConversa(Socket cliente){
        this.cliente = cliente;
    }

    @Override
    public void run() {

        if (cliente != null){
            try {
                System.out.println("Cliente Conectado: " + cliente.getInetAddress());
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

                OutputStreamWriter saida = new OutputStreamWriter(cliente.getOutputStream(), "UTF-8");

                String mensagem = "servidor";

                do {
                    mensagem = entrada.readLine();

                    if (!mensagem.equals( "fim")) {
                        System.out.println(cliente.getInetAddress() + "> " + mensagem);

                        saida.write("!2soquinhos \n");
                        saida.flush();
                    }
                } while(!mensagem.equals( "fim"));
                System.out.println("Cliente Desconectado: " + cliente.getInetAddress());

            } catch (Exception e) {
                System.err.println("Erro: " + e.toString());
            }
        }
    }
}
