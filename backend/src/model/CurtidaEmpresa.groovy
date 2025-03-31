package model

import java.time.LocalDateTime

class CurtidaEmpresa {
    int id, id_empresa, id_candidato
    LocalDateTime data


    @Override
    public String toString() {
        return "${id}, ${id_empresa}, ${id_candidato}, ${data}"
    }
}
