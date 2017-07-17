package br.uff.dac.sisreservas.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdAndar implements Serializable{

    @Column(name = "nivel_andar")
    private int nivel;
    
    @Column(name="id_predio")
    private Long idPredio;
    
    public IdAndar(){
    }
    
    public IdAndar(int nivel, Long idPredio){
        this.nivel=nivel;
        this.idPredio=idPredio;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public Long getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(Long idPredio) {
        this.idPredio = idPredio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.nivel;
        hash = 97 * hash + Objects.hashCode(this.idPredio);
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
        final IdAndar other = (IdAndar) obj;
        if (this.nivel != other.nivel) {
            return false;
        }
        if (!Objects.equals(this.idPredio, other.idPredio)) {
            return false;
        }
        return true;
    }
    
}
