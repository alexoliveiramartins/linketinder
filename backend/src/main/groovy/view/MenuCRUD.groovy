package view


import model.Candidato
import model.Empresa
import utils.Utils

class MenuCRUD {
    static void showMenu(menuActions) {
        while (true) {
            ConsoleView.printMenuOptions()

            def input = Utils.readInt()
            switch (input) {
                case '1':
                    menuActions.listCandidatos()
                    break
                case '2':
                    Candidato candidatoAdd = candidatoInput()
                    menuActions.addCandidato(candidatoAdd)
                    break
                case '3':
                    def option = Utils.promptInputInt("ID do candidato")
                    menuActions.deleteCandidato(option)
                    break
                case '4':
                    def candidatoId = Utils.promptInputInt("ID do candidato")
                    ConsoleView.printMenuPropriedadesCandidato()
                    def option = Utils.promptInputInt("Opcao")
                    def valor = Utils.promptInput("Novo valor")
                    menuActions.updateCandidato(option, valor, candidatoId)
                    break
                case '5':
                    menuActions.listEmpresas()
                    break
                case '6':
                    def empresa = empresaInput()
                    menuActions.addEmpresa(empresa)
                    break
                case '7':
                    def idEmpresa = Utils.promptInputInt("ID da empresa para deletar")
                    menuActions.deleteEmpresa(idEmpresa)
                    break
                case '8':
                    def empresaId = Utils.promptInputInt("ID da empresa para atualizar")
                    ConsoleView.printMenuPropriedadesEmpresa()
                    def option = Utils.promptInputInt("Opcao")
                    def valor = Utils.promptInput("Novo valor")
                    menuActions.updateEmpresa(option, valor, empresaId)
                    break
                case '9':
                    menuActions.listVagas()
                    break
                case '10':
                    def id_empresa = Utils.promptInputInt("ID da empresa da vaga")
                    def tituloVaga = Utils.promptInput("Titulo da vaga")
                    def descricaoVaga = Utils.promptInput("Descricao da vaga")
                    menuActions.addVaga(id_empresa, tituloVaga, descricaoVaga)
                    break
                case '11':
                    print "ID da vaga: "
                    def idVaga
                    idVaga = Utils.readInt()
                    ConsoleView.printMenuPropriedadesVaga()
                    def option = Utils.promptInputInt("Opcao")
                    def valor = Utils.promptInput("Novo valor")
                    menuActions.updateVaga(option, valor, idVaga)
                    break
                case '12':
                    def idVaga = Utils.promptInputInt("ID da vaga")
                    menuActions.deleteEmpresa(idVaga)
                    break
                case '13':
                    menuActions.listCompetencias()
                    break
                case '14':
                    println "Adicionar competencia para:\n[1] Candidato\n[2] Vaga"
                    def option = Utils.promptInputInt("Opcao")
                    menuActions.addCompetencia(option)
                    break
                case '15':
                    def idCompetencia = Utils.promptInputInt("ID da competencia")
                    def novo = Utils.promptInput("Novo nome")
                    menuActions.updateCompetencia(idCompetencia, novo)
                    break
                case '16':
                    def idCompetencia = Utils.promptInputInt("ID da competencia")
                    menuActions.deleteCompetencia(idCompetencia)
                    break
                case '17':
                    def idEmpresa = Utils.promptInputInt("ID da empresa")
                    def idUsuario = Utils.promptInputInt("ID do usuario para curtir")
                    menuActions.addCurtidaEmpresa(idEmpresa, idUsuario)
                    break
                case '18':
                    def idUsuario = Utils.promptInputInt("ID do usuario")
                    def idVaga = Utils.promptInputInt("ID da vaga para curtir")
                    menuActions.addCurtidaCandidato(idUsuario, idVaga)
                    break
                case '19':
                    menuActions.mostrarCurtidasCandidato()
                    break
                case '20':
                    menuActions.mostrarCurtidasEmpresa()
                    break
                case '21':
                    return
                default:
                    println "Opcao Invalida"
                    break
            }
        }
    }

    static Candidato candidatoInput() {
        Candidato candidato = new Candidato()
        printf ""

        candidato.nome = Utils.promptInput("Nome")
        candidato.cpf = Utils.promptInput("CPF")
        candidato.email = Utils.promptInput("Email")
        candidato.descricao = Utils.promptInput("Descricao")
        candidato.linkedinLink = Utils.promptInput("Link do Linkedin")
        candidato.dataNascimento = Utils.promptInput("dataNascimento (dd-mm-yyyy)")
        candidato.cidade = Utils.promptInput("Cidade")
        candidato.estado = Utils.promptInput("Estado")
        candidato.pais = Utils.promptInput("Pais")
        candidato.cep = Utils.promptInput("CEP")
        candidato.senha = Utils.promptInput("Senha")
        return candidato
    }

    static Empresa empresaInput() {
        Empresa empresa = new Empresa()
        printf ""

        empresa.nome = Utils.promptInput("Nome")
        empresa.cnpj = Utils.promptInput("CNPJ")
        empresa.email = Utils.promptInput("Email")
        empresa.descricao = Utils.promptInput("Descricao")
        empresa.linkedinLink = Utils.promptInput("Link do Linkedin")
        empresa.senha = Utils.promptInput("Senha")
        empresa.cidade = Utils.promptInput("Cidade")
        empresa.estado = Utils.promptInput("Estado")
        empresa.pais = Utils.promptInput("Pais")
        empresa.cep = Utils.promptInput("CEP")
        return empresa
    }
}
