package dao

interface ICurtidaDao {
    void deleteCurtidaCandidato(int id_candidato, int id_vaga)
    void deleteCurtidaEmpresa(int id_empresa, int id_candidato)
    void addCurtidaCandidato(int id_candidato, int id_vaga)
    void addCurtidaEmpresa(int id_empresa, int id_candidato)
}