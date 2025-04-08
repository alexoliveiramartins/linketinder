package service

import groovy.sql.Sql
import model.Candidato

import java.text.SimpleDateFormat

class CandidatosDAO {
    Map dbConnParams = [
            'url'     : 'jdbc:postgresql://localhost:5432/linketinder-database',
            'user'    : 'postgres',
            'password': 'linketinder',
            'driver'  : 'org.postgresql.Driver'
    ]
    private sql

    void addCandidato(Candidato candidato) {
        sql = Sql.newInstance(dbConnParams)
        def sdf = new SimpleDateFormat("dd-MM-yyyy")
        def parsedDate = sdf.parse(candidato.dataNascimento)
        def sqlDate = new java.sql.Date(parsedDate.getTime())
        sql.execute(
                "INSERT INTO candidatos (nome, cpf, email, descricao, data_nascimento, linkedin_link, senha) VALUES (?, ?, ?, ?, ?, ?, ?)",
                [candidato.nome, candidato.cpf, candidato.email, candidato.descricao, sqlDate, candidato.linkedinLink, candidato.senha]
        )
        sql.execute(
                "INSERT INTO enderecos_candidatos (id_candidato, cidade, estado, pais, cep) VALUES ((SELECT id FROM candidatos WHERE email = ?), ?, ?, ?, ?)",
                [candidato.email, candidato.cidade, candidato.estado, candidato.pais, candidato.cep]
        )
        sql.close()
    }

    void listCandidatos() {
        sql = Sql.newInstance(dbConnParams)
        println "Candidatos:"
        sql.eachRow("""
                        SELECT c.id, c.nome, c.cpf, c.email, c.descricao, c.data_nascimento, c.linkedin_link, c.senha,
                        en.cidade, en.estado, en.pais, en.cep
                        FROM candidatos as c INNER JOIN enderecos_candidatos as en ON en.id_candidato = c.id;
                        """) { row ->
            println row
        }
        sql.close()
    }

    void updateCandidato(String campo, String novo, int idCandidato) {
        sql = Sql.newInstance(dbConnParams)
        sql.execute("""
            UPDATE candidatos SET ${campo} = ? WHERE id = ?;
        """, [novo, idCandidato])
        sql.close()
    }

    void updateEnderecoCandidato(String campo, String novo, int idCandidato) {
        sql = Sql.newInstance(dbConnParams)
        sql.execute("UPDATE enderecos_candidatos SET ${campo} = ? WHERE id_candidato = ?;", [novo, idCandidato])
        sql.close()
    }

    void deleteCandidato(int id) {
        sql = Sql.newInstance(dbConnParams)
        sql.execute("DELETE FROM enderecos_candidatos WHERE id_candidato = ?;", [id])
        sql.execute("DELETE FROM candidatos WHERE id = ?;", [id])
        sql.close()
    }

    List<Candidato> candidatosData() {
        sql = Sql.newInstance(dbConnParams)

        ArrayList<Candidato> candidatosData = new ArrayList<>()

        sql.eachRow("""
            SELECT c.id, c.nome, c.cpf, c.email, c.descricao, c.data_nascimento, c.linkedin_link, c.senha,
            en.cidade, en.estado, en.pais, en.cep
            FROM candidatos as c INNER JOIN enderecos_candidatos as en ON en.id_candidato = c.id;
        """) {
            row ->
                {
                    Candidato candidatoItem = new Candidato()
                    candidatoItem.id = row.id
                    candidatoItem.nome = row.nome
                    candidatoItem.cpf = row.cpf
                    candidatoItem.email = row.email
                    candidatoItem.descricao = row.descricao
                    candidatoItem.linkedinLink = row.linkedin_link
                    candidatoItem.dataNascimento = row.data_nascimento
                    candidatoItem.cidade = row.cidade
                    candidatoItem.estado = row.estado
                    candidatoItem.pais = row.pais
                    candidatoItem.cep = row.cep
                    candidatoItem.senha = row.senha

                    candidatosData.push(candidatoItem)
                }
        }
        sql.close()
        return candidatosData
    }
}
