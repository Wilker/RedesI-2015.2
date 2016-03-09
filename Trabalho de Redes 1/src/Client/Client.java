/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilkerrj
 */
public class Client {

    public static void main(String[] args) throws ClassNotFoundException {
        try {
            boolean exit = false;
            Menu.inicial();
            Socket clientSocket = new Socket("localhost", 9999);
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); //cria um leitor e associa ao teclado
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); // cria uma conexão de saída com o servidor
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (!exit) {
                try {
                    String in = inFromUser.readLine();
                    if (in.equals("sair")) {
                        exit = true;
                        break;
                    }
                    outToServer.writeBytes(in + '\n');
                    String resp = inFromServer.readLine();
                    String[] dir = resp.split(" ");
                    for (int i = 1; i < dir.length; i++) {
                        System.out.println(dir[i]);
                    }
                } catch (IOException ex) {
                    System.out.println("Erro na comunicação com o servidor");

                }
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
