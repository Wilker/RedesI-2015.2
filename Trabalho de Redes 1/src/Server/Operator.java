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
        File[] files = file.listFiles();//removido filtro pois no lab usa o JAVA6
        if(files==null){
            return null;
        }
            
        String r = "";
        int i = 0;
        for (File f : files) {
            r += " ";
            r += f.getPath();
            i++;
        }
        return r;
    }

    protected static String enter(String dir, String current) {// Deve retornar o novo diretório entrado;
        File file = new File(current);
        File[] files = file.listFiles();//removido filtro pois no lab usa o JAVA6
        for (File f : files) {
            if (f.isDirectory() && f.getName().equals(dir)) {
                System.out.println("Entrou aaqui!");
                return f.getPath() + " " + f.getPath();//posso mudar aqui e retornar só um, mas terei q ajustar no cliente para responder a mensagem de erro pré determinada
            }
        }
        return "Diretorio_nao_encontrado " + current;
    }

    protected static String back(String current) {
        File file = new File(current);
        String r = file.getParent();
        if(r == null){
            return current;
        }
        return r;
    }
}