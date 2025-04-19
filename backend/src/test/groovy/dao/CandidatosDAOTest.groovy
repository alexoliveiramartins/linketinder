package dao

import dao.CandidatosDAO
import groovy.sql.Sql
import model.Candidato
import spock.lang.Specification

class CandidatosDAOTest extends Specification {
    def sqlMock
    def candidatosDAO

    def setup(){
        sqlMock = Mock(Sql)
        candidatosDAO = new CandidatosDAO(sqlMock)
    }

    def "Retornar candidato por id no banco de dados"(){
        given: "dado um id de candidato"
        def id = 1

        when: "solicita um candidato pelo id"
        candidatosDAO.getCandidatoById(id)

        then: "executa um SELECT com WHERE"
        1 * sqlMock.eachRow({ String query -> query.contains("SELECT") && query.contains("WHERE")}, [id], _)
    }

    def "Adicionar candidato no banco de dados"() {
        given: "Cria um candidato"
        def candidato = new Candidato(
                nome: "João",
                cpf: "11111111111",
                email: "joao@email.com",
                descricao: "Backend Dev",
                dataNascimento: "01-01-1990",
                linkedinLink: "linkedin.com/in/joao",
                senha: "senha123",
                cidade: "Goiânia",
                estado: "GO",
                pais: "Brasil",
                cep: "74000000"
        )

        when: "Chama o metodo de adicionar candidato"
        candidatosDAO.addCandidato(candidato)

        then: "Insert candidato e executado"
        1 * sqlMock.execute({ String query -> query.contains("INSERT INTO candidatos") }, _)

        and: "Insert endereco do candidato e executado"
        1 * sqlMock.execute({ String query -> query.contains("INSERT INTO enderecos_candidatos") }, _)
    }

    def "Atualizar candidatos no banco de dados"() {
        given: "Cria argumento de campo, novo valor e id de candidato"
        String campo = 'nome'
        String novo = 'Alex'
        int idCandidato = 1

        when: "Atualiza o campo com novo valor de um usuario"
        candidatosDAO.updateCandidato(campo, novo, idCandidato)

        then: "Executa um UPDATE na tabela de candidatos no banco de dados"
        1 * sqlMock.execute ({ String query -> query.contains("UPDATE candidatos SET") }, _)
    }

    def "Atualizar endereco de candidato"() {
        given: "Cria argumento de campo, novo valor e id de candidato"
        String campo = "cep"
        String novo = "74555707"
        int idCandidato = 1

        when: "Atualiza o campo do endereco com novo valor de um usuario"
        candidatosDAO.updateEnderecoCandidato(campo, novo, idCandidato)

        then: "Executa um UPDATE na tabela de enderecos_candidatos no banco de dados"
        1 * sqlMock.execute ({ String query -> query.contains("UPDATE enderecos_candidatos SET") }, _)
    }

    def "Deletar candidato no banco de dados"() {
        given: "Cria um int de id de candidato"
        def id = 1

        when: "Deleta um candidato"
        candidatosDAO.deleteCandidato(id)

        then: "Executa um DELETE com WHERE na tabela de candidatos no banco de dados"
        1 * sqlMock.execute ({ String query -> query.contains("DELETE FROM candidatos WHERE id") }, _)
    }

    def "CandidatosData"() {
        given: "Instancia uma lista de candidatos"
        def candidatosData

        when: "Chama a funcao que retorna os dados dos candidatos"
        candidatosData = candidatosDAO.candidatosData()

        then: "Seleciona os candidatos no banco de dados"
        1 * sqlMock.eachRow({ String query -> query.contains("SELECT") && query.contains("FROM candidatos") && query.contains("INNER JOIN enderecos_candidatos") }, _)

        and:
        candidatosData != null
    }
}
