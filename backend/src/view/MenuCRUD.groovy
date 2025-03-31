package view

import model.Candidato
import model.Empresa
import service.PessoasData
import service.UsuariosDAO
import utils.Utils

class MenuCRUD {
    static void showMenu(PessoasData data) {
        UsuariosDAO usuariosDAO = new UsuariosDAO()

        while (true) {
            println "==== Menu ===="
            println "Escolha uma opcao: "
            println "[1] Listar Candidatos"
            println "[2] Adicionar Candidato"
            println "[3] Remover Candidato"
            println "[4] Atualizar Candidato"

            println "[5] Listar Empresas"
            println "[6] Adicionar Empresa"
            println "[7] Remover Empresa"
            println "[8] Atualizar Empresa"

            println "[9] Listar Vagas"
            println "[10] Adicionar Vaga"
            println "[11] Editar Vaga"
            println "[12] Deletar Vaga"

            println "[13] Listar Competencias"
            println "[14] Adicionar Competencia"
            println "[15] Editar Competencia"
            println "[16] Remover Competencia"

            println "[17] Adicionar Curtida"

            println "[18] Sair"
            print "> "

            def input = Utils.readInt()
            switch (input) {
                case '1': // DONE
                    usuariosDAO.listCandidatos()
                    break
                case '2': // DONE
                    Candidato candidatoAdd = candidatoInput()
                    usuariosDAO.addCandidato(candidatoAdd)
                    break
                case '3': // DONE
                    print "ID do candidato: "
                    def choice = Utils.readInt()
                    usuariosDAO.deleteCandidato(choice)
                    break
                case '4': // DONE
                    print "ID do Candidato: "
                    def candidatoId
                    candidatoId = Utils.readInt()

                    println "Selecione uma propriedade para editar: "
                    println "[1] Nome"
                    println "[2] CPF"
                    println "[3] Email"
                    println "[4] Descricao"
                    println "[5] Link Linkedin"
                    println "[6] Data Nascimento"
                    println "[7] Senha"
                    println "[8] Cidade"
                    println "[9] Estado"
                    println "[10] Pais"
                    println "[11] Cep"
                    printf "> "
                    def option
                    option = Utils.readInt()

                    println "Digite o novo valor:"
                    printf "> "
                    def valor
                    valor = Utils.readLine()

                    if (option == 1) usuariosDAO.updateCandidato('nome', valor, candidatoId)
                    else if (option == 2) usuariosDAO.updateCandidato('cpf', valor, candidatoId)
                    else if (option == 3) usuariosDAO.updateCandidato('email', valor, candidatoId)
                    else if (option == 4) usuariosDAO.updateCandidato('descricao', valor, candidatoId)
                    else if (option == 5) usuariosDAO.updateCandidato('likedin_link', valor, candidatoId)
                    else if (option == 6) usuariosDAO.updateCandidato('data_nascimento', valor, candidatoId)
                    else if (option == 7) usuariosDAO.updateCandidato('senha', valor, candidatoId)
                    else if (option == 8) usuariosDAO.updateEnderecoCandidato('cidade', valor, candidatoId)
                    else if (option == 9) usuariosDAO.updateEnderecoCandidato('estado', valor, candidatoId)
                    else if (option == 10) usuariosDAO.updateEnderecoCandidato('pais', valor, candidatoId)
                    else if (option == 11) usuariosDAO.updateEnderecoCandidato('cep', valor, candidatoId)
                    else println "Opcao Invalida"
                    break
                case '5': // DONE
                    usuariosDAO.listEmpresas()
                    break
                case '6': // DONE
                    usuariosDAO.addEmpresa(empresaInput())
                    break
                case '7': // DONE
                    println "Digite o ID da empresa para deletar: "
                    printf "> "
                    def idEmpresa = Utils.readInt()
                    usuariosDAO.deleteEmpresa(idEmpresa)
                    break
                case '8': // DONE
                    print "ID da empresa: "
                    def empresaId
                    empresaId = Utils.readInt()

                    println "Selecione uma propriedade para editar: "
                    println "[1] Nome"
                    println "[2] CPF"
                    println "[3] Email"
                    println "[4] Descricao"
                    println "[5] Link Linkedin"
                    println "[6] Data Nascimento"
                    println "[7] Senha"
                    println "[8] Cidade"
                    println "[9] Estado"
                    println "[10] Pais"
                    println "[11] Cep"
                    printf "> "
                    def option
                    option = Utils.readInt()

                    println "Digite o novo valor:"
                    printf "> "
                    def valor
                    valor = Utils.readLine()

                    if (option == 1) usuariosDAO.updateEmpresa('nome', valor, empresaId)
                    else if (option == 2) usuariosDAO.updateEmpresa('cpf', valor, empresaId)
                    else if (option == 3) usuariosDAO.updateEmpresa('email', valor, empresaId)
                    else if (option == 4) usuariosDAO.updateEmpresa('descricao', valor, empresaId)
                    else if (option == 5) usuariosDAO.updateEmpresa('likedin_link', valor, empresaId)
                    else if (option == 6) usuariosDAO.updateEmpresa('data_nascimento', valor, empresaId)
                    else if (option == 7) usuariosDAO.updateEmpresa('senha', valor, empresaId)
                    else if (option == 8) usuariosDAO.updateEnderecoEmpresa('cidade', valor, empresaId)
                    else if (option == 9) usuariosDAO.updateEnderecoEmpresa('estado', valor, empresaId)
                    else if (option == 10) usuariosDAO.updateEnderecoEmpresa('pais', valor, empresaId)
                    else if (option == 11) usuariosDAO.updateEnderecoEmpresa('cep', valor, empresaId)
                    else println "Opcao Invalida"
                    break
                case '9':
                    usuariosDAO.listVagasEmpresas()
                    break
                case '10': // DONE
                    println "Digite o ID da empresa da vaga"
                    printf "> "
                    def id_empresa = Utils.readInt()
                    printf "Digite o Titulo da vaga: "
                    def tituloVaga = Utils.readLine()
                    printf "Digite a descricao da vaga: "
                    def descricaoVaga = Utils.readLine()
                    usuariosDAO.addVaga(id_empresa, tituloVaga, descricaoVaga)
                    break
                case '11': // DONE
                    print "ID da vaga: "
                    def idVaga
                    idVaga = Utils.readInt()

                    println "Selecione uma propriedade para editar: "
                    println "[1] Titulo"
                    println "[2] Descricao"
                    printf "> "
                    def option
                    option = Utils.readInt()

                    println "Digite o novo valor:"
                    printf "> "
                    def valor
                    valor = Utils.readLine()

                    if (option == 1) usuariosDAO.updateVaga('titulo', valor, idVaga)
                    else if (option == 2) usuariosDAO.updateVaga('descricao', valor, idVaga)
                    else println "Opcao Invalida"
                    break
                case '12': // DONE
                    print "ID da vaga: "
                    def idVaga
                    idVaga = Utils.readInt()
                    usuariosDAO.deleteVaga(idVaga)
                    break
                case '13': // DONE
                    usuariosDAO.listCompetencias()
                    break
                case '14': // DONE
                    println "Adicionar competencia para: [1] Candidato [2] Vaga"
                    printf "> "
                    def option = Utils.readInt()
                    if (option == 1) {
                        println "Digite o id do candidato"
                        printf "> "
                        def idCandidato = Utils.readInt()

                        printf "Digite o nome da competencia: "
                        def nomeCompetencia = Utils.readLine()

                        usuariosDAO.addCompetenciaCandidato(nomeCompetencia, idCandidato)
                    } else if (option == 2) {
                        println "Digite o id da vaga"
                        printf "> "
                        def idVaga = Utils.readInt()

                        printf "Digite o nome da competencia: "
                        def nomeCompetencia = Utils.readLine()

                        usuariosDAO.addCompetenciaVaga(nomeCompetencia, idVaga)
                    } else println "Opcao Invalida"
                    break
                case '15': // DONE
                    printf "Digite o id da competencia: "
                    def idCompetencia = Utils.readInt()
                    printf "Digite o novo nome: "
                    def novo = Utils.readLine()
                    usuariosDAO.updateCompetencia(idCompetencia, novo)
                    break
                case '16': // DONE
                    printf "Digite o id da competencia: "
                    def idCompetencia = Utils.readInt()
                    usuariosDAO.deleteCompetencia(idCompetencia)
                    break
                case '17':
                    printf "Digite o id do usuario: "
                    def idUsuario = Utils.readInt()
                    printf "Digite o id da vaga para curtir: "
                    def idVaga = Utils.readInt()
                    usuariosDAO.addCurtida(idUsuario, idVaga)
                    break
                case '18':
                    return
                default:
                    println "Opcao Invalida"
                    break
            }
        }
    }

