package br.uff.dac.sisreservas.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "andar")
public class Andar implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private IdAndar idAndar;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "id_campus", referencedColumnName = "id_campus", insertable = false, updatable = false),
        @JoinColumn(name = "nome_predio", referencedColumnName = "nome_predio", insertable = false, updatable = false)
    })
    private Predio predio;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "andar")
    private List<Sala> salas;

    public IdAndar getIdAndar() {
        return idAndar;
    }

    public void setIdAndar(IdAndar idAndar) {
        this.idAndar = idAndar;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.idAndar);
        hash = 23 * hash + Objects.hashCode(this.predio);
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
        final Andar other = (Andar) obj;
        if (!Objects.equals(this.idAndar, other.idAndar)) {
            return false;
        }
        if (!Objects.equals(this.predio, other.predio)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Andar{" + "idAndar=" + idAndar + ", predio=" + predio + '}';
    }

}
