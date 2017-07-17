package br.uff.dac.sisreservas.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sala")
public class Sala implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    private IdSala idSala;

    @Column(name="nome")
    private String nome;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="nivel_andar", referencedColumnName = "nivel_andar", insertable = false, updatable = false),
        @JoinColumn(name="id_predio", referencedColumnName = "id_predio" , insertable = false, updatable = false)
    })
    private Andar andar;

    @ManyToOne
    private Reserva reserva;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sala")
    private List<Recurso> recursos;

    public IdSala getIdSala() {
        return idSala;
    }

    public void setIdSala(IdSala idSala) {
        this.idSala = idSala;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public Andar getAndar() {
        return andar;
    }

    public void setAndar(Andar andar) {
        this.andar = andar;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSala != null ? idSala.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sala)) {
            return false;
        }
        Sala other = (Sala) object;
        if ((this.idSala == null && other.idSala != null) || (this.idSala != null && !this.idSala.equals(other.idSala))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.uff.dac.sisreservas.model.Sala[ id=" + idSala + " ]";
    }

}
