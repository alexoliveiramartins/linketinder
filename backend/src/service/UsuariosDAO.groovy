package service

import groovy.sql.Sql
import model.Candidato
import model.Empresa

import java.text.SimpleDateFormat

class UsuariosDAO {
    Map dbConnParams = [
            'url'     : 'jdbc:postgresql://localhost:5432/linketinder-database',
            'user'    : 'postgres',
            'password': 'linketinder',
            'driver'  : 'org.postgresql.Driver'
    ]
    private sql

    void addEmpresa(Empresa empresa) {
        sql = Sql.newInstance(dbConnParams)
        sql.execute(
                "INSERT INTO empresas (nome, cnpj, email, descricao, linkedin_link, senha) VALUES (?, ?, ?, ?, ?, ?)",
                [empresa.nome, empresa.cnpj, empresa.email, empresa.descricao, empresa.linkedinLink, empresa.senha]
        )
        sql.execute(
                "INSERT INTO enderecos_empresas (id_empresa, cidade, estado, pais, cep) VALUES ((SELECT id FROM empresas WHERE email = ?), ?, ?, ?, ?)",
                [empresa.email, empresa.cidade, empresa.estado, empresa.pais, empresa.cep]
        )
        sql.close()
    }

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

    void addVaga(Empresa empresa, String tituloVaga, String descricaoVaga) {
        println "Email: ${empresa.email}"
        sql = Sql.newInstance(dbConnParams)
        sql.execute(
                "INSERT INTO vagas (id_empresa, titulo, descricao) VALUES((SELECT id FROM empresas WHERE email = ?), ?, ?);",
                [empresa.email, tituloVaga, descricaoVaga]
        )
        sql.close()
    }

    // listagem

    void listCandidatos(){
        sql = Sql.newInstance(dbConnParams)
        println "Candidatos:"
        sql.eachRow("SELECT * FROM candidatos") { row ->
            println row
        }
    }

    void listEmpresas() {
        sql = Sql.newInstance(dbConnParams)
        println "Empresas:"
        sql.eachRow("SELECT * FROM empresas") { row ->
            println row
        }
    }

    void listVagas(){
        sql = Sql.newInstance(dbConnParams)
        println "Vagas:"
        sql.eachRow("""
            SELECT empresas.nome, vagas.titulo, vagas.descricao FROM vagas
            INNER JOIN empresas ON vagas.id_empresa=empresas.id;
        """) {
            row -> println row
        }
    }
}
