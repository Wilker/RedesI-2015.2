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

    protected static String[] listDir(String dir) {
        File file = new File(dir);
        File[] files = file.listFiles(new Filter());
        String[] r = new String[files.length];
        int i = 0;
        for (File f : files) {
            r[i] = f.getPath();
            i++;
        }
        return r;
    }

    protected static String[] enter(String dir, String current) {
        File file = new File(current);
        File[] files = file.listFiles(new Filter());
        String[] r = new String[1];
        for (File f : files) {
            if (f.isDirectory() && f.getParent().contains(dir)) {
                r[1] = dir;
                return r;
            }
        }
        r[1] = current;
        return r;
    }

    protected static String[] back(String current) {
        File file = new File(current);
        String[] r = new String[1];
        r[1] = file.getParent();
        return r;
    }
}
