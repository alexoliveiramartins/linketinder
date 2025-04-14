import "../../styles/globals.css";
import { addFormsEventListener, addCadastroUsuarioEventListener, addCadastroEmpresaEventListener, addBotaoEmpresaViewEventListener, addBotaoCandidatoViewEventListener } from "../utils/assets";

document.addEventListener('DOMContentLoaded', () => {
  addFormsEventListener();
  addCadastroUsuarioEventListener();
  addCadastroEmpresaEventListener();
  addBotaoEmpresaViewEventListener();
  addBotaoCandidatoViewEventListener();
});
