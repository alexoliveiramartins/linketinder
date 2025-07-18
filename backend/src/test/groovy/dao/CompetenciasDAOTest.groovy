package dao

import dao.CompetenciasDAO
import groovy.sql.Sql
import model.Competencia
import spock.lang.Specification

class CompetenciasDAOTest extends Specification {
    def sqlMock
    def competenciasDAO

    def setup(){
        sqlMock = Mock(Sql)
        competenciasDAO = new CompetenciasDAO(sqlMock)
    }

    def "adicionar competencia a vaga"() {
        // A FAZER
    }

    def "AddCompetenciaCandidato"() {
        // A FAZER
    }

    def Update() {
        given: "Dado um id de competencia e nome para atualizar"
        int id = 1
        String nome = "JAVASCRIPTO"

        when: "Atualizar uma competencia"
        competenciasDAO.update("nome", nome, id)

        then: "Executa o UPDATE no bd"
        1 * sqlMock.execute({String query -> query.contains("UPDATE competencias") && query.contains("SET nome")}, _)
    }

    def Delete() {
        given: "Dado um id de competencia"
        int id = 1

        when: "Deletar uma competencia"
        competenciasDAO.delete(id)

        then: "Executa o DELETE no bd"
        1 * sqlMock.execute({String query -> query.contains("DELETE FROM")}, _)
    }

    def "DeleteCompetenciaVaga"() {
        given: "Dado um id de competencia e id de vaga"
        int id_competencia = 1
        int id_vaga = 1

        when: "Deletar uma competencia de uma vaga"
        competenciasDAO.deleteCompetenciaVaga(id_vaga, id_competencia)

        then: "Executa o DELETE no bd"
        1 * sqlMock.execute({String query -> query.contains("DELETE FROM vaga_competencia") && query.contains("WHERE")}, _)
    }

    def "DeleteCompetenciaCandidato"() {
        given: "Dado um id de competencia e id de candidato"
        int id_competencia = 1
        int id_candidato = 1

        when: "Deletar uma competencia"
        competenciasDAO.deleteCompetenciaCandidato(id_candidato, id_competencia)

        then: "Executa o DELETE no bd"
        1 * sqlMock.execute({String query -> query.contains("DELETE FROM candidato_competencia") && query.contains("WHERE")}, _)
    }

    def "CompetenciaData"() {
        given: "Criar uma lista de competencias"
        ArrayList<Competencia> competenciaData = new ArrayList<>()

        when: "Pega os dados de competencias"
        competenciaData = competenciasDAO.competenciaData()

        then: "Executa o SELECT no bd"
        1 * sqlMock.eachRow({String query -> query.contains("SELECT * FROM competencias")}, _)

        and: "retorna a lista de competencias"
        competenciaData != null
    }
}
