package model

class Vaga {
    int id, id_empresa
    String titulo, descricao
    ArrayList<Integer> idCurtidasVaga
    List<String> competencias

    void loadCurtidas(){

    }

    @Override
    public String toString() {
        return "${id}, ${id_empresa}, ${titulo}, ${descricao}"
    }
}
