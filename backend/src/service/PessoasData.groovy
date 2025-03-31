package service

import model.Candidato
import model.Empresa

class PessoasData {

    private final usuariosDAO = new UsuariosDAO()

    List<Candidato> candidatos;
    List<Empresa> empresas;



    PessoasData(){
        loadEmpresasData()
        loadCandidatosData()
    }

    void loadCandidatosData(){
        candidatos = usuariosDAO.candidatosData()
    }

    void loadEmpresasData(){
        empresas = usuariosDAO.empresasData()
    }
}
