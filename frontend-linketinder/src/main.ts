import './style.css';
import { botaoCadastroEmpresa, botaoCadastroUsuario } from './componentes';
import {
  formularioCadastroEmpresa,
  formularioCadastroUsuario,
  pushEmpresaForm,
  pushUsuarioForm,
  botaoEmpresaViewCallbackListener,
  botaoCandidatoViewCallbackListener,
} from './assets';

// consertar:
// 1. Nao permitir formularios em branco

function cleanViewsLists(): void {
  let views: HTMLElement | null = document.getElementById('views');
  if (views) views.innerHTML = '';
}

document.addEventListener('DOMContentLoaded', () => {
  document.getElementById('forms')?.addEventListener('submit', (e) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget as HTMLFormElement);
    const clickedButton = e.submitter as HTMLButtonElement;

    if (clickedButton.name === 'usuarioButton') {
      pushUsuarioForm(formData);
    } else if (clickedButton.name === 'empresaButton') {
      pushEmpresaForm(formData);
    }
  });

  document
    .getElementById('cadastroUsuario')
    ?.addEventListener('click', (): void => {
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

  document
    .getElementById('cadastroEmpresa')
    ?.addEventListener('click', (): void => {
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

  // botoes de view
  document
    .getElementById('botaoEmpresaView')
    ?.addEventListener('click', botaoEmpresaViewCallbackListener);
  document
    .getElementById('botaoCandidatoView')
    ?.addEventListener('click', botaoCandidatoViewCallbackListener);
  // empresasChart();
  // candidatosChart();
});
