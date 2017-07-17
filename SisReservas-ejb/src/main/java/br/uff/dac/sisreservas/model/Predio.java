package br.uff.dac.sisreservas.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="predio")
public class Predio implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private IdPredio idPredio;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="id_campus", referencedColumnName = "id_campus", insertable = false, updatable = false)
    private Campus campus;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "predio")
    private List<Andar> andares;

    public IdPredio getIdPredio() {
        return idPredio;
    }

    public void setIdPredio(IdPredio idPredio) {
        this.idPredio = idPredio;
    }

    public String getNome() {
        return this.idPredio.getNome();
    }

    public void setNome(String nome) {
        this.idPredio.setNome(nome);
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public List<Andar> getAndares() {
        return andares;
    }

    public void setAndares(List<Andar> andares) {
        this.andares = andares;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPredio != null ? idPredio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Predio)) {
            return false;
        }
        Predio other = (Predio) object;
        if ((this.idPredio == null && other.idPredio != null) || (this.idPredio != null && !this.idPredio.equals(other.idPredio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.uff.dac.sisreservas.model.Predio[ id=" + idPredio + " ]";
    }
    
}
