package service

import groovy.sql.Sql
import model.CurtidaCandidato
import model.CurtidaEmpresa
import utils.Utils

class CurtidasDAO {
    final Sql sql

    CurtidasDAO(Sql sql){
        this.sql = sql
    }

    void addCurtidaCandidato(int id_candidato, int id_vaga) {
        Utils.dbErrorHandling("adicionar curtida candidato", {
            sql.execute('''INSERT INTO curtidas_candidato(id_candidato, id_vaga, data_curtida)
                            VALUES(?, ?, CURRENT_DATE);
                         ''', [id_candidato, id_vaga])
        })
    }

    void addCurtidaEmpresa(int id_empresa, int id_candidato) {
        Utils.dbErrorHandling("adicionar curtida empresa", {
            sql.execute('''INSERT INTO curtidas_empresa(id_empresa, id_candidato, data_curtida)
                            VALUES(?, ?, CURRENT_DATE);
                         ''', [id_empresa, id_candidato])
        })
    }

    void mostrarCurtidasCandidato() {
        Utils.dbErrorHandling("mostrar curtidas candidato", {
            sql.eachRow('''SELECT c.nome, v.titulo, v.id_empresa FROM candidatos AS c
                            JOIN curtidas_candidato AS curt ON curt.id_candidato = c.id
                            JOIN vagas AS v ON curt.id_vaga = v.id;
                         ''')
                    { row ->
                        println "$row.nome curtiu \"$row.titulo\" (Empresa $row.id_empresa)"
                    }
        })
    }

    void mostrarCurtidasEmpresa() {
        Utils.dbErrorHandling("mostrar curtidas empresa", {
            sql.eachRow('''SELECT e.nome as nome_empresa, c.nome as nome_candidato 
                            FROM candidatos AS c
                            JOIN curtidas_empresa as curt ON curt.id_candidato = c.id 
                            JOIN empresas AS e ON curt.id_empresa = e.id;
                         ''')
                    { row ->
                        println "$row.nome_empresa curtiu \"$row.nome_candidato\""
                    }
        })
    }

    List<CurtidaEmpresa> curtidasEmpresasData() {
        ArrayList<CurtidaEmpresa> curtidasEmpresas = new ArrayList<>()
        Utils.dbErrorHandling("retornar curtidas empresas", {
            sql.eachRow("""
            SELECT * FROM curtidas_empresa;
        """) {
                row ->
                    {
                        CurtidaEmpresa curtida = new CurtidaEmpresa()
                        curtida.id_empresa = row.id_empresa
                        curtida.id_candidato = row.id_candidato
                        curtida.data = (row.data_curtida).toLocalDateTime()
                        curtidasEmpresas.push(curtida)
                    }
            }
        })
        return curtidasEmpresas
    }

    List<CurtidaCandidato> curtidasCandidatosData() {
        ArrayList<CurtidaCandidato> curtidasCandidatos = new ArrayList<>()
        Utils.dbErrorHandling("retornar curtidas candidatos", {
            sql.eachRow("""
            SELECT * FROM curtidas_candidato;
        """) {
                row ->
                    {
                        CurtidaCandidato curtida = new CurtidaCandidato()
                        curtida.id_candidato = row.id_candidato
                        curtida.id_vaga = row.id_vaga
                        curtida.data = (row.data_curtida).toLocalDateTime()

                        curtidasCandidatos.push(curtida)
                    }
            }
        })
        return curtidasCandidatos
    }

}
