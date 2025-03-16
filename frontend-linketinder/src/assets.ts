import { Usuario, Empresa } from './classes';
import { candidatosChart, empresasChart } from './componentes';

function cleanForms(): void {
  let forms: HTMLElement | null = document.getElementById('forms');
  if (forms) forms.innerHTML = '';
}

let empresas: Empresa[] = [
  {
    nome: 'Google',
    email: 'contato@google.com',
    estado: 'California',
    descricao: 'Tecnologia e inovação',
    cep: '94043-1351',
    cnpj: '12.345.678/0001-90',
    pais: 'EUA',
    competencias: [
      'Machine Learning',
      'Cloud Computing',
      'Big Data',
      'DevOps',
      'Python',
      'Java',
      'Autonomous Driving',
    ],
  },
  {
    nome: 'Apple',
    email: 'contato@apple.com',
    estado: 'California',
    descricao: 'Eletrônicos e software',
    cep: '95014-2083',
    cnpj: '23.456.789/0001-45',
    pais: 'EUA',
    competencias: [
      'Machine Learning',
      'Cloud Computing',
      'iOS Development',
      'UI/UX Design',
      'Data Analytics',
      'DevOps',
      'Swift',
    ],
  },
  {
    nome: 'Tesla',
    email: 'contato@tesla.com',
    estado: 'California',
    descricao: 'Veículos elétricos e energia',
    cep: '94304-1188',
    cnpj: '34.567.890/0001-56',
    pais: 'EUA',
    competencias: [
      'Machine Learning',
      'Cloud Computing',
      'Big Data',
      'DevOps',
      'Python',
      'Java',
      'Autonomous Driving',
      'IoT',
      'AWS',
      'Robotics',
    ],
  },
  {
    nome: 'Facebook',
    email: 'contato@facebook.com',
    estado: 'California',
    descricao: 'Rede social e inovação digital',
    cep: '94025-2412',
    cnpj: '45.678.901/0001-67',
    pais: 'EUA',
    competencias: [
      'Machine Learning',
      'Cloud Computing',
      'Data Analytics',
      'DevOps',
      'iOS Development',
      'UI/UX Design',
      'Node.js',
      'React',
      'GraphQL',
      'Swift',
      'Robotics',
    ],
  },
  {
    nome: 'Amazon',
    email: 'contato@amazon.com',
    estado: 'Washington',
    descricao: 'E-commerce e tecnologia',
    cep: '98109-5210',
    cnpj: '56.789.012/0001-78',
    pais: 'EUA',
    competencias: [
      'Machine Learning',
      'Cloud Computing',
      'Big Data',
      'Data Analytics',
      'DevOps',
      'Node.js',
      'React',
      'GraphQL',
      'AWS',
      'IoT',
      'Robotics',
    ],
  },
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
    competencias: ['Java', 'Python', 'Spring', 'React', 'Node.js'],
  },
  {
    nome: 'Maria Oliveira',
    email: 'maria.oliveira@email.com',
    estado: 'Minas Gerais',
    descricao: 'Analista de Sistemas',
    cep: '30123-456',
    cpf: '987.654.321-00',
    idade: 28,
    competencias: ['JavaScript', 'Node.js', 'React', 'AWS', 'Docker'],
  },
  {
    nome: 'Carlos Pereira',
    email: 'carlos.pereira@email.com',
    estado: 'Rio de Janeiro',
    descricao: 'Designer UX/UI',
    cep: '23345-678',
    cpf: '456.789.123-11',
    idade: 35,
    competencias: ['Figma', 'Photoshop', 'HTML/CSS', 'React', 'UX Research'],
  },
  {
    nome: 'Fernanda Souza',
    email: 'fernanda.souza@email.com',
    estado: 'Bahia',
    descricao: 'Gerente de TI',
    cep: '41012-345',
    cpf: '321.654.987-22',
    idade: 40,
    competencias: [
      'Gestão de Projetos',
      'Scrum',
      'Java',
      'Docker',
      'Kubernetes',
    ],
  },
  {
    nome: 'Kurosaki Ichigo',
    email: 'kurosaki.ichigo@email.com',
    estado: 'Soul Society',
    descricao: 'Arquiteto de Software',
    cep: '29010-456',
    cpf: '654.321.987-33',
    idade: 18,
    competencias: ['C#', 'Azure', 'Bankai', 'Java', 'Python'],
  },
];

export { empresas, candidatos };

function createElement(
  inputId: string,
  inputText: string,
  placeholder?: string
): { inputLabel: HTMLLabelElement; input: HTMLInputElement } {
  let inputLabel: HTMLLabelElement = document.createElement('label');
  inputLabel.setAttribute('for', `${inputId}`);
  inputLabel.textContent = `${inputText}`;

  let input: HTMLInputElement = document.createElement('input');
  input.setAttribute('id', `${inputId}`);
  input.setAttribute('type', 'text');
  input.setAttribute('name', `${inputId}`);
  if (placeholder) input.setAttribute('placeholder', `${placeholder}`);
  return { inputLabel, input };
}

