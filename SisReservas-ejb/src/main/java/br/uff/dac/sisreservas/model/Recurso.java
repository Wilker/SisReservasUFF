package br.uff.dac.sisreservas.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="recurso")
public class Recurso implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private IdRecurso idRecurso;

    @Enumerated(EnumType.STRING)
    private TipoRecurso tipo;
    
    @Column(name="quantidade")
    private int quantidade;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name="id_sala", referencedColumnName = "id_sala", insertable = false, updatable = false),
        @JoinColumn(name="id_andar", referencedColumnName = "id_andar", insertable = false, updatable = false)
    })
    private Sala sala;
    
    public IdRecurso getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(IdRecurso idRecurso) {
        this.idRecurso = idRecurso;
    }

    public TipoRecurso getTipo() {
        return tipo;
    }

    public void setTipo(TipoRecurso tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRecurso != null ? idRecurso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recurso)) {
            return false;
        }
        Recurso other = (Recurso) object;
        if ((this.idRecurso == null && other.idRecurso != null) || (this.idRecurso != null && !this.idRecurso.equals(other.idRecurso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.uff.dac.sisreservas.model.Recurso[ id=" + idRecurso + " ]";
    }
    
}
