package controller

import groovy.sql.Sql
import model.Candidato
import utils.Utils

import java.text.SimpleDateFormat

class CandidatosDAO {
    final Sql sql

    CandidatosDAO(Sql sql) {
        this.sql = sql
    }

    Candidato getCandidatoById(int id) {
        def candidato = new Candidato()
        Utils.dbErrorHandling("retornar candidato por id", {
            sql.eachRow(
                    "SELECT * FROM candidatos AS c JOIN enderecos_candidatos AS e ON e.id_candidato = c.id WHERE c.id = ?;",
                    [id]
            ) { candidatoResult ->
                candidato.id = candidatoResult.id
                candidato.nome = candidatoResult.nome
                candidato.cpf = candidatoResult.cpf
                candidato.email = candidatoResult.email
                candidato.descricao = candidatoResult.descricao
                candidato.linkedinLink = candidatoResult.linkedin_link
                candidato.dataNascimento = candidatoResult.data_nascimento
                candidato.cidade = candidatoResult.cidade
                candidato.estado = candidatoResult.estado
                candidato.pais = candidatoResult.pais
                candidato.cep = candidatoResult.cep
                candidato.senha = candidatoResult.senha
            }
        })
        return candidato
    }

    void addCandidato(Candidato candidato) {
        def sdf = new SimpleDateFormat("dd-MM-yyyy")
        def parsedDate = sdf.parse(candidato.dataNascimento)
        def sqlDate = new java.sql.Date(parsedDate.getTime())

        Utils.dbErrorHandling("adicionar candidato", {
            sql.execute(
                    "INSERT INTO candidatos (nome, cpf, email, descricao, data_nascimento, linkedin_link, senha) VALUES (?, ?, ?, ?, ?, ?, ?)",
                    [candidato.nome, candidato.cpf, candidato.email, candidato.descricao, sqlDate, candidato.linkedinLink, candidato.senha]
            )
            sql.execute(
                    "INSERT INTO enderecos_candidatos (id_candidato, cidade, estado, pais, cep) VALUES ((SELECT id FROM candidatos WHERE email = ?), ?, ?, ?, ?)",
                    [candidato.email, candidato.cidade, candidato.estado, candidato.pais, candidato.cep]
            )
        })
    }

    void updateCandidato(String campo, String novo, int idCandidato) {
        Utils.dbErrorHandling("atualizar candidato", {
            sql.execute("""
                UPDATE candidatos SET ${campo} = ? WHERE id = ?;
            """, [novo, idCandidato])
        })
    }

    void updateEnderecoCandidato(String campo, String novo, int idCandidato) {
        Utils.dbErrorHandling("atualizar endereco de candidato", {
            sql.execute("UPDATE enderecos_candidatos SET ${campo} = ? WHERE id_candidato = ?;", [novo, idCandidato])
        })
    }

    void deleteCandidato(int id) {
        Utils.dbErrorHandling("deletar candidato", {
            sql.execute("DELETE FROM candidatos WHERE id = ?;", [id])
        })
    }

    List<Candidato> candidatosData() {
        ArrayList<Candidato> candidatosData = new ArrayList<>()

        Utils.dbErrorHandling("retornar dados dos candidatos", {
            sql.eachRow("""
            SELECT c.id, c.nome, c.cpf, c.email, c.descricao, c.data_nascimento, c.linkedin_link, c.senha,
            en.cidade, en.estado, en.pais, en.cep
            FROM candidatos as c INNER JOIN enderecos_candidatos as en ON en.id_candidato = c.id;
        """) { row ->
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
        })
        return candidatosData
    }
}

