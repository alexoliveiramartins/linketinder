package service

import model.Candidato
import model.Competencia
import model.CompetenciaCandidato
import model.CompetenciaVaga
import model.CurtidaCandidato
import model.CurtidaEmpresa
import model.Empresa
import model.Vaga

class PessoasData {

    private final usuariosDAO = new UsuariosDAO()

    List<Candidato> candidatos
    List<Empresa> empresas
    List<Vaga> vagas
    List<CurtidaCandidato> curtidaCandidatos // Curtidas de candidatos em empresas
    List<CurtidaEmpresa> curtidasEmpresa // Curtidas da empresa em candidatos
    List<Competencia> competencias
    List<CompetenciaCandidato> competenciaCandidatos
    List<CompetenciaVaga> competenciaVagas

    PessoasData(){
        loadEmpresasData()
        loadCandidatosData()
        loadVagasData()
    }

    void loadCandidatosData(){
        competenciaCandidatos = usuariosDAO.candidatoCompetenciasData()
        competencias = usuariosDAO.competenciaData()
        candidatos = usuariosDAO.candidatosData()
        curtidaCandidatos = usuariosDAO.curtidasCandidatosData()
        candidatos.each {candidato -> {
            curtidaCandidatos.each { curtida -> {
                if(curtida.id_candidato == candidato.id){
                    candidato.curtidas.push(curtida.id_vaga)
                }
            }}
            competenciaCandidatos.each {competencia -> {
                if(competencia.id_candidato == candidato.id){
                    competencias.each {comp -> {
                        if(comp.id == competencia.id_competencia) candidato.competencias.push(comp)
                    }}
                }
            }}
        }}

    }

    void loadEmpresasData(){
        empresas = usuariosDAO.empresasData()
        curtidasEmpresa = usuariosDAO.curtidasEmpresasData()
        empresas.each {empresa -> {
            curtidasEmpresa.each {curtida -> {
                if(curtida.id_empresa == empresa.id){
                    empresa.curtidas.push(curtida.id_candidato)
                }
            }}
        }}
    }

    void loadVagasData(){
        competenciaVagas = usuariosDAO.vagaCompetenciasData()
        competencias = usuariosDAO.competenciaData()
        vagas = usuariosDAO.vagasData()
        vagas.each {vaga -> {
            competenciaVagas.each {competencia -> {
                if(competencia.id_vaga == vaga.id) {
                    competencias.each {comp -> {
                        if(comp.id == competencia.id_competencia) vaga.competencias.push(comp)
                    }}
                }
            }}
        }}
    }

    void adicionarCandidato(Candidato candidatoAdd){
        usuariosDAO.addCandidato(candidatoAdd)
        loadCandidatosData()
    }

    void removerCandidato(int id){
        usuariosDAO.deleteCandidato(id)
        loadCandidatosData()
    }

    void adicionarEmpresa(Empresa empresa){
        usuariosDAO.addEmpresa(empresa)
        loadEmpresasData()
    }

    void removerEmpresa(int id){
        usuariosDAO.deleteEmpresa(id)
        loadEmpresasData()
    }

    void adicionarVaga(id_empresa, tituloVaga, descricaoVaga){
        usuariosDAO.addVaga(id_empresa, tituloVaga, descricaoVaga)
        loadVagasData()
    }

    void deletarVaga(int idVaga){
        usuariosDAO.deleteVaga(idVaga)
        loadVagasData()
    }

    void adicionarCompetenciaCandidato(String nomeCompetencia, int idCandidato){
        usuariosDAO.addCompetenciaCandidato(nomeCompetencia, idCandidato)
        loadCandidatosData()
    }

    void adicionarCompetenciaVaga(String nomeCompetencia, int idVaga){
        usuariosDAO.addCompetenciaVaga(nomeCompetencia, idVaga)
        loadVagasData()
    }

    void deletarCompetenciaCandidato(int idCandidato, int idCompetencia){
        usuariosDAO.deleteCompetenciaCandidato(idCandidato, idCompetencia)
        loadCandidatosData()
    }

    void deletarCompetenciaVaga(int idVaga, int idCompetencia){
        usuariosDAO.deleteCompetenciaVaga(idVaga, idCompetencia)
        loadVagasData()
    }

    void adicionarCurtidaEmpresa(int id_empresa, int id_candidato){
        usuariosDAO.addCurtidaEmpresa(id_empresa, id_candidato)
        loadEmpresasData()
    }

    void adicionarCurtidaCandidato(int id_candidato, int id_vaga){
        usuariosDAO.addCurtidaCandidato(id_candidato, id_vaga)
        loadCandidatosData()
    }
}
