package view

import model.PessoaFisica
import model.PessoaJuridica

class Menu {
    static void showMenu(List<PessoaFisica> candidatos, List<PessoaJuridica> empresas){
        Scanner sc = new Scanner(System.in);

        while(true){
            println "==== Menu ===="
            println "Escolha uma opcao: "
            println "[1] Listar Usuários"
            println "[2] Lista Empresas"
            println "[3] Sair"
            print "> "

            String input = sc.nextLine();
            switch(input){
                case '1':
                    candidatos.each {println it.toString()}
                    break;
                case '2':
                    empresas.each {println it.toString()}
                    break;
                case '3':
                    return
                default:
                    break;
            }
        }
    }
}