const formularioCadastroUsuario: {
  inputLabel: HTMLLabelElement;
  input: HTMLInputElement;
}[] = [
  createElement('nome', 'Nome'),
  createElement('email', 'Email'),
  createElement('estado', 'Estado'),
  createElement('descricao', 'Descricao'),
  createElement('cpf', 'CPF'),
  createElement('cep', 'CEP'),
  createElement('idade', 'Idade'),
  createElement('competencias', 'Competencias', 'comp1, comp2, ..., compN'),
  // nao tem nenhum verificador entao use
  // "competencia1, competencia2, ..., competenciaN" >:)
];

const formularioCadastroEmpresa: {
  inputLabel: HTMLLabelElement;
  input: HTMLInputElement;
}[] = [
  createElement('nome', 'Nome'),
  createElement('email', 'Email'),
  createElement('estado', 'Estado'),
  createElement('descricao', 'Descricao'),
  createElement('pais', 'Pais'),
  createElement('cnpj', 'CNPJ'),
  createElement('competencias', 'Competencias', 'comp1, comp2, ..., compN'),
  // nao tem nenhum verificador entao use
  // "competencia1, competencia2, ..., competenciaN" >:)
];

export { formularioCadastroEmpresa, formularioCadastroUsuario };

export function pushEmpresaForm(data: FormData): void {
  let competencias: string = data.get('competencias') as string;
  const empresa: Empresa = new Empresa(
    data.get('nome') as string,
    data.get('email') as string,
    data.get('estado') as string,
    data.get('descricao') as string,
    data.get('cep') as string,
    data.get('cnpj') as string,
    data.get('pais') as string,
    competencias.split(' ,')
  );
  empresas.push(empresa);
  document.querySelectorAll('input').forEach((input) => {
    input.value = '';
  });
  console.log(empresas);
}

export function pushUsuarioForm(data: FormData): void {
  let competencias: string = data.get('competencias') as string;
  const candidato: Usuario = new Usuario(
    data.get('nome') as string,
    data.get('email') as string,
    data.get('estado') as string,
    data.get('descricao') as string,
    data.get('cep') as string,
    data.get('cpf') as string,
    Number(data.get('idade')),
    competencias.split(' ,')
  );
  candidatos.push(candidato);
  document.querySelectorAll('input').forEach((input) => {
    input.value = '';
  });
  console.log(candidatos);
}

export const botaoEmpresaViewCallbackListener = (): void => {
  cleanForms();
  // console.log('Empresa View');

  let views: HTMLElement | null = document.getElementById('views');
  if (views) views.innerHTML = '';

  // <canvas id="chartCandidatos"></canvas>
  let canvas: HTMLCanvasElement = document.createElement('canvas');
  canvas.setAttribute('id', 'chartCandidatos');
  views?.appendChild(canvas);
  candidatosChart();

  candidatos.forEach((element, index) => {
    let elementDiv: HTMLDivElement = document.createElement('div');
    elementDiv.className = 'candidatosList';
    elementDiv.id = 'candidatosList';

    let candidatoNome: HTMLHeadingElement = document.createElement('h3');
    candidatoNome.textContent = `Candidato ${index + 1}`; // here I want element.index
    let candidatoCompetencias: HTMLElement = document.createElement('div');
    candidatoCompetencias.className = 'competencias-subtexto';
    candidatoCompetencias.textContent = `${element.competencias.join(', ')}`;

    elementDiv?.appendChild(candidatoNome);
    elementDiv?.appendChild(candidatoCompetencias);
    views?.appendChild(elementDiv);
  });
};

export const botaoCandidatoViewCallbackListener = (): void => {
  cleanForms();
  // console.log('Candidato View');

  let views: HTMLElement | null = document.getElementById('views');
  if (views) views.innerHTML = '';

  // <canvas id="chartCandidatos"></canvas>
  let canvas: HTMLCanvasElement = document.createElement('canvas');
  canvas.setAttribute('id', 'chartEmpresas');
  views?.appendChild(canvas);
  empresasChart();

  empresas.forEach((element) => {
    let elementDiv: HTMLDivElement = document.createElement('div');
    elementDiv.className = 'empresasList';
    elementDiv.id = 'empresasList';

    let empresaNome: HTMLHeadingElement = document.createElement('h3');
    empresaNome.textContent = element.nome;
    let empresaCompetencias: HTMLElement = document.createElement('div');
    empresaCompetencias.className = 'competencias-subtexto';
    empresaCompetencias.textContent = `${element.competencias.join(', ')}`;

    elementDiv?.appendChild(empresaNome);
    elementDiv?.appendChild(empresaCompetencias);
    views?.appendChild(elementDiv);
  });
};
