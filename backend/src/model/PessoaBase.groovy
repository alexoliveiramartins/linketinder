package model

import groovy.transform.ToString

abstract class PessoaBase implements Pessoa {
    String nome, email, descricao, linkedinLink, cidade, estado, pais, cep, senha
    List<String> competencias
}
