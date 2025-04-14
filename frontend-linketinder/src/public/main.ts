import "../../styles/globals.css";
import { botaoCadastroEmpresa, botaoCadastroUsuario } from '../components/componentes';
import {
  formularioCadastroEmpresa,
  formularioCadastroUsuario,
  pushEmpresaForm,
  pushUsuarioForm,
  botaoEmpresaViewCallbackListener,
  botaoCandidatoViewCallbackListener,
} from '../utils/assets';

// consertar:
// 1. Nao permitir formularios em branco

function cleanViewsLists(): void {
  let views: HTMLElement | null = document.getElementById('views');
  if (views) views.innerHTML = '';
}

document.addEventListener('DOMContentLoaded', () => {

  // Event Listener enviar o formulario
  document.getElementById('forms')?.addEventListener('submit', (e) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget as HTMLFormElement);
    const clickedButton = e.submitter as HTMLButtonElement;

    if (clickedButton.name === 'usuarioButton') {
      if (/^[A-Za-zÀ-ü\s]+$/.test(formData.get('nome') as string)) { } // do nothing
      else {
        alert('Nome Invalido!')
        return;
      }
      if (/^[_\.\-0-9A-Za-zÀ-ü]+@\w+\.\w{2,}(\.\w{2,})?$/.test(formData.get('email') as string)) { } // do nothing
      else {
        alert('Email Invalido!')
        return;
      }
      if (/^(\d{3}\.\d{3}\.\d{3}-\d{2}||\d{3}\d{3}\d{3}\d{2})$/.test(formData.get('cpf') as string)) { } // do nothing
      else {
        alert('CPF Invalido!')
        return;
      }
      if (/^(\d{5}-?\d{3})$/.test(formData.get('cep') as string)) { } // do nothing
      else {
        alert('CEP Invalido!')
        return;
      }
      pushUsuarioForm(formData);

    } else if (clickedButton.name === 'empresaButton') {
      if (/^[A-Za-zÀ-ü\s]+$/.test(formData.get('nome') as string)) { } // do nothing
      else {
        alert('Nome Invalido!')
        return;
      }
      if (/^[_\.\-0-9A-Za-zÀ-ü]+@\w+\.\w{2,}(\.\w{2,})?$/.test(formData.get('email') as string)) { } // do nothing
      else {
        alert('Email Invalido!')
        return;
      }
      if (/^(\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}||\d{2}\d{3}\d{3}\d{4}\d{2})$/.test(formData.get('cnpj') as string)) { } // do nothing
      else {
        alert('CNPJ Invalido!')
        return;
      }
      pushEmpresaForm(formData);
    }
  });

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

  document.getElementById('botaoEmpresaView')?.addEventListener('click', botaoEmpresaViewCallbackListener);
  document.getElementById('botaoCandidatoView')?.addEventListener('click', botaoCandidatoViewCallbackListener);
});
