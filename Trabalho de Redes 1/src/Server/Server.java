/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilkerrj
 */
public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(9999); //cria um Socket para escutar na porta 9999
        System.out.println("Listening on port 9999");
        while (true) {//loop eterno
            Socket connectionSocket = server.accept(); // inicia a escuta do servidor
            System.out.println("Client connected " + connectionSocket.getInetAddress().getHostAddress());
            try {
                Decoder decoder = new Decoder();//classe de decodificação do comando recebido
                String[] command = decoder.decode(connectionSocket.getInputStream()); //string a ser enviada para o cliente;
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                //ObjectOutputStream outToClient = new ObjectOutputStream(connectionSocket.getOutputStream());
                outToClient.writeBytes(command[0]+"\n");
                //outToClient.writeObject(command);
            } catch (IllegalArgumentException ex) {
                DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
                outToClient.writeBytes("Comando Inválido");
            }
        }

    }
}
