package dao

import groovy.sql.Sql
import model.Vaga
import utils.Utils

class VagasDAO implements IDao<Vaga>{
    private final Sql sql

    VagasDAO(Sql sql) {
        this.sql = sql
    }

    Vaga get(int id) {
        Vaga vaga
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

    void add(Vaga vaga) {
        Utils.dbErrorHandling("adicionar vaga", {
            sql.execute(
                    "INSERT INTO vagas (id_empresa, titulo, descricao) VALUES(?, ?, ?);",
                    [vaga.id_empresa, vaga.titulo, vaga.descricao]
            )
        })
    }

    void update(String campo, String novo, int idVaga) {
        Utils.dbErrorHandling("atualizar vaga", {
            sql.execute("""
            UPDATE vagas SET ${campo} = ? WHERE id = ?;
        """, [novo, idVaga])
        })
    }

    void delete(int id) {
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
                        Vaga vagaItem = new Vaga(row.id, row.id_empresa, row.titulo, row.descricao)

                        vagasData.push(vagaItem)
                    }
            }
        })
        return vagasData
    }
}
