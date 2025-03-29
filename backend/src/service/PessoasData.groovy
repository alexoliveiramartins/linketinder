package service

import model.Candidato
import model.Empresa

class PessoasData {
    List<Empresa> empresas = [
            new Empresa(nome: 'Google', email: 'contato@google.com', descricao: 'Tecnologia e inovação', linkedinLink: 'https://www.linkedin.com/company/google', cnpj: '12.345.678/0001-90', cidade: 'Mountain View', estado: 'California', pais: 'EUA', cep: '94043-1351', senha: 'Google@123'),
            new Empresa(nome: 'Apple', email: 'contato@apple.com', descricao: 'Eletrônicos e software', linkedinLink: 'https://www.linkedin.com/company/apple', cnpj: '23.456.789/0001-45', cidade: 'Cupertino', estado: 'California', pais: 'EUA', cep: '95014-2083', senha: 'Apple@123'),
            new Empresa(nome: 'Tesla', email: 'contato@tesla.com', descricao: 'Veículos elétricos e energia', linkedinLink: 'https://www.linkedin.com/company/tesla-motors', cnpj: '34.567.890/0001-56', cidade: 'Palo Alto', estado: 'California', pais: 'EUA', cep: '94304-1188', senha: 'Tesla@123'),
            new Empresa(nome: 'Facebook', email: 'contato@facebook.com', descricao: 'Rede social e inovação digital', linkedinLink: 'https://www.linkedin.com/company/meta', cnpj: '45.678.901/0001-67', cidade: 'Menlo Park', estado: 'California', pais: 'EUA', cep: '94025-2412', senha: 'Facebook@123'),
            new Empresa(nome: 'Amazon', email: 'contato@amazon.com', descricao: 'E-commerce e tecnologia', linkedinLink: 'https://www.linkedin.com/company/amazon', cnpj: '56.789.012/0001-78', cidade: 'Seattle', estado: 'Washington', pais: 'EUA', cep: '98109-5210', senha: 'Amazon@123')
    ]

    List<Candidato> candidatos = [
            new Candidato(nome: 'Alex Silva', cpf: '123.456.789-10', email: 'alex.silva@email.com', descricao: 'Desenvolvedor Web', linkedinLink: 'https://www.linkedin.com/in/alexsilva', dataNascimento: '10/02/2005', cidade: 'São Paulo', estado: 'São Paulo', pais: 'Brasil', cep: '01234-567', senha: 'Alex@123'),
            new Candidato(nome: 'Maria Oliveira', cpf: '987.654.321-00', email: 'maria.oliveira@email.com', descricao: 'Analista de Sistemas', linkedinLink: 'https://www.linkedin.com/in/mariaoliveira', dataNascimento: '10/02/2005', cidade: 'Belo Horizonte', estado: 'Minas Gerais', pais: 'Brasil', cep: '30123-456', senha: 'Maria@123'),
            new Candidato(nome: 'Carlos Pereira', cpf: '456.789.123-11', email: 'carlos.pereira@email.com', descricao: 'Designer UX/UI', linkedinLink: 'https://www.linkedin.com/in/carlospereira', dataNascimento: '20/01/2009', cidade: 'Rio de Janeiro', estado: 'Rio de Janeiro', pais: 'Brasil', cep: '23345-678', senha: 'Carlos@123'),
            new Candidato(nome: 'Fernanda Souza', cpf: '321.654.987-22', email: 'fernanda.souza@email.com', descricao: 'Gerente de TI', linkedinLink: 'https://www.linkedin.com/in/fernandasouza', dataNascimento: '10/02/2001', cidade: 'Salvador', estado: 'Bahia', pais: 'Brasil', cep: '41012-345', senha: 'Fernanda@123'),
            new Candidato(nome: 'Kurosaki Ichigo', cpf: '654.321.987-33', email: 'kurosaki.ichigo@email.com', descricao: 'Arquiteto de Software', linkedinLink: 'https://www.linkedin.com/in/kurosakiichigo', dataNascimento: '11/09/2001', cidade: 'Karakura', estado: 'Soul Society', pais: 'Japão', cep: '29010-456', senha: 'Bankai@123')
    ]


    void addCandidato(Candidato pessoa){
        candidatos.add(pessoa)
    }

    void addEmpresa(Empresa empresa){
        empresas.add(empresa)
    }
}
