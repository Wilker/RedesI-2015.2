/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author wilkerrj
 */
public class Decoder {

    private String[] dir;
    private String currentDirectory;
    private String defaultDirectory;

    /**
     * Recebe um InputStream e decodifica verificando o comando a ser realizado;
     *
     * @param in
     * @throws IOException
     * @throws Exception
     */
    String decode(InputStream in) throws IOException, IllegalArgumentException {
        BufferedReader inFromClient = new BufferedReader(new InputStreamReader(in)); //recebe o InputStream do cliente
        defaultDirectory = setDefaultDirectory();
        currentDirectory = defaultDirectory;
        dir = inFromClient.readLine().split(" ");
        if (validate(dir)) {
            if (dir[0].equals("ls")) {
                if (!dir[1].equals("init")) {
                    currentDirectory = dir[1];
                }
                return listDirectory(currentDirectory);


            } else if (dir[0].equals("cd")) {
                if (!dir[2].equals("init")) {
                    currentDirectory = dir[2];
                }
                return enterDirectory(dir[1], currentDirectory);


            } else if (dir[0].equals("..")) {
                if (!dir[1].equals("init")) {
                    currentDirectory = dir[1];
                }
                return backDirectory(currentDirectory);


            } else if (dir[0].equals("get")) {
                //TODO
                getFile(dir[1]);
                return null;
            }
        } else {
            throw new IllegalArgumentException("Invalid Command");
        }
        return null;//TODO
    }

    private String listDirectory(String currentDirectory) {
        return Operator.listDir(currentDirectory);
    }

    private String enterDirectory(String string, String currentDirectory) {
        return Operator.enter(string, currentDirectory);
    }

    private static String backDirectory(String currentDirectory) {
        return Operator.back(currentDirectory);
    }

    private static void getFile(String string) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private static boolean validate(String[] s) {
        if (s.length > 3) {
            return false;
        }
        if (s[0].equals("ls") || s[0].equals("cd")
                || s[0].equals("..") || s[0].equals("get")) {
            return true;
        }
        return false;
    }

    private String setDefaultDirectory() {
        String system = System.getProperty("os.name");
        if (system.toLowerCase().contains("windows")) {
            return "c:\\";
        }
        return "/home";
    }
}
