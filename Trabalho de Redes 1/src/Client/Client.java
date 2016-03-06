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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author wilkerrj
 */
public class Client {

    public static void main(String[] args) throws ClassNotFoundException {
        boolean exit = false;
        Menu.inicial();
        while (!exit) {

            try {
                Socket clientSocket = new Socket("localhost", 9999);
                BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); //cria um leitor e associa ao teclado
                DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); // cria uma conexão de saída com o servidor
                BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                //   ObjectInputStream inObjectFromServer = new ObjectInputStream(clientSocket.getInputStream());
                String in = inFromUser.readLine();
                if (in.equals("sair")) {
                    exit = true;
                    break;
                }
                outToServer.writeBytes(in + '\n');
                System.out.println(inFromServer.readLine());
//                String[] resp = (String[])inObjectFromServer.readObject();
//                for (String string : resp) {
//                    System.out.println(string);
//                }
            } catch (IOException ex) {
                System.out.println("Erro na comunicação com o servidor");

            }
        }
    }
}
