package br.uff.dac.sisreservas.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class IdSala implements Serializable {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_sala")
    private Long idSala;
    
    @Column(name="nivel_andar")
    private Long idAndar;
    
    public IdSala(){
    }
    
    public IdSala(Long idSala, Long idAndar){
        this.idSala = idSala;
        this.idAndar = idAndar;
    }

    public Long getIdSala() {
        return idSala;
    }

    public void setIdSala(Long idSala) {
        this.idSala = idSala;
    }

    public Long getIdAndar() {
        return idAndar;
    }

    public void setIdAndar(Long idAndar) {
        this.idAndar = idAndar;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.idSala);
        hash = 29 * hash + Objects.hashCode(this.idAndar);
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
        final IdSala other = (IdSala) obj;
        if (!Objects.equals(this.idSala, other.idSala)) {
            return false;
        }
        if (!Objects.equals(this.idAndar, other.idAndar)) {
            return false;
        }
        return true;
    }
    
}
