package br.uff.dac.sisreservas.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="andar")
public class Andar implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAndar;
    
    @Column(name="nivel")
    private int nivel;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Predio predio;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "andar", fetch = FetchType.LAZY)
    private List<Sala> salas;
    
    
    public Long getIdAndar() {
        return idAndar;
    }

    public void setIdAndar(Long idAndar) {
        this.idAndar = idAndar;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
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
        int hash = 0;
        hash += (idAndar != null ? idAndar.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Andar)) {
            return false;
        }
        Andar other = (Andar) object;
        if ((this.idAndar == null && other.idAndar != null) || (this.idAndar != null && !this.idAndar.equals(other.idAndar))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.uff.dac.sisreservas.model.Andar[ id=" + idAndar + " ]";
    }
    
}
