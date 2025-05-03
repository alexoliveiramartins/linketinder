package model

class Vaga {
    int id, id_empresa
    String titulo, descricao
    List<Competencia> competencias = new ArrayList<>()

    Vaga() {}

    Vaga(id_empresa, titulo, descricao){
        this.id_empresa = id_empresa
        this.titulo = titulo
        this.descricao = descricao
    }

    Vaga(id, id_empresa, titulo, descricao){
        this.id = id
        this.id_empresa = id_empresa
        this.titulo = titulo
        this.descricao = descricao
    }

    @Override
    String toString() {
        return "id: ${id}, id_empresa: ${id_empresa}, ${titulo}, ${descricao} " +
                "Competencias: $competencias"
    }
}
