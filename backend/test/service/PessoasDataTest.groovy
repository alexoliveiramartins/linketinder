package service

import model.Candidato
import model.Empresa
import spock.lang.Specification

class PessoasDataTest extends Specification {
    def data = new PessoasData()

    def "Adicionar Candidato adiciona corretamente"() {
        given: "O tamanho da lista"
        def size = data.candidatos.size()

        and: "O candidato a ser adicionado"
        def Sanji = new Candidato(nome: 'Sanji', email: 'all.blue@email.com', estado: 'Baratie', descricao: 'Cozinheiro de Software', cep: '29010-456', cpf: '654.321.987-33', dataNascimento: '04-03-2005', competencias: ['C#', 'Cloud', 'Vim btw'])

        when: "Adiciona candidato Sanji"
        data.addCandidato(Sanji)

        then: "A lista de candidatos aumenta em 1"
        data.candidatos.size() == size + 1

        and: "O candidato adicionado é o correto"
        data.candidatos.last() == Sanji
    }

    def "AddEmpresa"() {
        given: "O tamanho da lista"
        def size = data.empresas.size()

        and: "O candidato a ser adicionado"
        def phantomTroupe = new Empresa(nome: 'Genei Ryodan', email: 'Genei@ryodan.com', estado: 'Washington', descricao: 'Phantom Troupe', cep: '98109-5210', cnpj: '56.789.012/0001-78', pais: 'EUA', competencias: ['AWS', 'E-commerce', 'Cloud Computing'])

        when: "Adiciona candidato Sanji"
        data.addEmpresa(phantomTroupe)

        then: "A lista de candidatos aumenta em 1"
        data.empresas.size() == size + 1

        and: "O candidato adicionado é o correto"
        data.empresas.last() == phantomTroupe
    }
}
