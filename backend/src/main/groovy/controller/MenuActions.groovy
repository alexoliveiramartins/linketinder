package controller

import dao.CandidatosDAO
import dao.CompetenciasDAO
import dao.CurtidasDAO
import dao.EmpresasDAO
import dao.VagasDAO
import groovy.sql.Sql
import model.Candidato
import model.Empresa
import model.SqlInstance
import utils.Utils

class MenuActions {
    Sql sql = SqlInstance.getInstance().sqlConnection

    CandidatosDAO candidatosDAO = new CandidatosDAO(sql)
    VagasDAO vagasDAO = new VagasDAO(sql)
    EmpresasDAO empresasDAO = new EmpresasDAO(sql)
    CurtidasDAO curtidasDAO = new CurtidasDAO(sql)
    CompetenciasDAO competenciasDAO = new CompetenciasDAO(sql)

    void listCandidatos() {
        def candidatos = candidatosDAO.candidatosData()
        candidatos.each { it -> println it }
    }

    void addCandidato(Candidato candidato) {
        candidatosDAO.addCandidato(candidato)
    }

    void updateCandidato(int option, String valor, int candidatoId) {
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
    }

    void deleteCandidato(int option) {
        candidatosDAO.deleteCandidato(option)
    }

    void listEmpresas() {
        def empresas = empresasDAO.empresasData()
        empresas.each { it -> println it }
    }

    void addEmpresa(Empresa empresa) {
        empresasDAO.addEmpresa(empresa)
    }

    void deleteEmpresa(int idEmpresa) {
        empresasDAO.deleteEmpresa(idEmpresa)
    }

    void updateEmpresa(int option, String valor, int empresaId) {
        if (option == 1) empresasDAO.updateEmpresa('nome', valor, empresaId)
        else if (option == 2) empresasDAO.updateEmpresa('cnpj', valor, empresaId)
        else if (option == 3) empresasDAO.updateEmpresa('email', valor, empresaId)
        else if (option == 4) empresasDAO.updateEmpresa('descricao', valor, empresaId)
        else if (option == 5) empresasDAO.updateEmpresa('likedin_link', valor, empresaId)
        else if (option == 6) empresasDAO.updateEmpresa('senha', valor, empresaId)
        else if (option == 7) empresasDAO.updateEnderecoEmpresa('cidade', valor, empresaId)
        else if (option == 8) empresasDAO.updateEnderecoEmpresa('estado', valor, empresaId)
        else if (option == 9) empresasDAO.updateEnderecoEmpresa('pais', valor, empresaId)
        else if (option == 10) empresasDAO.updateEnderecoEmpresa('cep', valor, empresaId)
        else println "Opcao Invalida"
    }

    void listVagas() {
        def vagas = vagasDAO.vagasData()
        vagas.each { it -> println it }
    }

    void addVaga(int id_empresa, String tituloVaga, String descricaoVaga) {
        vagasDAO.addVaga(id_empresa, tituloVaga, descricaoVaga)
    }

    void updateVaga(int option, String valor, int idVaga) {
        if (option == 1) vagasDAO.updateVaga('titulo', valor, idVaga)
        else if (option == 2) vagasDAO.updateVaga('descricao', valor, idVaga)
        else println "Opcao Invalida"
    }

    void deleteVaga(int idVaga) {
        vagasDAO.deleteVaga(idVaga)
    }

    void listCompetencias() {
        def competencias = competenciasDAO.competenciaData()
        competencias.each { it -> println it }
    }

    void addCompetencia(int option) {
        if (option == 1) {
            def idCandidato = Utils.promptInputInt("ID do candidato")
            def nomeCompetencia = Utils.promptInput("Nome da competencia")
            competenciasDAO.addCompetenciaCandidato(nomeCompetencia, idCandidato)
        } else if (option == 2) {
            def idVaga = Utils.promptInputInt("ID da vaga")
            def nomeCompetencia = Utils.promptInput("Nome da competencia")
            competenciasDAO.addCompetenciaVaga(nomeCompetencia, idVaga)
        } else println "Opcao Invalida"
    }

    void updateCompetencia(int idCompetencia, String novo) {
        competenciasDAO.updateCompetencia(idCompetencia, novo)
    }

    void deleteCompetencia(int idCompetencia) {
        competenciasDAO.deleteCompetencia(idCompetencia)
    }

    void addCurtidaEmpresa(int idEmpresa, int idUsuario) {
        curtidasDAO.addCurtidaEmpresa(idEmpresa, idUsuario)
    }

    void addCurtidaCandidato(int idUsuario, int idVaga) {
        curtidasDAO.addCurtidaCandidato(idUsuario, idVaga)
    }

    void mostrarCurtidasCandidato() {
        def curtidasCandidato = curtidasDAO.curtidasCandidatosData()
        curtidasCandidato.each { it ->
            println candidatosDAO.getCandidatoById(it.id_candidato).nome + " <3 " +
                    vagasDAO.getVagaById(it.id_vaga).titulo
        }
    }

    void mostrarCurtidasEmpresa() {
        def curtidasEmpresa = curtidasDAO.curtidasEmpresasData()
        curtidasEmpresa.each { it ->
            println empresasDAO.getEmpresaById(it.id_empresa).nome + " <3 " +
                    candidatosDAO.getCandidatoById(it.id_candidato).nome
        }
    }

}
