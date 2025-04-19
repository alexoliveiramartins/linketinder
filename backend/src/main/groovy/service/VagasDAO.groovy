package service

import groovy.sql.Sql
import model.Vaga
import utils.Utils

class VagasDAO {
    final Sql sql

    VagasDAO(Sql sql) {
        this.sql = sql
    }

    Vaga getVagaById(int id) {
        def vaga = new Vaga()
        Utils.dbErrorHandling("retornar vaga por id", {
            sql.eachRow(
                    "SELECT * FROM vagas WHERE id = ?",
                    [id]
            ) { vagaResult ->
                vaga.id = vagaResult.id
                vaga.id_empresa = vagaResult.id_empresa
                vaga.titulo = vagaResult.titulo
                vaga.descricao = vagaResult.descricao
            }
        })
        return vaga
    }

    void addVaga(int id_empresa, String tituloVaga, String descricaoVaga) {
        Utils.dbErrorHandling("adicionar vaga", {
            sql.execute(
                    "INSERT INTO vagas (id_empresa, titulo, descricao) VALUES(?, ?, ?);",
                    [id_empresa, tituloVaga, descricaoVaga]
            )
        })
    }

    void updateVaga(String campo, String novo, int idVaga) {
        Utils.dbErrorHandling("atualizar vaga", {
            sql.execute("""
            UPDATE vagas SET ${campo} = ? WHERE id = ?;
        """, [novo, idVaga])
        })
    }

    void deleteVaga(int id) {
        Utils.dbErrorHandling("deletar vaga", {
            sql.execute(
                    "DELETE FROM vagas WHERE id = ?;",
                    [id]
            )
        })
    }

    List<Vaga> vagasData() {
        ArrayList<Vaga> vagasData = new ArrayList<>()
        Utils.dbErrorHandling("retornar dados de vagas", {
            sql.eachRow("""
            SELECT * FROM vagas;
        """) {
                row ->
                    {
                        Vaga vagaItem = new Vaga()
                        vagaItem.id = row.id
                        vagaItem.id_empresa = row.id_empresa
                        vagaItem.titulo = row.titulo
                        vagaItem.descricao = row.descricao

                        vagasData.push(vagaItem)
                    }
            }
        })
        return vagasData
    }
}
