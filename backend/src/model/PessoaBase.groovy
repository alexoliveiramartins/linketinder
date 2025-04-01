package model

import groovy.transform.ToString

abstract class PessoaBase{
    int id
    String nome, email, descricao, linkedinLink, cidade, estado, pais, cep, senha
    ArrayList<Integer> curtidas = new ArrayList<>()
}
