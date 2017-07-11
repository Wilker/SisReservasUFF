package br.uff.dac.sisreservas.model;

import br.uff.dac.sisreservas.model.Sala;
import br.uff.dac.sisreservas.model.TipoRecurso;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-11T08:41:34")
@StaticMetamodel(Recurso.class)
public class Recurso_ { 

    public static volatile SingularAttribute<Recurso, TipoRecurso> tipo;
    public static volatile SingularAttribute<Recurso, Sala> sala;
    public static volatile SingularAttribute<Recurso, Long> idRecurso;
    public static volatile SingularAttribute<Recurso, Integer> quantidade;

}