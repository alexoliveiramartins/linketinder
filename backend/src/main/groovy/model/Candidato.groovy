package model

class Candidato extends PessoaBase {
    String cpf, dataNascimento
    List<Competencia> competencias = new ArrayList<>()

    Candidato() { }

    // Construtor JSON
    Candidato(Object jsonObject){
        this.nome = jsonObject.nome
        this.cpf = jsonObject.cpf
        this.email = jsonObject.email
        this.descricao = jsonObject.descricao
        this.linkedinLink = jsonObject.linkedinLink
        this.dataNascimento = jsonObject.dataNascimento
        this.cidade = jsonObject.cidade
        this.estado = jsonObject.estado
        this.pais = jsonObject.pais
        this.cep = jsonObject.cep
        this.senha = jsonObject.senha
        this.competencias = jsonObject.competencias
    }

    @Override
    String toString() {
        return "${id}, ${nome}, ${cpf}, ${email}, ${descricao}, ${linkedinLink}, ${dataNascimento}, ${cidade}, ${estado}, ${pais}, ${cep}, ${senha}" +
                "Competencias: $competencias"
    }
}