    static Candidato candidatoInput() {
        Scanner sc = new Scanner(System.in)
        Candidato candidato = new Candidato()
        def input
        printf ""

        printf "nome: "
        input = sc.nextLine()
        candidato.nome = input

        printf "cpf: "
        input = sc.nextLine()
        candidato.cpf = input

        printf "email: "
        input = sc.nextLine()
        candidato.email = input

        printf "descricao: "
        input = sc.nextLine()
        candidato.descricao = input

        printf "linkedinLink: "
        input = sc.nextLine()
        candidato.linkedinLink = input

        printf "dataNascimento (dd-mm-yyyy): "
        input = sc.nextLine()
        candidato.dataNascimento = input

        printf "cidade: "
        input = sc.nextLine()
        candidato.cidade = input

        printf "estado: "
        input = sc.nextLine()
        candidato.estado = input

        printf "pais: "
        input = sc.nextLine()
        candidato.pais = input

        printf "cep: "
        input = sc.nextLine()
        candidato.cep = input

        printf "senha: "
        input = sc.nextLine()
        candidato.senha = input

        return candidato
    }

    static Empresa empresaInput() {
        Scanner sc = new Scanner(System.in)
        def input
        Empresa empresa = new Empresa()
        printf ""

        print "nome: "
        input = Utils.readLine()
        empresa.nome = input
        print "cnpj: "
        input = Utils.readLine()
        empresa.cnpj = input
        print "email: "
        input = Utils.readLine()
        empresa.email = input
        print "descricao: "
        input = Utils.readLine()
        empresa.descricao = input
        print "Link Linkedin: "
        input = Utils.readLine()
        empresa.linkedinLink = input
        print "Senha: "
        input = Utils.readLine()
        empresa.senha = input
        print "cidade: "
        input = Utils.readLine()
        empresa.cidade = input
        print "estado: "
        input = Utils.readLine()
        empresa.estado = input
        print "pais: "
        input = Utils.readLine()
        empresa.pais = input
        print "cep: "
        input = Utils.readLine()
        empresa.cep = input

        return empresa
    }
}
