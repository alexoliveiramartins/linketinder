package model

import groovy.transform.ToString

abstract class PessoaBase{
    String nome, email, descricao, linkedinLink, cidade, estado, pais, cep, senha
    List<String> competencias
}
