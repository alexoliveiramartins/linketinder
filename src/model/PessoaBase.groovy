package model

import groovy.transform.ToString

abstract class PessoaBase implements Pessoa {
    String nome, email, estado, descricao, cep
    List<String> competencias
}
