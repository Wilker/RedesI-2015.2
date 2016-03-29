/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.SocketException;
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
            
//            System.out.println(File.separator);
//            System.out.println(File.pathSeparator);
            boolean exit = false;
            Menu.inicial();
            Socket clientSocket = new Socket("localhost", 9999);
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in)); //cria um leitor e associa ao teclado
            DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream()); // cria uma conexão de saída com o servidor
            BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String currentDirectory = "init";
            while (!exit) {
                try {
                    String in = inFromUser.readLine();
                    if (in.equals("sair")) {
                        exit = true;
                        break;
                    }
                    if (in.equals("..")) {
                        outToServer.writeBytes(in + " " + currentDirectory + '\n');
                        String resp = inFromServer.readLine();
                        currentDirectory = resp;
                        System.out.println(resp);
                    }
                    if (in.equals("ls")) {
                        outToServer.writeBytes(in + " " + currentDirectory + '\n');
                        String resp = inFromServer.readLine();
                        if (resp.equals("null")) {
                            System.out.println("permissão negada");
                        } else {
                            String[] dir = resp.split(" ");
                            for (int i = 1; i < dir.length; i++) {
                                System.out.println(dir[i]);
                            }
                        }
                    } else {
                        String[] s = in.split(" ");
                        if (s[0].equals("cd")) {
                            if (s.length == 2) {
                                outToServer.writeBytes(in + " " + currentDirectory + "\n");
                            } else {
                                System.out.println("Comando inválido");
                            }
                            String resp = inFromServer.readLine();
                            String[] r = resp.split(" ");
                            System.out.println(r[0]);
                            currentDirectory = r[1];
                        } else if (s[0].equals("get")) {
                            String saveDirectory;
                            saveDirectory = System.getProperty("user.dir");
                            outToServer.writeBytes(in + " " + currentDirectory + "\n");
                            String[] resp = inFromServer.readLine().split(" ");
                            if (resp[0].equals("")) {
                                System.out.println("Arquivo inexistente");
                            } else {
                                byte[] buffer = new byte[Integer.parseInt(resp[0])];
                                int bytesRead;
                                FileOutputStream file = new FileOutputStream(new File(saveDirectory + "\\" + resp[1]));
                                InputStream receive = clientSocket.getInputStream();
                                while ((bytesRead = receive.read(buffer)) != -1) {
                                    file.write(buffer, 0, bytesRead);
                                }
                                file.close();
                                System.out.println("Arquivo recebido, encerrando conexão...");
                                receive.close();
                                clientSocket.close();
                                break;
                            }
                        }
                    }
                } catch (SocketException ex) {
                    System.out.println("Conexao encerrada");
                } catch (IOException ex) {
                    System.out.println("Erro na comunicação com o servidor");

                }

            }
        } catch (IOException ex) {
            System.out.println("Erro"); //vou colocar uma msg melhor aqui, prometo!
        }
    }
}
