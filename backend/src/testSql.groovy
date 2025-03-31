import service.PessoasData
import service.UsuariosDAO

UsuariosDAO usuariosDAO = new UsuariosDAO()

PessoasData pessoasData = new PessoasData()

pessoasData.empresas.each {it -> println it}
pessoasData.candidatos.each {it -> println it}
pessoasData.vagas.each {it -> println it}
