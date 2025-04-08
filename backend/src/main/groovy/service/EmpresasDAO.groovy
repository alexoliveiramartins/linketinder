package service

import groovy.sql.Sql
import model.Empresa

class EmpresasDAO {
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

    List<Empresa> empresasData() {
        sql = Sql.newInstance(dbConnParams)

        ArrayList<Empresa> empresasData = new ArrayList<>()

        sql.eachRow("""
            SELECT e.id, e.nome, e.cnpj, e.email, e.descricao, e.linkedin_link, e.senha,
            en.cidade, en.estado, en.pais, en.cep
            FROM empresas as e INNER JOIN enderecos_empresas as en ON en.id_empresa = e.id;
        """) {
            row ->
                {
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
}
