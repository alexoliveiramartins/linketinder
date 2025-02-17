import model.PessoaFisica
import model.PessoaJuridica

def empresas = [
        new PessoaJuridica(nome: 'Google', email: 'contato@google.com', estado: 'California', descricao: 'Tecnologia e inovação', cep: '94043-1351', cnpj: '12.345.678/0001-90', pais: 'EUA', competencias: ['Python', 'Java', 'Machine Learning']),
        new PessoaJuridica(nome: 'Apple', email: 'contato@apple.com', estado: 'California', descricao: 'Eletrônicos e software', cep: '95014-2083', cnpj: '23.456.789/0001-45', pais: 'EUA', competencias: ['Swift', 'iOS Development', 'UI Design']),
        new PessoaJuridica(nome: 'Tesla', email: 'contato@tesla.com', estado: 'California', descricao: 'Veículos elétricos e energia', cep: '94304-1188', cnpj: '34.567.890/0001-56', pais: 'EUA', competencias: ['Electrical Engineering', 'Autonomous Vehicles', 'Renewable Energy']),
        new PessoaJuridica(nome: 'Facebook', email: 'contato@facebook.com', estado: 'California', descricao: 'Rede social e inovação digital', cep: '94025-2412', cnpj: '45.678.901/0001-67', pais: 'EUA', competencias: ['React', 'Node.js', 'Social Media Marketing']),
        new PessoaJuridica(nome: 'Amazon', email: 'contato@amazon.com', estado: 'Washington', descricao: 'E-commerce e tecnologia', cep: '98109-5210', cnpj: '56.789.012/0001-78', pais: 'EUA', competencias: ['AWS', 'E-commerce', 'Cloud Computing'])
]

def candidatos = [
        new PessoaFisica(nome: 'Alex Silva', email: 'alex.silva@email.com', estado: 'São Paulo', descricao: 'Desenvolvedor Web', cep: '01234-567', cpf: '123.456.789-10', idade: 19, competencias: ['Java', 'Python', 'Spring']),
        new PessoaFisica(nome: 'Maria Oliveira', email: 'maria.oliveira@email.com', estado: 'Minas Gerais', descricao: 'Analista de Sistemas', cep: '30123-456', cpf: '987.654.321-00', idade: 28, competencias: ['JavaScript', 'Node.js', 'React']),
        new PessoaFisica(nome: 'Carlos Pereira', email: 'carlos.pereira@email.com', estado: 'Rio de Janeiro', descricao: 'Designer UX/UI', cep: '23345-678', cpf: '456.789.123-11', idade: 35, competencias: ['Figma', 'Photoshop', 'HTML/CSS']),
        new PessoaFisica(nome: 'Fernanda Souza', email: 'fernanda.souza@email.com', estado: 'Bahia', descricao: 'Gerente de TI', cep: '41012-345', cpf: '321.654.987-22', idade: 40, competencias: ['Gestão de Projetos', 'Scrum', 'Java']),
        new PessoaFisica(nome: 'Lucas Costa', email: 'lucas.costa@email.com', estado: 'Espírito Santo', descricao: 'Arquiteto de Software', cep: '29010-456', cpf: '654.321.987-33', idade: 32, competencias: ['C#', 'Azure', 'Design Patterns'])
]