/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

/**
 *
 * @author Wilker
 */
public class Menu {

    static void inicial() {
        System.out.println("\t*******Menu*******");
        System.out.println("Digite 'ls' para listar o diretório");
        System.out.println("exemplo: ls");
        System.out.println("Digite 'cd dir' para entrar em um diretório");
        System.out.println("exemplo: cd Windows");
        System.out.println("Digite '..' para voltar um diretório");
        System.out.println("exemplo: ..");
        System.out.println("Digite 'get nome_do_arquivo local_para_salvar' para tranferir um arquivo");
        System.out.println("exemplo: get foto.jpeg c:\\Usuários\\Wilker\\Desktop");
        System.out.println("Digite 'sair' para encerrar a conexão");
    }
}
