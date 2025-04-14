package service

import groovy.sql.Sql
import model.Empresa
import spock.lang.Specification

class EmpresasDAOTest extends Specification {
    def sqlMock
    def empresasDAO

    def setup(){
        sqlMock = Mock(Sql)
        empresasDAO = new EmpresasDAO(sqlMock)
    }


    def "AddEmpresa"() {
        given: "cria uma empresa"
        Empresa empresa = new Empresa(
                nome: 'Havan',
                cnpj: '74210-01/0001-2',
                email: 'havan@veiodahavan.com',
                descricao: 'havante',
                linkedinLink: 'havan.linkedin.com.br',
                senha: '123havan',
                cidade: 'Florianopolis',
                estado: 'SC',
                pais: 'BR',
                cep: '74210069'
        )

        when: "adiciona empresa"
        empresasDAO.addEmpresa(empresa)

        then: "executa um INSERT na tabela de empresas"
        1 * sqlMock.execute({String query -> query.contains("INSERT INTO empresas")}, _)

        and: "executa um INSERT na tabela de enderecos de empresas"
        1 * sqlMock.execute({String query -> query.contains("INSERT INTO enderecos_empresas")}, _)
    }

    def "ListEmpresas"() {
        given:

        when: "lista as empresas"
        empresasDAO.listEmpresas()

        then: "executa um SELECT no bd"
        1 * sqlMock.eachRow({String query -> query.contains("SELECT") && query.contains("FROM empresas") && query.contains("enderecos_empresas")}, _)
    }

    def "UpdateEmpresa"() {
        given: "dados valores de campo a ser mudado, valor novo e id da empresa"
        String campo = "nome"
        String novo = "Kabum"
        int idEmpresa = 1

        when: "Atualiza a empresa selecionada no campo selecionado com valor novo"
        empresasDAO.updateEmpresa(campo, novo, idEmpresa)

        then: "executa um UPDATE no campo"
        1 * sqlMock.execute({String query -> query.contains("UPDATE empresas") && query.contains("SET")}, _)
    }

    def "UpdateEnderecoEmpresa"() {
        given: "dados valores de campo a ser mudado, valor novo e id do endereco da empresa"
        String campo = "cidade"
        String novo = "Kabum"
        int idEmpresa = 1

        when: "Atualiza o endereco da empresa selecionada no campo selecionado com valor novo"
        empresasDAO.updateEnderecoEmpresa(campo, novo, idEmpresa)

        then: "executa um UPDATE no campo"
        1 * sqlMock.execute({String query -> query.contains("UPDATE enderecos_empresas") && query.contains("WHERE")}, _)
    }

    def "DeleteEmpresa"() {
        given: "dado id da empresa para deletar"
        int id = 1

        when: "deleta a empresa"
        empresasDAO.deleteEmpresa(id)

        then: "executa um DELETE da empresa com id selecionado"
        1 * sqlMock.execute({String query -> query.contains("DELETE") && query.contains("WHERE")}, _)
    }

    def "EmpresasData"() {
        given: "cria uma lista de Empresas"
        ArrayList<Empresa> empresasData = new ArrayList<>()

        when: "coleta os dados de empresas"
        empresasData = empresasDAO.empresasData()

        then: "executa um SELECT das empresas"
        1 * sqlMock.eachRow({String query -> query.contains("SELECT") && query.contains("FROM empresas")}, _)
    }
}
