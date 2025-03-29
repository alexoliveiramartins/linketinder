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

    // CRUD Empresa
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

    void listEmpresas() {
        sql = Sql.newInstance(dbConnParams)
        println "Empresas:"
        sql.eachRow("SELECT * FROM empresas") { row ->
            println row
        }
        sql.close()
    }

    void deleteEmpresa(int id){
        sql = Sql.newInstance(dbConnParams)
        sql.execute("DELETE FROM enderecos_empresas WHERE id_empresa = ?;", [id])
        sql.execute("DELETE FROM empresas WHERE id = ?;", [id])
        sql.close()
    }

    // CRUD Candidato
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

    void listCandidatos(){
        sql = Sql.newInstance(dbConnParams)
        println "Candidatos:"
        sql.eachRow("SELECT * FROM candidatos") { row ->
            println row
        }
        sql.close()
    }

    void deleteCandidato(int id){
        sql = Sql.newInstance(dbConnParams)
        sql.execute("DELETE FROM enderecos_candidatos WHERE id_candidato = ?;", [id])
        sql.execute("DELETE FROM candidatos WHERE id = ?;", [id])
        sql.close()
    }

    // CRUD Vaga
    void addVaga(Empresa empresa, String tituloVaga, String descricaoVaga) {
        println "Email: ${empresa.email}"
        sql = Sql.newInstance(dbConnParams)
        sql.execute(
                "INSERT INTO vagas (id_empresa, titulo, descricao) VALUES((SELECT id FROM empresas WHERE email = ?), ?, ?);",
                [empresa.email, tituloVaga, descricaoVaga]
        )
        sql.close()
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

    void deleteVaga(int id){
        sql = Sql.newInstance(dbConnParams)
        sql.execute(
                "DELETE FROM vagas WHERE id = ?;",
                [id]
        )
        sql.close()
    }

    // CRUD competencia
    void addCompetenciaVaga(String nome, int id_vaga){
        sql = Sql.newInstance(dbConnParams)
        if(!competenciaExists(nome)) {
            sql.execute(
                    "INSERT INTO competencias(nome) VALUES (?);",
                    [nome]
            )
        }
        sql.execute(
                "INSERT INTO vaga_competencia(id_vaga, id_competencia) VALUES (?, (SELECT id FROM competencias WHERE nome = ?));",
                [id_vaga, nome]
        )
        sql.close()
    }
    void listCompetencias(){
        sql = Sql.newInstance(dbConnParams)
        sql.eachRow("""SELECT v.titulo AS vaga,
                        c.nome AS competencia
                        FROM vagas v
                        JOIN vaga_competencia vc
                          ON v.id = vc.id_vaga
                        JOIN competencias c
                          ON c.id = vc.id_competencia;""")
                {row -> println row}
        sql.close()
    }

    boolean competenciaExists(String nome){
        sql = Sql.newInstance(dbConnParams)
        def result = sql.firstRow("SELECT COUNT(*) FROM competencias WHERE nome = ?;", [nome])
//        sql.close();

        return result && result.count > 0
    }
    void deleteCompetencia(String nome){
        sql = Sql.newInstance(dbConnParams)

        sql.execute('''
        DELETE FROM vaga_competencia
        WHERE vaga_competencia.id_competencia = (SELECT id FROM competencias WHERE nome = ?);
        ''', [nome])
        sql.execute('''
        DELETE FROM competencias WHERE nome = ?;
        ''', [nome])
        sql.close()
    }
}
