package model

class Candidato extends PessoaBase {
    String cpf, dataNascimento

    @Override
    String toString() {
        return "${nome}, ${cpf}, ${email}, ${descricao}, ${linkedinLink}, ${dataNascimento}, ${cidade}, ${estado}, ${pais}, ${cep}, ${senha}"
    }
}
