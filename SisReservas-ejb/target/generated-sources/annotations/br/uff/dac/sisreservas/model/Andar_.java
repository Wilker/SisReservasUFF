package br.uff.dac.sisreservas.model;

import br.uff.dac.sisreservas.model.IdAndar;
import br.uff.dac.sisreservas.model.Predio;
import br.uff.dac.sisreservas.model.Sala;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-20T14:13:39")
@StaticMetamodel(Andar.class)
public class Andar_ { 

    public static volatile ListAttribute<Andar, Sala> salas;
    public static volatile SingularAttribute<Andar, Predio> predio;
    public static volatile SingularAttribute<Andar, IdAndar> idAndar;

}