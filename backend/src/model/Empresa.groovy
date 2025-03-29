package model

class Empresa extends PessoaBase {
    String cnpj

    @Override
    String toString() {
        return "${nome}, ${email}, ${descricao}, ${linkedinLink}, ${cnpj}, ${cidade}, ${estado}, ${pais}, ${cep}, ${senha}"
    }
}
