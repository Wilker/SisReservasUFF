package br.uff.dac.sisreservas.model;

import br.uff.dac.sisreservas.model.Pessoa;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-13T22:59:22")
@StaticMetamodel(Usuario.class)
public class Usuario_ { 

    public static volatile SingularAttribute<Usuario, Pessoa> idPessoa;
    public static volatile SingularAttribute<Usuario, String> senha;
    public static volatile SingularAttribute<Usuario, Character> tipo;
    public static volatile SingularAttribute<Usuario, String> email;

}