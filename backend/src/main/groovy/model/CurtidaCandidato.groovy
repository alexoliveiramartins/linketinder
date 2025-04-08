package model

import java.time.LocalDateTime

class CurtidaCandidato {
    int id, id_candidato, id_vaga
    LocalDateTime data

    @Override
    String toString() {
        return "${id}, ${id_candidato}, ${id_vaga}, ${data}"
    }
}
