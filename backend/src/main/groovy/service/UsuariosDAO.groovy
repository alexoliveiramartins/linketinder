package service

import groovy.sql.Sql
import model.*

import java.text.SimpleDateFormat


class UsuariosDAO {
    Map dbConnParams = [
            'url'     : 'jdbc:postgresql://localhost:5432/linketinder-database',
            'user'    : 'postgres',
            'password': 'linketinder',
            'driver'  : 'org.postgresql.Driver'
    ]
    private sql

    // CRUD Curtidas

    void addCurtidaCandidato(int id_candidato, int id_vaga){
        sql = Sql.newInstance(dbConnParams)
        sql.execute('''INSERT INTO curtidas_candidato(id_candidato, id_vaga, data_curtida)
                            VALUES(?, ?, CURRENT_DATE);
                         ''', [id_candidato, id_vaga])
        sql.close()
    }

    void addCurtidaEmpresa(int id_empresa, int id_candidato){
        sql = Sql.newInstance(dbConnParams)
        sql.execute('''INSERT INTO curtidas_empresa(id_empresa, id_candidato, data_curtida)
                            VALUES(?, ?, CURRENT_DATE);
                         ''', [id_empresa, id_candidato])
        sql.close()
    }

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
        sql.eachRow("""
                        SELECT e.id, e.nome, e.cnpj, e.email, e.descricao, e.linkedin_link, e.senha,
                        en.cidade, en.estado, en.pais, en.cep
                        FROM empresas as e INNER JOIN enderecos_empresas as en ON en.id_empresa = e.id;
                        """) { row ->
            println row
        }
        sql.close()
    }

    void updateEmpresa(String campo, String novo, int idEmpresa) {
        sql = Sql.newInstance(dbConnParams)
        sql.execute("""
            UPDATE empresas SET ${campo} = ? WHERE id = ?;
        """, [novo, idEmpresa])
        sql.close()
    }

    void updateEnderecoEmpresa(String campo, String novo, int idEmpresa) {
        sql = Sql.newInstance(dbConnParams)
        sql.execute("UPDATE enderecos_empresas SET ${campo} = ? WHERE id_empresa = ?;", [novo, idEmpresa])
        sql.close()
    }

    void deleteEmpresa(int id) {
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

    // CRUD Vaga
    void addVaga(int id_empresa, String tituloVaga, String descricaoVaga) {
        sql = Sql.newInstance(dbConnParams)
        sql.execute(
                "INSERT INTO vagas (id_empresa, titulo, descricao) VALUES(?, ?, ?);",
                [id_empresa, tituloVaga, descricaoVaga]
        )
        sql.close()
    }

    void listVagasEmpresas() {
        sql = Sql.newInstance(dbConnParams)
        println "Vagas:"
        sql.eachRow("""
            SELECT empresas.nome, vagas.titulo, vagas.descricao FROM vagas
            INNER JOIN empresas ON vagas.id_empresa=empresas.id;
        """) {
            row -> println row
        }
    }

    void listVagas() {
        sql = Sql.newInstance(dbConnParams)
        println "Vagas:"
        sql.eachRow("""
            SELECT * FROM vagas;
        """) {
            row -> println row
        }
    }

    void updateVaga(String campo, String novo, int idVaga) {
        sql = Sql.newInstance(dbConnParams)
        sql.execute("""
            UPDATE vagas SET ${campo} = ? WHERE id = ?;
        """, [novo, idVaga])
        sql.close()
    }

    void deleteVaga(int id) {
        sql = Sql.newInstance(dbConnParams)
        sql.execute(
                "DELETE FROM vagas WHERE id = ?;",
                [id]
        )
        sql.close()
    }

    // CRUD competencia
    void addCompetenciaVaga(String nome, int id_vaga) {
        sql = Sql.newInstance(dbConnParams)
        def queryCount = sql.firstRow("SELECT Count(*) AS cnt FROM competencias WHERE nome = ?", [nome])
        int count = queryCount.cnt as int
        if (count == 0) {
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


    void addCompetenciaCandidato(String nome, int id_candidato) {
        sql = Sql.newInstance(dbConnParams)
        def queryCount = sql.firstRow("SELECT Count(*) AS cnt FROM competencias WHERE nome = ?", [nome])
        int count = queryCount.cnt as int
        if (count == 0) {
            sql.execute(
                    "INSERT INTO competencias(nome) VALUES (?);",
                    [nome]
            )
        }
        sql.execute(
                "INSERT INTO candidato_competencia(id_candidato, id_competencia) VALUES (?, (SELECT id FROM competencias WHERE nome = ?));",
                [id_candidato, nome]
        )
        sql.close()
    }

    void listCompetencias() {
        sql = Sql.newInstance(dbConnParams)

        sql.eachRow("""SELECT v.titulo AS vaga,
                        c.nome AS competencia
                        FROM vagas v
                        JOIN vaga_competencia vc
                          ON v.id = vc.id_vaga
                        JOIN competencias c
                          ON c.id = vc.id_competencia;""")
                { row -> println row }

        sql.close()
    }

    void updateCompetencia(int idCompetencia, String nomeSet) {
        sql = Sql.newInstance(dbConnParams)

        sql.execute("UPDATE competencias SET nome = ? WHERE id = ?;", [nomeSet, idCompetencia])

        sql.close()
    }

    void deleteCompetencia(int idCompetencia) {
        sql = Sql.newInstance(dbConnParams)

        sql.execute("DELETE FROM vaga_competencia WHERE id_competencia = ?;", [idCompetencia])
        sql.execute("DELETE FROM candidato_competencia WHERE id_competencia = ?;", [idCompetencia])
        sql.execute("DELETE FROM competencias WHERE id = ?;", [idCompetencia])

        sql.close()
    }

    void deleteCompetenciaVaga(int id_vaga, int idCompetencia) {
        sql = Sql.newInstance(dbConnParams)
        sql.execute("DELETE FROM vaga_competencia WHERE id_competencia = ? AND id_vaga = ?;", [idCompetencia, id_vaga])
        sql.close()
    }

    void deleteCompetenciaCandidato(int idCandidato, int idCompetencia) {
        sql = Sql.newInstance(dbConnParams)
        sql.execute("DELETE FROM candidato_competencia WHERE id_competencia = ? AND id_candidato = ?;", [idCompetencia, idCandidato])
        sql.close()
    }

    boolean competenciaExists(String nome) {
        sql = Sql.newInstance(dbConnParams)
        def result = sql.firstRow("SELECT COUNT(*) FROM competencias WHERE nome = ?;", [nome])
        return result && result.count > 0
    }

    List<Competencia> competenciaData(){
        sql = Sql.newInstance(dbConnParams)

        ArrayList<Competencia> competenciaData = new ArrayList<>()
        sql.eachRow("SELECT * FROM competencias;") {
            row -> {
                Competencia competencia = new Competencia()
                competencia.id = row.id
                competencia.nome = row.nome
                competenciaData.push(competencia)
            }
        }
        return competenciaData
        sql.close()
    }

    List<Empresa> empresasData() {
        sql = Sql.newInstance(dbConnParams)

        ArrayList<Empresa> empresasData = new ArrayList<>()

        sql.eachRow("""
            SELECT e.id, e.nome, e.cnpj, e.email, e.descricao, e.linkedin_link, e.senha,
            en.cidade, en.estado, en.pais, en.cep
            FROM empresas as e INNER JOIN enderecos_empresas as en ON en.id_empresa = e.id;
        """) {
            row -> {
                Empresa empresaItem = new Empresa()
                empresaItem.id = row.id
                empresaItem.nome = row.nome
                empresaItem.email = row.email
                empresaItem.descricao = row.descricao
                empresaItem.linkedinLink = row.linkedin_link
                empresaItem.cnpj = row.cnpj
                empresaItem.cidade = row.cidade
                empresaItem.estado = row.estado
                empresaItem.pais = row.pais
                empresaItem.cep = row.cep
                empresaItem.senha = row.senha

                empresasData.push(empresaItem)
            }
        }
        sql.close()
        return empresasData
    }

    List<Candidato> candidatosData() {
        sql = Sql.newInstance(dbConnParams)

        ArrayList<Candidato> candidatosData = new ArrayList<>()

        sql.eachRow("""
            SELECT c.id, c.nome, c.cpf, c.email, c.descricao, c.data_nascimento, c.linkedin_link, c.senha,
            en.cidade, en.estado, en.pais, en.cep
            FROM candidatos as c INNER JOIN enderecos_candidatos as en ON en.id_candidato = c.id;
        """) {
            row -> {
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

    List<CompetenciaCandidato> candidatoCompetenciasData(){
        sql = Sql.newInstance(dbConnParams)
        ArrayList<CompetenciaCandidato> candidatoCompetenciasData = new ArrayList<>()
        sql.eachRow("""
            SELECT c.id, c.nome, cand.id_candidato FROM competencias AS c 
            JOIN candidato_competencia AS cand ON cand.id_competencia = c.id;
        """) {
            row -> {
                CompetenciaCandidato competenciaCandidato = new CompetenciaCandidato()
                competenciaCandidato.id_candidato = row.id_candidato
                competenciaCandidato.id_competencia = row.id
                candidatoCompetenciasData.push(competenciaCandidato)
            }
        }
        sql.close()
        return candidatoCompetenciasData
    }

    List<CompetenciaVaga> vagaCompetenciasData(){
        sql = Sql.newInstance(dbConnParams)
        ArrayList<CompetenciaVaga> vagaCompetenciasData = new ArrayList<>()
        sql.eachRow("""
            SELECT c.id, c.nome, v.id_vaga FROM competencias AS c 
            JOIN vaga_competencia AS v ON v.id_competencia = c.id;
        """) {
            row -> {
                CompetenciaVaga competenciaVaga = new CompetenciaVaga()
                competenciaVaga.id_vaga = row.id_vaga
                competenciaVaga.id_competencia = row.id
                vagaCompetenciasData.push(competenciaVaga)
            }
        }
        sql.close()
        return vagaCompetenciasData
    }

    List<Vaga> vagasData() {
        sql = Sql.newInstance(dbConnParams)
        ArrayList<Vaga> vagasData = new ArrayList<>()
        sql.eachRow("""
            SELECT * FROM vagas;
        """) {
            row -> {
                Vaga vagaItem = new Vaga()
                vagaItem.id = row.id
                vagaItem.id_empresa = row.id_empresa
                vagaItem.titulo = row.titulo
                vagaItem.descricao = row.descricao

                vagasData.push(vagaItem)
            }
        }
        sql.close()
        return vagasData
    }

    List<CurtidaEmpresa> curtidasEmpresasData() {
        sql = Sql.newInstance(dbConnParams)
        ArrayList<CurtidaEmpresa> curtidasEmpresas = new ArrayList<>()
        sql.eachRow("""
            SELECT * FROM curtidas_empresa;
        """) {
            row -> {
                CurtidaEmpresa curtida = new CurtidaEmpresa()
                curtida.id_empresa = row.id_empresa
                curtida.id_candidato = row.id_candidato
                curtida.data = (row.data_curtida).toLocalDateTime()

                curtidasEmpresas.push(curtida)
            }
        }
        sql.close()
        return curtidasEmpresas
    }

    List<CurtidaCandidato> curtidasCandidatosData() {
        sql = Sql.newInstance(dbConnParams)

        ArrayList<CurtidaCandidato> curtidasCandidatos = new ArrayList<>()

        sql.eachRow("""
            SELECT * FROM curtidas_candidato;
        """) {
            row -> {
                CurtidaCandidato curtida = new CurtidaCandidato()
                curtida.id_candidato = row.id_candidato
                curtida.id_vaga = row.id_vaga
                curtida.data = (row.data_curtida).toLocalDateTime()

                curtidasCandidatos.push(curtida)
            }
        }
        sql.close()
        return curtidasCandidatos
    }

}
