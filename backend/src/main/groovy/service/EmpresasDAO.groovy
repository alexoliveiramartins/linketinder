package service

import groovy.sql.Sql
import jdk.jshell.execution.Util
import model.Empresa
import utils.Utils

class EmpresasDAO {
    final Sql sql

    EmpresasDAO(Sql sql){
        this.sql = sql
    }

    void addEmpresa(Empresa empresa) {
        Utils.dbErrorHandling("adicionar empresa", {
            sql.execute(
                    "INSERT INTO empresas (nome, cnpj, email, descricao, linkedin_link, senha) VALUES (?, ?, ?, ?, ?, ?)",
                    [empresa.nome, empresa.cnpj, empresa.email, empresa.descricao, empresa.linkedinLink, empresa.senha]
            )
            sql.execute(
                    "INSERT INTO enderecos_empresas (id_empresa, cidade, estado, pais, cep) VALUES ((SELECT id FROM empresas WHERE email = ?), ?, ?, ?, ?)",
                    [empresa.email, empresa.cidade, empresa.estado, empresa.pais, empresa.cep]
            )
        })
    }

    void listEmpresas() {
        println "Empresas:"
        Utils.dbErrorHandling("listar empresas", {
            sql.eachRow("""
                        SELECT e.id, e.nome, e.cnpj, e.email, e.descricao, e.linkedin_link, e.senha,
                        en.cidade, en.estado, en.pais, en.cep
                        FROM empresas as e INNER JOIN enderecos_empresas as en ON en.id_empresa = e.id;
                        """) { row ->
                println row
            }
        })
    }

    void updateEmpresa(String campo, String novo, int idEmpresa) {
        Utils.dbErrorHandling("atualizar empresa", {
            sql.execute("""
            UPDATE empresas SET ${campo} = ? WHERE id = ?;
        """, [novo, idEmpresa])
        })
    }

    void updateEnderecoEmpresa(String campo, String novo, int idEmpresa) {
        Utils.dbErrorHandling("atualizar endereco de empresa", {
            sql.execute("UPDATE enderecos_empresas SET ${campo} = ? WHERE id_empresa = ?;", [novo, idEmpresa])
        })
    }

    void deleteEmpresa(int id) {
        Utils.dbErrorHandling("deletar empresa", {
            sql.execute("DELETE FROM empresas WHERE id = ?;", [id])
        })
    }

    List<Empresa> empresasData() {
        ArrayList<Empresa> empresasData = new ArrayList<>()
        Utils.dbErrorHandling("retornar dados de empresa", {
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
        })
        return empresasData
    }
}
