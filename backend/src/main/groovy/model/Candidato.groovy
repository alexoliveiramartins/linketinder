package model

class Candidato extends PessoaBase {
    String cpf, dataNascimento
    List<Competencia> competencias = new ArrayList<>()

    @Override
    String toString() {
        return "${id}, ${nome}, ${cpf}, ${email}, ${descricao}, ${linkedinLink}, ${dataNascimento}, ${cidade}, ${estado}, ${pais}, ${cep}, ${senha}, ${curtidas}" +
                "Competencias: $competencias"
    }
}
