package model

import groovy.transform.ToString

class PessoaJuridica extends PessoaBase {
    String cnpj, pais

    @Override
    String toString() {
        return "${nome}, ${email}, ${pais}, ${estado}, ${descricao}, ${cnpj}"
    }
}
