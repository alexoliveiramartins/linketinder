package service

import groovy.sql.Sql
import model.Vaga
import spock.lang.Specification

class VagasDAOTest extends Specification {
    def sqlMock
    def vagasDAO

    def setup(){
        sqlMock = Mock(Sql)
        vagasDAO = new VagasDAO(sqlMock)
    }

    def "AddVaga"() {
        given: "cria uma vaga"
        int id_empresa = 1
        String tituloVaga = 'Escovador de bits'
        String descricaoVaga = 'Saber assembly, cobol, fortran e Vue.js'

        when: "adiciona a vaga"
        vagasDAO.addVaga(id_empresa, tituloVaga, descricaoVaga)

        then: "executa um INSERT INTO no banco de dados"
        1 * sqlMock.execute({ String query -> query.contains("INSERT INTO") && query.contains("vagas")}, _)
    }

    def "listar vagas + nome empresa"() {
        given:

        when: "lista as vagas"
        vagasDAO.listVagasEmpresas()

        then: "executa um INSERT INTO no banco de dados"
        1 * sqlMock.eachRow({ String query -> query.contains("SELECT") && query.contains("FROM vagas")}, _)
    }

    def "listar todas as vagas"() {
        given:

        when: "lista as vagas"
        vagasDAO.listVagas()

        then: "executa um INSERT INTO no banco de dados"
        1 * sqlMock.eachRow({ String query -> query.contains("SELECT") && query.contains("FROM vagas")}, _)
    }

    def "UpdateVaga"() {
        given: "dado o campo e novo valor para atualizar na vaga"
        String campo = 'titulo'
        String novo = 'Suporte tecnico CVNet'
        int idVaga = 1

        when: "atualiza a vaga com o novo valor no campo selecionado"
        vagasDAO.updateVaga(campo, novo, idVaga)

        then: "executa um UDPATE no banco de dados"
        1 * sqlMock.execute({ String query -> query.contains("UPDATE") && query.contains("WHERE")}, _)
    }

    def "DeleteVaga"() {
        given: "dado o id da empresa para deletar"
        int idVaga = 1

        when: "atualiza a vaga com o novo valor no campo selecionado"
        vagasDAO.deleteVaga(idVaga)

        then: "executa um DELETE no banco de dados"
        1 * sqlMock.execute({ String query -> query.contains("DELETE") && query.contains("WHERE")}, _)
    }

    def "VagasData"() {
        given: "Cria uma lista de vagas"
        ArrayList<Vaga> vagasData = new ArrayList<>()

        when: "pega os dados de vagas"
        vagasData = vagasDAO.vagasData()

        then: "executa um SELECT no banco de dados"
        1 * sqlMock.eachRow({ String query -> query.contains("SELECT") && query.contains("FROM vagas")}, _)

        and: "verifica se a lista de vagas nao e null"
        assert vagasData != null
    }
}
