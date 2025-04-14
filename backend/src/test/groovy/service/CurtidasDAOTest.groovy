package service

import groovy.sql.Sql
import model.CurtidaEmpresa
import spock.lang.Specification

class CurtidasDAOTest extends Specification {
    def sqlMock
    def curtidasDAO

    def setup(){
        sqlMock = Mock(Sql)
        curtidasDAO = new CurtidasDAO(sqlMock)
    }

    def "AddCurtidaCandidato"() {
        given: "Dado id de candidato e id de vaga"
        int id_candidato = 1
        int id_vaga = 1

        when: "Adiciona curtida de candidato na vaga"
        curtidasDAO.addCurtidaCandidato(id_candidato, id_vaga)

        then: "Executa INSERT INTO na tabela de curtida_candidato"
        1 * sqlMock.execute({String query -> query.contains("INSERT INTO curtidas_candidato")}, _)
    }

    def "AddCurtidaEmpresa"() {
        given: "Dado id de empresa e id de candidato"
        int id_empresa = 1
        int id_candidato = 1

        when: "Adiciona curtida de candidato na vaga"
        curtidasDAO.addCurtidaEmpresa(id_empresa, id_candidato)

        then: "Executa INSERT INTO na tabela de curtida_empresa"
        1 * sqlMock.execute({String query -> query.contains("INSERT INTO curtidas_empresa")}, _)
    }

    def "MostrarCurtidasCandidato"() {
        given:

        when: "mostra curtidas do usuario"
        curtidasDAO.mostrarCurtidasCandidato()

        then: "Executa SELECT nas curtidas dos candidatos"
        1 * sqlMock.eachRow({String query -> query.contains("SELECT") && query.contains("curtidas_candidato")}, _)
    }

    def "MostrarCurtidasEmpresa"() {
        given:

        when: "mostra curtidas da empresa"
        curtidasDAO.mostrarCurtidasEmpresa()

        then: "Executa SELECT nas curtidas dos candidatos"
        1 * sqlMock.eachRow({String query -> query.contains("SELECT") && query.contains("curtidas_empresa")}, _)
    }

    def "CurtidasEmpresasData"() {
        given: "Cria uma lista de curtidas de empresa"
        ArrayList<CurtidaEmpresa> curtidasEmpresas = new ArrayList<>()

        when: "Pega os dados de curtidas"
        curtidasEmpresas = curtidasDAO.curtidasEmpresasData()

        then: "Executa um SELECT nas curtidas de empresas"
        1 * sqlMock.eachRow({String query -> query.contains("SELECT") && query.contains("curtidas_empresa")}, _)

        and: "verifica se os dados foram buscados"
        assert curtidasEmpresas != null
    }

    def "CurtidasCandidatosData"() {
        given: "Cria uma lista de curtidas de empresa"
        ArrayList<CurtidaEmpresa> curtidasCandidatos = new ArrayList<>()

        when: "Pega os dados de curtidas"
        curtidasCandidatos = curtidasDAO.curtidasCandidatosData()

        then: "Executa um SELECT nas curtidas de candidatos"
        1 * sqlMock.eachRow({String query -> query.contains("SELECT") && query.contains("curtidas_candidato")}, _)

        and: "verifica se os dados foram buscados"
        assert curtidasCandidatos != null
    }
}
