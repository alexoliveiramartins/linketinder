package service

import groovy.sql.Sql
import model.Competencia
import model.CompetenciaCandidato
import model.CompetenciaVaga

class CompetenciasDAO {
    Map dbConnParams = [
            'url'     : 'jdbc:postgresql://localhost:5432/linketinder-database',
            'user'    : 'postgres',
            'password': 'linketinder',
            'driver'  : 'org.postgresql.Driver'
    ]
    private sql

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

    List<Competencia> competenciaData() {
        sql = Sql.newInstance(dbConnParams)

        ArrayList<Competencia> competenciaData = new ArrayList<>()
        sql.eachRow("SELECT * FROM competencias;") {
            row ->
                {
                    Competencia competencia = new Competencia()
                    competencia.id = row.id
                    competencia.nome = row.nome
                    competenciaData.push(competencia)
                }
        }
        sql.close()
        return competenciaData
    }

    List<CompetenciaCandidato> candidatoCompetenciasData() {
        sql = Sql.newInstance(dbConnParams)
        ArrayList<CompetenciaCandidato> candidatoCompetenciasData = new ArrayList<>()
        sql.eachRow("""
            SELECT c.id, c.nome, cand.id_candidato FROM competencias AS c 
            JOIN candidato_competencia AS cand ON cand.id_competencia = c.id;
        """) {
            row ->
                {
                    CompetenciaCandidato competenciaCandidato = new CompetenciaCandidato()
                    competenciaCandidato.id_candidato = row.id_candidato
                    competenciaCandidato.id_competencia = row.id
                    candidatoCompetenciasData.push(competenciaCandidato)
                }
        }
        sql.close()
        return candidatoCompetenciasData
    }

    List<CompetenciaVaga> vagaCompetenciasData() {
        sql = Sql.newInstance(dbConnParams)
        ArrayList<CompetenciaVaga> vagaCompetenciasData = new ArrayList<>()
        sql.eachRow("""
            SELECT c.id, c.nome, v.id_vaga FROM competencias AS c 
            JOIN vaga_competencia AS v ON v.id_competencia = c.id;
        """) {
            row ->
                {
                    CompetenciaVaga competenciaVaga = new CompetenciaVaga()
                    competenciaVaga.id_vaga = row.id_vaga
                    competenciaVaga.id_competencia = row.id
                    vagaCompetenciasData.push(competenciaVaga)
                }
        }
        sql.close()
        return vagaCompetenciasData
    }
}
