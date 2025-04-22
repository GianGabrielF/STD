package engtelecom.std;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class ThreadServidor implements Runnable{

    private Socket cliente;

    public ThreadServidor(Socket cliente){
        this.cliente = cliente;
    }

    @Override
    public void run() {

        if (cliente != null){
            try {
                System.out.println("Cliente Conectado: " + cliente.getInetAddress());
                BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));

                OutputStreamWriter saida = new OutputStreamWriter(cliente.getOutputStream(), "UTF-8");


                String mensagem = entrada.readLine();

                System.out.println(cliente.getInetAddress() + "> " + mensagem);

                saida.write("Hallo, ich bin servidor \n");
                saida.flush();
            } catch (Exception e) {
                System.err.println("Erro: " + e.toString());
            }
        }
    }
}
