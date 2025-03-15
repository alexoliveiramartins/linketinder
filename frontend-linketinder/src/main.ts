import './style.css'
import { Empresa, Usuario } from './classes'
import { botaoCadastroEmpresa, botaoCadastroUsuario } from './componentes';
import {
  empresas, candidatos,
  formularioCadastroEmpresa, formularioCadastroUsuario,
  pushEmpresaForm,
  pushUsuarioForm
} from './assets';


document.getElementById("forms")?.addEventListener("submit", (e) => {
  e.preventDefault();
  const formData = new FormData(e.currentTarget as HTMLFormElement);
  const clickedButton = e.submitter as HTMLButtonElement;

  if (clickedButton.name === 'usuarioButton') {
    pushUsuarioForm(formData);
  } else if (clickedButton.name === 'empresaButton') {
    pushEmpresaForm(formData);
  }
}
);

document.getElementById("cadastroUsuario")
  ?.addEventListener("click", (): void => {
    let forms = document.getElementById('forms');

    if (forms) {
      forms.innerHTML = ''
      formularioCadastroUsuario.forEach(element => {
        element.input.value = '';
        forms.appendChild(element.inputLabel);
        forms.appendChild(element.input);
      });
    }
    forms?.appendChild(botaoCadastroUsuario);
  });

document.getElementById("cadastroEmpresa")
  ?.addEventListener("click", (): void => {
    let forms = document.getElementById('forms');

    if (forms) {
      forms.innerHTML = ''
      formularioCadastroEmpresa.forEach(element => {
        element.input.value = '';
        forms.appendChild(element.inputLabel);
        forms.appendChild(element.input);
      });
    }
    forms?.appendChild(botaoCadastroEmpresa);
  });
