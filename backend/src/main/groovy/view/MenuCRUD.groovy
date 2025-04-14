package view

import groovy.sql.Sql
import model.Candidato
import model.Empresa
import service.*
import utils.Utils

import java.sql.SQLException

class MenuCRUD {
    static void showMenu() {
        Sql sql = Sql.newInstance(Sql.newInstance(Utils.dbConnParameters))

        CandidatosDAO candidatosDAO = new CandidatosDAO(sql)
        VagasDAO vagasDAO = new VagasDAO(sql)
        EmpresasDAO empresasDAO = new EmpresasDAO(sql)
        CurtidasDAO curtidasDAO = new CurtidasDAO(sql)
        CompetenciasDAO competenciasDAO = new CompetenciasDAO(sql)


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

            println "[17] Adicionar Curtida de Empresa"
            println "[18] Adicionar Curtida de Candidato"
            println "[19] Mostrar Curtidas do Candidato"
            println "[20] Mostrar Curtidas da Empresa"

            println "[21] Sair"
            print "> "

            def input = Utils.readInt()
            switch (input) {
                case '1':
                    candidatosDAO.listCandidatos()
                    break
                case '2':
                    Candidato candidatoAdd = candidatoInput()
                    candidatosDAO.addCandidato(candidatoAdd)
                    break
                case '3':
                    def choice = Utils.promptInputInt("ID do candidato")
                    candidatosDAO.deleteCandidato(choice)
                    break
                case '4':
                    def candidatoId = Utils.promptInputInt("ID do candidato")
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
                    def option = Utils.promptInputInt("Opcao")
                    def valor = Utils.promptInput("Novo valor")

                    if (option == 1) candidatosDAO.updateCandidato('nome', valor, candidatoId)
                    else if (option == 2) candidatosDAO.updateCandidato('cpf', valor, candidatoId)
                    else if (option == 3) candidatosDAO.updateCandidato('email', valor, candidatoId)
                    else if (option == 4) candidatosDAO.updateCandidato('descricao', valor, candidatoId)
                    else if (option == 5) candidatosDAO.updateCandidato('likedin_link', valor, candidatoId)
                    else if (option == 6) candidatosDAO.updateCandidato('data_nascimento', valor, candidatoId)
                    else if (option == 7) candidatosDAO.updateCandidato('senha', valor, candidatoId)
                    else if (option == 8) candidatosDAO.updateEnderecoCandidato('cidade', valor, candidatoId)
                    else if (option == 9) candidatosDAO.updateEnderecoCandidato('estado', valor, candidatoId)
                    else if (option == 10) candidatosDAO.updateEnderecoCandidato('pais', valor, candidatoId)
                    else if (option == 11) candidatosDAO.updateEnderecoCandidato('cep', valor, candidatoId)
                    else println "Opcao Invalida"
                    break
                case '5':
                    empresasDAO.listEmpresas()
                    break
                case '6':
                    empresasDAO.addEmpresa(empresaInput())
                    break
                case '7':
                    def idEmpresa = Utils.promptInputInt("ID da empresa para deletar")
                    empresasDAO.deleteEmpresa(idEmpresa)
                    break
                case '8':
                    def empresaId = Utils.promptInputInt("ID da empresa para atualizar")

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
                    def option = Utils.promptInputInt("Opcao")
                    def valor = Utils.promptInput("Novo valor")

                    if (option == 1) empresasDAO.updateEmpresa('nome', valor, empresaId)
                    else if (option == 2) empresasDAO.updateEmpresa('cpf', valor, empresaId)
                    else if (option == 3) empresasDAO.updateEmpresa('email', valor, empresaId)
                    else if (option == 4) empresasDAO.updateEmpresa('descricao', valor, empresaId)
                    else if (option == 5) empresasDAO.updateEmpresa('likedin_link', valor, empresaId)
                    else if (option == 6) empresasDAO.updateEmpresa('data_nascimento', valor, empresaId)
                    else if (option == 7) empresasDAO.updateEmpresa('senha', valor, empresaId)
                    else if (option == 8) empresasDAO.updateEnderecoEmpresa('cidade', valor, empresaId)
                    else if (option == 9) empresasDAO.updateEnderecoEmpresa('estado', valor, empresaId)
                    else if (option == 10) empresasDAO.updateEnderecoEmpresa('pais', valor, empresaId)
                    else if (option == 11) empresasDAO.updateEnderecoEmpresa('cep', valor, empresaId)
                    else println "Opcao Invalida"
                    break
                case '9':
                    vagasDAO.listVagasEmpresas()
                    break
                case '10':
                    def id_empresa = Utils.promptInputInt("ID da empresa da vaga")
                    def tituloVaga = Utils.promptInput("Titulo da vaga")
                    def descricaoVaga = Utils.promptInput("Descricao da vaga")
                    vagasDAO.addVaga(id_empresa, tituloVaga, descricaoVaga)
                    break
                case '11':
                    print "ID da vaga: "
                    def idVaga
                    idVaga = Utils.readInt()

                    println "Selecione uma propriedade para editar: "
                    println "[1] Titulo"
                    println "[2] Descricao"
                    def option = Utils.promptInput("Opcao")
                    def valor = Utils.promptInput("Novo valor")

                    if (option == 1) vagasDAO.updateVaga('titulo', valor, idVaga)
                    else if (option == 2) vagasDAO.updateVaga('descricao', valor, idVaga)
                    else println "Opcao Invalida"
                    break
                case '12':
                    def idVaga = Utils.promptInputInt("ID da vaga")
                    vagasDAO.deleteVaga(idVaga)
                    break
                case '13':
                    competenciasDAO.listCompetencias()
                    break
                case '14':
                    println "Adicionar competencia para:\n[1] Candidato\n[2] Vaga"
                    def option = Utils.promptInputInt("Opcao")
                    if (option == 1) {
                        def idCandidato = Utils.promptInputInt("ID do candidato")
                        def nomeCompetencia = Utils.promptInput("Nome da competencia")
                        competenciasDAO.addCompetenciaCandidato(nomeCompetencia, idCandidato)
                    } else if (option == 2) {
                        def idVaga = Utils.promptInputInt("ID da vaga")
                        def nomeCompetencia = Utils.promptInput("Nome da competencia")
                        competenciasDAO.addCompetenciaVaga(nomeCompetencia, idVaga)
                    } else println "Opcao Invalida"
                    break
                case '15':
                    def idCompetencia = Utils.promptInputInt("ID da competencia")
                    def novo = Utils.promptInput("Novo nome")
                    competenciasDAO.updateCompetencia(idCompetencia, novo)
                    break
                case '16':
                    def idCompetencia = Utils.promptInputInt("ID da competencia")
                    competenciasDAO.deleteCompetencia(idCompetencia)
                    break
                case '17':
                    def idEmpresa = Utils.promptInputInt("ID da empresa")
                    def idUsuario = Utils.promptInputInt("ID do usuario para curtir")
                    curtidasDAO.addCurtidaEmpresa(idEmpresa, idUsuario)
                    break
                case '18':
                    def idUsuario = Utils.promptInputInt("ID do usuario")
                    def idVaga = Utils.promptInputInt("ID da vaga para curtir")
                    curtidasDAO.addCurtidaCandidato(idUsuario, idVaga)
                    break
                case '19':
                    curtidasDAO.mostrarCurtidasCandidato()
                    break
                case '20':
                    curtidasDAO.mostrarCurtidasEmpresa()
                    break
                case '21':
                    try {
                        sql.close()
                    } catch(SQLException e){
                        println "Erro no banco de dados ao fechar conexao: $e.message"
                    } catch (Exception e){
                        println "Erro inesperado ao fechar conexao com banco de dados: $e.message"
                    }
                    return
                default:
                    println "Opcao Invalida"
                    break
            }
        }
    }

    static Candidato candidatoInput() {
        Candidato candidato = new Candidato()
        printf ""

        candidato.nome = Utils.promptInput("Nome")
        candidato.cpf = Utils.promptInput("CPF")
        candidato.email = Utils.promptInput("Email")
        candidato.descricao = Utils.promptInput("Descricao")
        candidato.linkedinLink = Utils.promptInput("Link do Linkedin")
        candidato.dataNascimento = Utils.promptInput("dataNascimento (dd-mm-yyyy)")
        candidato.cidade = Utils.promptInput("Cidade")
        candidato.estado = Utils.promptInput("Estado")
        candidato.pais = Utils.promptInput("Pais")
        candidato.cep = Utils.promptInput("CEP")
        candidato.senha = Utils.promptInput("Senha")
        return candidato
    }

    static Empresa empresaInput() {
        Empresa empresa = new Empresa()
        printf ""

        empresa.nome = Utils.promptInput("Nome")
        empresa.cnpj = Utils.promptInput("CNPJ")
        empresa.email = Utils.promptInput("Email")
        empresa.descricao = Utils.promptInput("Descricao")
        empresa.linkedinLink = Utils.promptInput("Link do Linkedin")
        empresa.senha = Utils.promptInput("Senha")
        empresa.cidade = Utils.promptInput("Cidade")
        empresa.estado = Utils.promptInput("Estado")
        empresa.pais = Utils.promptInput("Pais")
        empresa.cep = Utils.promptInput("CEP")
        return empresa
    }
}
