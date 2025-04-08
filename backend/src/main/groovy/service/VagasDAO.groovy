package service

import groovy.sql.Sql
import model.Vaga

class VagasDAO {
    Map dbConnParams = [
            'url'     : 'jdbc:postgresql://localhost:5432/linketinder-database',
            'user'    : 'postgres',
            'password': 'linketinder',
            'driver'  : 'org.postgresql.Driver'
    ]
    private sql

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

    List<Vaga> vagasData() {
        sql = Sql.newInstance(dbConnParams)
        ArrayList<Vaga> vagasData = new ArrayList<>()
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
        sql.close()
        return vagasData
    }
}
