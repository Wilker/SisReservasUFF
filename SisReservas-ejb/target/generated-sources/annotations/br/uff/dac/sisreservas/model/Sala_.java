package br.uff.dac.sisreservas.model;

import br.uff.dac.sisreservas.model.Andar;
import br.uff.dac.sisreservas.model.IdSala;
import br.uff.dac.sisreservas.model.Recurso;
import br.uff.dac.sisreservas.model.Reserva;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-20T14:13:39")
@StaticMetamodel(Sala.class)
public class Sala_ { 

    public static volatile SingularAttribute<Sala, Andar> andar;
    public static volatile SingularAttribute<Sala, String> nome;
    public static volatile SingularAttribute<Sala, IdSala> idSala;
    public static volatile ListAttribute<Sala, Recurso> recursos;
    public static volatile SingularAttribute<Sala, Reserva> reserva;

}