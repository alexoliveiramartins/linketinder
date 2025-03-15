export class Empresa {
    nome: string
    email: string
    estado: string
    descricao: string
    cep: string
    cnpj: string
    pais: string
    competencias: string[]

    constructor(nome: string,
        email: string,
        estado: string,
        descricao: string,
        cep: string,
        cnpj: string,
        pais: string,
        competencias: string[]) {
        this.nome = nome
        this.email = email
        this.estado = estado
        this.descricao = descricao
        this.cep = cep
        this.cnpj = cnpj
        this.pais = pais
        this.competencias = competencias
    }
}

export class Usuario {
    nome: string
    email: string
    estado: string
    descricao: string
    cep: string
    cpf: string
    idade: number
    competencias: string[]

    constructor(nome: string,
        email: string,
        estado: string,
        descricao: string,
        cep: string,
        cpf: string,
        idade: number,
        competencias: string[]) {
        this.nome = nome
        this.email = email
        this.estado = estado
        this.descricao = descricao
        this.cep = cep
        this.cpf = cpf
        this.idade = idade
        this.competencias = competencias
    }
}