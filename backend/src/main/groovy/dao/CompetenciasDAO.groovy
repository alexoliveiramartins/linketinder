package dao

import groovy.sql.Sql
import model.Competencia
import utils.Utils

class CompetenciasDAO implements IDao<Competencia> {
    private final Sql sql

    CompetenciasDAO(Sql sql) {
        this.sql = sql
    }

    void add(Competencia competencia) {
        sql.execute("INSERT INTO competencias (nome) VALUES (?);", [competencia.nome])
    }

    void addCompetenciaVaga(String nome, int id_vaga) {
        Utils.dbErrorHandling("adicionar competencia a vaga", {
            def row = sql.firstRow("SELECT id FROM competencias WHERE nome = ?", [nome])
            if (row == null) {
                sql.execute("INSERT INTO competencias(nome) VALUES (?)", [nome])
                row = sql.firstRow("SELECT id FROM competencias WHERE nome = ?", [nome])
            }
            def idCompetencia = row.id
            sql.execute("INSERT INTO vaga_competencia(id_vaga, id_competencia) VALUES (?, ?)", [id_vaga, idCompetencia])
        })
    }

    void addCompetenciaCandidato(String nome, int id_candidato) {
        Utils.dbErrorHandling("Adicionar competencia a candidato", {
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
        })
    }

    void delete(int idCompetencia) {
        Utils.dbErrorHandling("deletar competencia", {
            sql.execute("DELETE FROM competencias WHERE id = ?;", [idCompetencia])
        })
    }

    void update(String campo, String nomeSet, int idCompetencia) {
        Utils.dbErrorHandling("atualizar competencia", {
            sql.execute("UPDATE competencias SET nome = ? WHERE id = ?;", [nomeSet, idCompetencia])
        })
    }

    void deleteCompetenciaVaga(int id_vaga, int idCompetencia) {
        Utils.dbErrorHandling("deletar competencia de vaga", {
            sql.execute("DELETE FROM vaga_competencia WHERE id_competencia = ? AND id_vaga = ?;", [idCompetencia, id_vaga])
        })
    }

    void deleteCompetenciaCandidato(int idCandidato, int idCompetencia) {
        Utils.dbErrorHandling("deletar competencia candidato", {
            sql.execute("DELETE FROM candidato_competencia WHERE id_competencia = ? AND id_candidato = ?;", [idCompetencia, idCandidato])
        })
    }

    Competencia get(int id){
        Competencia competencia = new Competencia()
        sql.eachRow("SELECT * FROM competencias WHERE id = ?", [id]) { row ->
            competencia.id = row.id
            competencia.nome = row.nome
        }
        return competencia
    }

    List<Competencia> competenciaData() {
        ArrayList<Competencia> competenciaData = new ArrayList<>()
        Utils.dbErrorHandling("retornar dados de competencias", {
            sql.eachRow("SELECT * FROM competencias;") {
                row ->
                    {
                        Competencia competencia = new Competencia()
                        competencia.id = row.id
                        competencia.nome = row.nome
                        competenciaData.push(competencia)
                    }
            }
        })
        return competenciaData
    }
}
