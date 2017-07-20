package br.uff.dac.sisreservas.model;

import br.uff.dac.sisreservas.model.Andar;
import br.uff.dac.sisreservas.model.Campus;
import br.uff.dac.sisreservas.model.IdPredio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-20T14:13:39")
@StaticMetamodel(Predio.class)
public class Predio_ { 

    public static volatile SingularAttribute<Predio, IdPredio> idPredio;
    public static volatile SingularAttribute<Predio, Campus> campus;
    public static volatile ListAttribute<Predio, Andar> andares;

}