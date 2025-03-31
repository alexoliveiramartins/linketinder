package service

import model.Candidato
import model.Empresa
import model.Vaga

class PessoasData {

    private final usuariosDAO = new UsuariosDAO()

    List<Candidato> candidatos
    List<Empresa> empresas
    List<Vaga> vagas

    PessoasData(){
        loadEmpresasData()
        loadCandidatosData()
        loadVagasData()
    }

    void loadCandidatosData(){
        candidatos = usuariosDAO.candidatosData()
    }

    void loadEmpresasData(){
        empresas = usuariosDAO.empresasData()
    }

    void loadVagasData(){
        vagas = usuariosDAO.vagasData()
    }


}
