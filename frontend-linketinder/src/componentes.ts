const botaoCadastroUsuario: HTMLButtonElement = document.createElement('button');
botaoCadastroUsuario.setAttribute('type', 'submit')
botaoCadastroUsuario.setAttribute('id', 'buttonEnviarCadastroUsuario')
botaoCadastroUsuario.setAttribute('name', 'usuarioButton')
botaoCadastroUsuario.className = 'buttonEnviarCadastroUsuario'
botaoCadastroUsuario.innerText = 'Enviar Registro de Usuario';
botaoCadastroUsuario.style.marginTop = "10px";

export { botaoCadastroUsuario };

const botaoCadastroEmpresa: HTMLButtonElement = document.createElement('button');
botaoCadastroEmpresa.setAttribute('type', 'submit')
botaoCadastroEmpresa.setAttribute('id', 'buttonEnviarCadastroEmpresa')
botaoCadastroEmpresa.setAttribute('name', 'empresaButton')
botaoCadastroEmpresa.className = 'buttonEnviarCadastroEmpresa'
botaoCadastroEmpresa.innerText = 'Enviar Registro de Empresa';
botaoCadastroEmpresa.style.marginTop = "10px";

export { botaoCadastroEmpresa };