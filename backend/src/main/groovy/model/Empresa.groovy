package model

class Empresa extends PessoaBase {
    String cnpj

    Empresa(){}

    Empresa(Object jsonObject){
        this.nome = jsonObject.nome
        this.email = jsonObject.email
        this.descricao = jsonObject.descricao
        this.linkedinLink = jsonObject.linkedinLink
        this.cnpj = jsonObject.cnpj
        this.cidade = jsonObject.cidade
        this.estado = jsonObject.estado
        this.pais = jsonObject.pais
        this.cep = jsonObject.cep
        this.senha = jsonObject.senha
    }

    @Override
    String toString() {
        return "${id}, ${nome}, ${email}, ${descricao}, ${linkedinLink}, ${cnpj}, ${cidade}, ${estado}, ${pais}, ${cep}, ${senha}"
    }
}
