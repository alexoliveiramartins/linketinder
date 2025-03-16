import { Chart, registerables } from 'chart.js';
import { candidatos, empresas } from './assets';
Chart.register(...registerables);

const botaoCadastroUsuario: HTMLButtonElement =
    document.createElement('button');
botaoCadastroUsuario.setAttribute('type', 'submit');
botaoCadastroUsuario.setAttribute('id', 'buttonEnviarCadastroUsuario');
botaoCadastroUsuario.setAttribute('name', 'usuarioButton');
botaoCadastroUsuario.className = 'buttonEnviarCadastroUsuario';
botaoCadastroUsuario.innerText = 'Enviar Registro de Usuario';
botaoCadastroUsuario.style.marginTop = '10px';

export { botaoCadastroUsuario };

const botaoCadastroEmpresa: HTMLButtonElement =
    document.createElement('button');
botaoCadastroEmpresa.setAttribute('type', 'submit');
botaoCadastroEmpresa.setAttribute('id', 'buttonEnviarCadastroEmpresa');
botaoCadastroEmpresa.setAttribute('name', 'empresaButton');
botaoCadastroEmpresa.className = 'buttonEnviarCadastroEmpresa';
botaoCadastroEmpresa.innerText = 'Enviar Registro de Empresa';
botaoCadastroEmpresa.style.marginTop = '10px';

export { botaoCadastroEmpresa };

export const empresasChart = (): Chart => {
    const competenciaCount: Map<string, number> = new Map();

    empresas.forEach((empresa) => {
        empresa.competencias.forEach((competencia) => {
            const currentCount = competenciaCount.get(competencia) ?? 0;
            competenciaCount.set(competencia, currentCount + 1);
        });
    });

    console.log(competenciaCount);
    console.log(competenciaCount.keys);
    console.log(competenciaCount.values);

    var xValues = [...competenciaCount.keys()];
    var yValues = [...competenciaCount.values()];

    let chartEmpresas: Chart = new Chart('chartEmpresas', {
        type: 'bar',
        data: {
            labels: xValues,
            datasets: [
                {
                    label: 'Quantidade de Empresas',
                    data: yValues,
                    backgroundColor: 'rgba(75, 192, 192, 0.5)',
                },
            ],
        },
        options: {
            responsive: false,
            maintainAspectRatio: false,
            scales: {
                y: {
                    beginAtZero: true,
                    min: 0,
                    ticks: {
                        stepSize: 1,
                    },
                },
            },
            plugins: {
                legend: { display: true },
                title: {
                    display: true,
                    text: 'Distribuição de Competências por Empresa',
                },
            },
        },
    });
    return chartEmpresas;
};

export const candidatosChart = (): Chart => {
    const competenciaCount: Map<string, number> = new Map();

    candidatos.forEach((empresa) => {
        empresa.competencias.forEach((competencia) => {
            const currentCount = competenciaCount.get(competencia) ?? 0;
            competenciaCount.set(competencia, currentCount + 1);
        });
    });

    console.log(competenciaCount);
    console.log(competenciaCount.keys);
    console.log(competenciaCount.values);

    var xValues = [...competenciaCount.keys()];
    var yValues = [...competenciaCount.values()];

    let chartEmpresas: Chart = new Chart('chartCandidatos', {
        type: 'bar',
        data: {
            labels: xValues,
            datasets: [
                {
                    label: 'Quantidade de Candidatos',
                    data: yValues,
                    backgroundColor: 'rgba(75, 192, 192, 0.5)',
                },
            ],
        },
        options: {
            responsive: false,
            maintainAspectRatio: false,
            scales: {
                y: {
                    beginAtZero: true,
                    min: 0,
                    ticks: {
                        stepSize: 1,
                    },
                },
            },
            plugins: {
                legend: { display: true },
                title: {
                    display: true,
                    text: 'Distribuição de Competências por Candidatos',
                },
            },
        },
    });
    return chartEmpresas;
};
