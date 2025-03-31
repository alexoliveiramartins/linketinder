package model

class Vaga {
    int id, id_empresa
    String titulo, descricao
    ArrayList<Integer> idCurtidasVaga

    @Override
    public String toString() {
        return "${id}, ${id_empresa}, ${titulo}, ${descricao}"
    }
}
