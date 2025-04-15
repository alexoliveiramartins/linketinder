package view

class ConsoleView {

    static void printMenuOptions() {
        println "==== Menu ===="
        println "Escolha uma opcao: "
        println "[1] Listar Candidatos"
        println "[2] Adicionar Candidato"
        println "[3] Remover Candidato"
        println "[4] Atualizar Candidato"

        println "[5] Listar Empresas"
        println "[6] Adicionar Empresa"
        println "[7] Remover Empresa"
        println "[8] Atualizar Empresa"

        println "[9] Listar Vagas"
        println "[10] Adicionar Vaga"
        println "[11] Editar Vaga"
        println "[12] Deletar Vaga"

        println "[13] Listar Competencias"
        println "[14] Adicionar Competencia"
        println "[15] Editar Competencia"
        println "[16] Remover Competencia"

        println "[17] Adicionar Curtida de Empresa"
        println "[18] Adicionar Curtida de Candidato"
        println "[19] Mostrar Curtidas do Candidato"
        println "[20] Mostrar Curtidas da Empresa"

        println "[21] Sair"
        print "> "
    }

    static void printMenuPropriedadesCandidato() {
        println "Selecione uma propriedade para editar: "
        println "[1] Nome"
        println "[2] CPF"
        println "[3] Email"
        println "[4] Descricao"
        println "[5] Link Linkedin"
        println "[6] Data Nascimento"
        println "[7] Senha"
        println "[8] Cidade"
        println "[9] Estado"
        println "[10] Pais"
        println "[11] Cep"
    }

    static void printMenuPropriedadesEmpresa() {
        println "Selecione uma propriedade para editar: "
        println "[1] Nome"
        println "[2] CNPJ"
        println "[3] Email"
        println "[4] Descricao"
        println "[5] Link Linkedin"
        println "[6] Senha"
        println "[7] Cidade"
        println "[8] Estado"
        println "[9] Pais"
        println "[10] Cep"
    }

    static void printMenuPropriedadesVaga() {
        println "Selecione uma propriedade para editar: "
        println "[1] Titulo"
        println "[2] Descricao"
    }
}
