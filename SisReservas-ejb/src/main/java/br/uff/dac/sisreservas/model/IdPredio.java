package br.uff.dac.sisreservas.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class IdPredio implements Serializable {
    
    @Column(name = "nome")
    private String nome;

    @Column(name = "id_campus")
    private Long idCampus;

    public IdPredio() {
    }

    public IdPredio(Long idCampus, String nomePredio) {
        this.idCampus = idCampus;
        this.nome = nomePredio;
    }

    public Long getIdCampus() {
        return idCampus;
    }

    public void setIdCampus(Long idCampus) {
        this.idCampus = idCampus;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.nome);
        hash = 83 * hash + Objects.hashCode(this.idCampus);
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
        final IdPredio other = (IdPredio) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.idCampus, other.idCampus)) {
            return false;
        }
        return true;
    }
    

}
