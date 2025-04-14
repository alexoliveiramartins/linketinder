import { Usuario, Empresa } from '../components/classes';
import { candidatosChart, empresasChart } from '../components/componentes';
import { REGEX_EMAIL, REGEX_NOME, REGEX_CPF, REGEX_CEP, REGEX_CNPJ } from '../utils/constants.ts'
import { candidatos, empresas } from '../mocks/mockData';
import { botaoCadastroEmpresa, botaoCadastroUsuario } from '../components/componentes';
import { formularioCadastroEmpresa, formularioCadastroUsuario } from "../components/componentes";

function cleanForms(): void {
  let forms: HTMLElement | null = document.getElementById('forms');
  if (forms) forms.innerHTML = '';
}

function cleanViewsLists(): void {
  let views: HTMLElement | null = document.getElementById('views');
  if (views) views.innerHTML = '';
}

export function createElement(
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

export const addCadastroEmpresaEventListener = (): void => {
  document.getElementById('cadastroEmpresa')?.addEventListener('click', (): void => {
    cleanViewsLists();
    let forms = document.getElementById('forms');

    if (forms) {
      forms.innerHTML = '';
      formularioCadastroEmpresa.forEach((element) => {
        element.input.value = '';
        forms.appendChild(element.inputLabel);
        forms.appendChild(element.input);
      });
    }
    forms?.appendChild(botaoCadastroEmpresa);
  });
}

export const addCadastroUsuarioEventListener = (): void => {
  document.getElementById('cadastroUsuario')?.addEventListener('click', (): void => {
    cleanViewsLists();
    let forms = document.getElementById('forms');

    if (forms) {
      forms.innerHTML = '';
      formularioCadastroUsuario.forEach((element) => {
        element.input.value = '';
        forms.appendChild(element.inputLabel);
        forms.appendChild(element.input);
      });
    }
    forms?.appendChild(botaoCadastroUsuario);
  });
}

export const addFormsEventListener = (): void => {
  document.getElementById('forms')?.addEventListener('submit', (e) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget as HTMLFormElement);
    const clickedButton = e.submitter as HTMLButtonElement;

    if (clickedButton.name === 'usuarioButton') {
      if (REGEX_NOME.test(formData.get('nome') as string)) { }
      else {
        alert('Nome Invalido!')
        return;
      }
      if (REGEX_EMAIL.test(formData.get('email') as string)) { }
      else {
        alert('Email Invalido!')
        return;
      }
      if (REGEX_CPF.test(formData.get('cpf') as string)) { }
      else {
        alert('CPF Invalido!')
        return;
      }
      if (REGEX_CEP.test(formData.get('cep') as string)) { }
      else {
        alert('CEP Invalido!')
        return;
      }
      pushUsuarioForm(formData);

    } else if (clickedButton.name === 'empresaButton') {
      if (REGEX_NOME.test(formData.get('nome') as string)) { }
      else {
        alert('Nome Invalido!')
        return;
      }
      if (REGEX_EMAIL.test(formData.get('email') as string)) { }
      else {
        alert('Email Invalido!')
        return;
      }
      if (REGEX_CNPJ.test(formData.get('cnpj') as string)) { }
      else {
        alert('CNPJ Invalido!')
        return;
      }
      pushEmpresaForm(formData);
    }
  });
}

export const addBotaoEmpresaViewEventListener = (): void => {
  document.getElementById('botaoEmpresaView')?.addEventListener('click', botaoEmpresaViewCallbackListener);
}

export const addBotaoCandidatoViewEventListener = (): void => {
  document.getElementById('botaoCandidatoView')?.addEventListener('click', botaoCandidatoViewCallbackListener);
}