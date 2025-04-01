package view

import model.Candidato
import model.Empresa
import model.Vaga
import service.PessoasData
import service.UsuariosDAO
import utils.Utils

class MenuAplicacao {
    static void showMenu() {
        PessoasData pessoasData = new PessoasData()

        while (true) {
            println "==== Menu ===="
            println "Escolha uma opcao: "
            println "[1] Listar Candidatos (Visao da Empresa)"
            println "[2] Listar Vagas (Visao do Candidato)"

            println "[3] Listar Curtidas das Empresas"
            println "[4] Listar Curtidas dos Candidatos"

            println "[5] Adicionar Candidato"
            println "[6] Remover Candidato"

            println "[7] Adicionar Empresa"
            println "[8] Remover Empresa"

            println "[9] Adicionar Vaga"
            println "[10] Remover Vaga"

            println "[11] Adicionar Competencia"
            println "[12] Remover Competencia"

            println "[13] Adicionar Curtida de Empresa"
            println "[14] Adicionar Curtida de Candidato"

            println "[15] Sair"
            print "> "

            def input = Utils.readInt()
            switch (input) {
                case '1':
                    pessoasData.candidatos.each {candidato -> {
                        println "Candidato $candidato.id, $candidato.descricao, $candidato.competencias"
                    }}
                    break
                case '2':
                    pessoasData.vagas.each {vaga ->
                        println "$vaga.id - $vaga.titulo, $vaga.descricao Competencias: $vaga.competencias"
                    }
                    break
                case '3':
                    println "Curtidas de empresas: "
                    pessoasData.curtidasEmpresa.each {it -> {
                        Candidato candidato
                        pessoasData.candidatos.each { cand ->
                            if(cand.id == it.id_candidato) candidato = cand
                        }
                        Empresa empresa
                        pessoasData.empresas.each { emp ->
                            if(emp.id == it.id_empresa) empresa = emp
                        }

                        println "${empresa.nome} <3 ${candidato.nome}"
                    }}
                    break
                case '4':
                    println "Curtidas de candidatos: "
                    pessoasData.curtidaCandidatos.each {it -> {
                        Candidato candidato
                        pessoasData.candidatos.each { cand ->
                            if(cand.id == it.id_candidato) candidato = cand
                        }
                        Vaga vaga
                        pessoasData.vagas.each { vag ->
                            if(vag.id == it.id_vaga) vaga = vag
                        }
                        Empresa empresa
                        pessoasData.empresas.each { emp ->
                            if(emp.id == vaga.id_empresa) empresa = emp
                        }

                        println "${candidato.nome} <3 ${vaga.titulo} (${empresa.nome})"
                    }}
                    break
                case '5':
                    Candidato candidatoAdd = MenuCRUD.candidatoInput()
                    pessoasData.adicionarCandidato(candidatoAdd)
                    break
                case '6':
                    printf "Id do candidato para deletar: "
                    def choice = Utils.readInt()
                    pessoasData.removerCandidato(choice)
                    break
                case '7':
                    pessoasData.adicionarEmpresa(MenuCRUD.empresaInput())
                    break
                case '8':
                    printf "Id da empresa para deletar: "
                    def choice = Utils.readInt()
                    pessoasData.removerEmpresa(choice)
                    break
                case '9':
                    println "Digite o ID da empresa da vaga"
                    printf "> "
                    def id_empresa = Utils.readInt()
                    printf "Digite o Titulo da vaga: "
                    def tituloVaga = Utils.readLine()
                    printf "Digite a descricao da vaga: "
                    def descricaoVaga = Utils.readLine()
                    pessoasData.adicionarVaga(id_empresa, tituloVaga, descricaoVaga)
                    break
                case '10':
                    print "ID da vaga: "
                    def idVaga
                    idVaga = Utils.readInt()
                    pessoasData.deletarVaga(idVaga)
                    break
                case '11':
                    println "Adicionar competencia para: [1] Candidato [2] Vaga"
                    printf "> "
                    def option = Utils.readInt()
                    if (option == 1) {
                        println "Digite o id do candidato"
                        printf "> "
                        def idCandidato = Utils.readInt()

                        printf "Digite o nome da competencia: "
                        def nomeCompetencia = Utils.readLine()

                        pessoasData.adicionarCompetenciaCandidato(nomeCompetencia, idCandidato)
                    } else if (option == 2) {
                        println "Digite o id da vaga"
                        printf "> "
                        def idVaga = Utils.readInt()

                        printf "Digite o nome da competencia: "
                        def nomeCompetencia = Utils.readLine()

                        pessoasData.adicionarCompetenciaVaga(nomeCompetencia, idVaga)
                    } else println "Opcao Invalida"
                    break
                case '12':
                    println "Deletar competencia para: [1] Candidato [2] Vaga"
                    printf "> "
                    def option = Utils.readInt()
                    printf "Digite o id da competencia: "
                    def idCompetencia = Utils.readInt()
                    if(option == 1){
                        printf "Digite o id do candidato: "
                        def idCandidato = Utils.readInt()
                        pessoasData.deletarCompetenciaCandidato(idCandidato, idCompetencia)
                    }
                    else if(option == 2){
                        printf "Digite o id da vaga: "
                        def idVaga = Utils.readInt()
                        pessoasData.deletarCompetenciaVaga(idVaga, idCompetencia)
                    }
                    else printf "Opcao Invalida"
                    break
                case '13':
                    printf "Digite o id da empresa: "
                    def idEmpresa = Utils.readInt()
                    printf "Digite o id do usuario para curtir: "
                    def idUsuario = Utils.readInt()
                    pessoasData.adicionarCurtidaEmpresa(idEmpresa, idUsuario)
                    break
                case '14':
                    printf "Digite o id do usuario: "
                    def idUsuario = Utils.readInt()
                    printf "Digite o id da vaga para curtir: "
                    def idVaga = Utils.readInt()
                    pessoasData.adicionarCurtidaCandidato(idUsuario, idVaga)
                    break
                case '15':
                    return
                default:
                    println "Opcao Invalida"
                    break
            }
        }
    }
}