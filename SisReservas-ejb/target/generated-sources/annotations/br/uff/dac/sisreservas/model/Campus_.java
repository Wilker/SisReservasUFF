package br.uff.dac.sisreservas.model;

import br.uff.dac.sisreservas.model.Predio;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-13T22:59:23")
@StaticMetamodel(Campus.class)
public class Campus_ { 

    public static volatile SingularAttribute<Campus, Long> idCampus;
    public static volatile SingularAttribute<Campus, String> endereco;
    public static volatile SingularAttribute<Campus, String> nome;
    public static volatile ListAttribute<Campus, Predio> predios;

}