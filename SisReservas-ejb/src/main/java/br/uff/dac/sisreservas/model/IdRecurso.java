package br.uff.dac.sisreservas.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class IdRecurso implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_recurso")
    private Long idRecurso;

    @Column(name = "id_sala")
    private Long idSala;

    public IdRecurso() {
    }

    public IdRecurso(Long idRecurso, Long idSala) {
        this.idRecurso = idRecurso;
        this.idSala = idSala;
    }

    public Long getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(Long idRecurso) {
        this.idRecurso = idRecurso;
    }

    public Long getIdSala() {
        return idSala;
    }

    public void setIdSala(Long idSala) {
        this.idSala = idSala;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.idRecurso);
        hash = 41 * hash + Objects.hashCode(this.idSala);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final IdRecurso other = (IdRecurso) obj;
        if (!Objects.equals(this.idRecurso, other.idRecurso)) {
            return false;
        }
        if (!Objects.equals(this.idSala, other.idSala)) {
            return false;
        }
        return true;
    }

}
