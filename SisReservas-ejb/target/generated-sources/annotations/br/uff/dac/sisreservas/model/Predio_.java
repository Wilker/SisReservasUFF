package br.uff.dac.sisreservas.model;

import br.uff.dac.sisreservas.model.Andar;
import br.uff.dac.sisreservas.model.Campus;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-12T16:04:40")
@StaticMetamodel(Predio.class)
public class Predio_ { 

    public static volatile SingularAttribute<Predio, Long> idPredio;
    public static volatile SingularAttribute<Predio, Campus> campus;
    public static volatile ListAttribute<Predio, Andar> andares;
    public static volatile SingularAttribute<Predio, String> nome;

}