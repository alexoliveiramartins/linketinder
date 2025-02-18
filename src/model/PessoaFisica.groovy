package model

import groovy.transform.ToString

class PessoaFisica extends PessoaBase {
    String cpf
    int idade

    @Override
    String toString() {
        return "${nome}, ${email}, ${estado}, ${descricao}, ${cep}, ${idade}"
    }
}
