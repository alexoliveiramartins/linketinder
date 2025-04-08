package model

class Empresa extends PessoaBase {
    String cnpj

    @Override
    String toString() {
        return "${id}, ${nome}, ${email}, ${descricao}, ${linkedinLink}, ${cnpj}, ${cidade}, ${estado}, ${pais}, ${cep}, ${senha}"
    }
}
