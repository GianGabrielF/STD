package engtelecom.std;

import java.io.*;
import java.net.Socket;

public class ThreadServidorFiles implements Runnable{ private Socket clientSocket;

    public ThreadServidorFiles(Socket cliente){
        this.clientSocket = cliente;
    }

    @Override
    public void run() {

        if (clientSocket != null){
            try {
                // DataInputStream e DataOutputStream são usados para ler e escrever dados binários
                DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
// readUTF lê uma String codificada em UTF-8
                String nomeArquivo = dis.readUTF();
                File arquivo = new File(nomeArquivo);
                if (arquivo.exists()) {
                    dos.writeLong(arquivo.length());
                } else {
                    dos.writeLong(-1);
                }
// A leitura do arquivo deve ser feita em blocos de bytes.
                byte[] buffer = new byte[4096];
                int bytesLidos;
// O envio do arquivo deve ser feito em blocos de bytes até que o arquivo seja totalmente enviado.
                FileInputStream fis = new FileInputStream(arquivo);
                while ((bytesLidos = fis.read(buffer)) != -1) {
// O métodô write do DataOutputStream envia os bytes para o cliente
                    dos.write(buffer, 0, bytesLidos);
                }
// É importante liberar os recursos após o uso. Talvez try-with-resources seja mais interessante
                fis.close();
                dos.flush();
                clientSocket.close();
            } catch (Exception e) {
                System.err.println("Erro: " + e.toString());
            }
        }
    }
}
