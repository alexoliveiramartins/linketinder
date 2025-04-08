package model

class Vaga {
    int id, id_empresa
    String titulo, descricao
    List<Competencia> competencias = new ArrayList<>()

    @Override
    String toString() {
        return "id: ${id}, id_empresa: ${id_empresa}, ${titulo}, ${descricao} " +
                "Competencias: $competencias"
    }
}
