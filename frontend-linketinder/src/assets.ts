import { Usuario, Empresa } from "./classes";

let empresas: Empresa[] = [
    {
        nome: 'Google',
        email: 'contato@google.com',
        estado: 'California',
        descricao: 'Tecnologia e inovação',
        cep: '94043-1351',
        cnpj: '12.345.678/0001-90',
        pais: 'EUA',
        competencias: ['Python', 'Java', 'Machine Learning']
    },
    {
        nome: 'Apple',
        email: 'contato@apple.com',
        estado: 'California',
        descricao: 'Eletrônicos e software',
        cep: '95014-2083',
        cnpj: '23.456.789/0001-45',
        pais: 'EUA',
        competencias: ['Swift', 'iOS Development', 'UI Design']
    },
    {
        nome: 'Tesla',
        email: 'contato@tesla.com',
        estado: 'California',
        descricao: 'Veículos elétricos e energia',
        cep: '94304-1188',
        cnpj: '34.567.890/0001-56',
        pais: 'EUA',
        competencias: ['Electrical Engineering', 'Autonomous Vehicles', 'Renewable Energy']
    },
    {
        nome: 'Facebook',
        email: 'contato@facebook.com',
        estado: 'California',
        descricao: 'Rede social e inovação digital',
        cep: '94025-2412',
        cnpj: '45.678.901/0001-67',
        pais: 'EUA',
        competencias: ['React', 'Node.js', 'Social Media Marketing']
    },
    {
        nome: 'Amazon',
        email: 'contato@amazon.com',
        estado: 'Washington',
        descricao: 'E-commerce e tecnologia',
        cep: '98109-5210',
        cnpj: '56.789.012/0001-78',
        pais: 'EUA',
        competencias: ['AWS', 'E-commerce', 'Cloud Computing']
    }
];

let candidatos: Usuario[] = [
    {
        nome: 'Alex Silva',
        email: 'alex.silva@email.com',
        estado: 'São Paulo',
        descricao: 'Desenvolvedor Web',
        cep: '01234-567',
        cpf: '123.456.789-10',
        idade: 19,
        competencias: ['Java', 'Python', 'Spring']
    },
    {
        nome: 'Maria Oliveira',
        email: 'maria.oliveira@email.com',
        estado: 'Minas Gerais',
        descricao: 'Analista de Sistemas',
        cep: '30123-456',
        cpf: '987.654.321-00',
        idade: 28,
        competencias: ['JavaScript', 'Node.js', 'React']
    },
    {
        nome: 'Carlos Pereira',
        email: 'carlos.pereira@email.com',
        estado: 'Rio de Janeiro',
        descricao: 'Designer UX/UI',
        cep: '23345-678',
        cpf: '456.789.123-11',
        idade: 35,
        competencias: ['Figma', 'Photoshop', 'HTML/CSS']
    },
    {
        nome: 'Fernanda Souza',
        email: 'fernanda.souza@email.com',
        estado: 'Bahia',
        descricao: 'Gerente de TI',
        cep: '41012-345',
        cpf: '321.654.987-22',
        idade: 40,
        competencias: ['Gestão de Projetos', 'Scrum', 'Java']
    },
    {
        nome: 'Kurosaki Ichigo',
        email: 'kurosaki.ichigo@email.com',
        estado: 'Soul Society',
        descricao: 'Arquiteto de Software',
        cep: '29010-456',
        cpf: '654.321.987-33',
        idade: 18,
        competencias: ['C#', 'Azure', 'Bankai']
    }
];

export { empresas, candidatos }

function createElement(
    inputId: string,
    inputText: string,
    placeholder?: string
): { inputLabel: HTMLLabelElement, input: HTMLInputElement } {
    let inputLabel: HTMLLabelElement = document.createElement('label')
    inputLabel.setAttribute('for', `${inputId}`)
    inputLabel.textContent = `${inputText}`

    let input: HTMLInputElement = document.createElement('input')
    input.setAttribute('id', `${inputId}`);
    input.setAttribute('type', 'text')
    input.setAttribute('name', `${inputId}`)
    if (placeholder) input.setAttribute('placeholder', `${placeholder}`)
    return { inputLabel, input }
}

const formularioCadastroUsuario: { inputLabel: HTMLLabelElement, input: HTMLInputElement }[] = [
    createElement('nome', 'Nome'),
    createElement('email', 'Email'),
    createElement('estado', 'Estado'),
    createElement('descricao', 'Descricao'),
    createElement('cpf', 'CPF'),
    createElement('cep', 'CEP'),
    createElement('idade', 'Idade'),
    createElement('competencias', 'Competencias', "comp1, comp2, ..., compN")
    // nao tem nenhum verificador entao use
    // "competencia1, competencia2, ..., competenciaN" >:)
]

const formularioCadastroEmpresa: { inputLabel: HTMLLabelElement, input: HTMLInputElement }[] = [
    createElement('nome', 'Nome'),
    createElement('email', 'Email'),
    createElement('estado', 'Estado'),
    createElement('descricao', 'Descricao'),
    createElement('pais', 'Pais'),
    createElement('cnpj', 'CNPJ'),
    createElement('competencias', 'Competencias', "comp1, comp2, ..., compN")
    // nao tem nenhum verificador entao use
    // "competencia1, competencia2, ..., competenciaN" >:)
]

export { formularioCadastroEmpresa, formularioCadastroUsuario }

export function pushEmpresaForm(data: FormData): void {
    let competencias: string = data.get("competencias") as string;
    const empresa: Empresa = new Empresa(
        data.get("nome") as string,
        data.get("email") as string,
        data.get("estado") as string,
        data.get("descricao") as string,
        data.get("cep") as string,
        data.get("cnpj") as string,
        data.get("pais") as string,
        competencias.split(' ,')
    )
    empresas.push(empresa);
    console.log(empresas);
    console.log(competencias);
}

export function pushUsuarioForm(data: FormData): void {
    let competencias: string = data.get("competencias") as string;
    const candidato: Usuario = new Usuario(
        data.get("nome") as string,
        data.get("email") as string,
        data.get("estado") as string,
        data.get("descricao") as string,
        data.get("cep") as string,
        data.get("cpf") as string,
        Number(data.get("idade")),
        competencias.split(' ,')
    )
    candidatos.push(candidato);
    console.log(candidatos)
}
