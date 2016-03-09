/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.io.File;

/**
 * Classe de decodificação dos comando recebidos do cliente
 *
 * @author Wilker
 */
public class Operator {

    protected static String listDir(String dir) {
        File file = new File(dir);
        File[] files = file.listFiles();
        String r = "";
        int i = 0;
        for (File f : files) {
            r += " ";
            r += f.getPath();
            i++;
        }
        return r;
    }

    protected static String enter(String dir, String current) {
        File file = new File(current);
        File[] files = file.listFiles(new Filter());
        for (File f : files) {
            if (f.isDirectory() && f.getParent().contains(dir)) {
                return dir;
            }
        }
        return current;
    }

    protected static String back(String current) {
        File file = new File(current);
        String r = file.getParent();
        return r;
    }
}
