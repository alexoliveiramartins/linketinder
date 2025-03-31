package model

class Candidato extends PessoaBase {
    String cpf, dataNascimento
    ArrayList<Integer> idCurtidasCandidato

    @Override
    String toString() {
        return "${id}, ${nome}, ${cpf}, ${email}, ${descricao}, ${linkedinLink}, ${dataNascimento}, ${cidade}, ${estado}, ${pais}, ${cep}, ${senha}"
    }
}
