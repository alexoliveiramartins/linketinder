package view

import model.Candidato
import model.Empresa
import service.PessoasData

class Menu {
    static void showMenu(PessoasData data){

        Scanner sc = new Scanner(System.in)

        while(true){
            println "==== Menu ===="
            println "Escolha uma opcao: "
            println "[1] Listar Usuários"
            println "[2] Listar Empresas"
            println "[3] Adicionar Empresa"
            println "[4] Adicionar Pessoa"
            println "[5] Sair"
            print "> "

            String input = sc.nextLine()
            switch(input){
                case '1':
                    data.candidatos.each {println it.toString()}
                    break
                case '2':
                    data.empresas.each {println it.toString()}
                    break
                case '3':
                    Empresa empresa = new Empresa()
                    print "nome: "
                    input = sc.nextLine()
                    empresa.nome = input
                    print "email: "
                    input = sc.nextLine()
                    empresa.email = input
                    print "pais: "
                    input = sc.nextLine()
                    empresa.pais = input
                    print "estado: "
                    input = sc.nextLine()
                    empresa.estado = input
                    print "descricao: "
                    input = sc.nextLine()
                    empresa.descricao = input
                    print "cnpj: "
                    input = sc.nextLine()
                    empresa.cnpj = input
                    data.addEmpresa(empresa)
                    break
                    return
                case '4':
                    Candidato pessoa = new Candidato()
                    print "nome: "
                    input = sc.nextLine()
                    pessoa.nome = input
                    print "cpf: "
                    input = sc.nextLine()
                    pessoa.cpf = input
                    print "email: "
                    input = sc.nextLine()
                    pessoa.email = input
                    print "estado: "
                    input = sc.nextLine()
                    pessoa.estado = input
                    print "descricao: "
                    input = sc.nextLine()
                    pessoa.descricao = input
                    print "cep: "
                    input = sc.nextLine()
                    pessoa.cep = input
                    print "idade: "
                    input = sc.nextLine()
                    pessoa.idade = input as Integer
                    data.addCandidato(pessoa)
                    break
                    return
                default:
                    break
            }
        }
    }
}
