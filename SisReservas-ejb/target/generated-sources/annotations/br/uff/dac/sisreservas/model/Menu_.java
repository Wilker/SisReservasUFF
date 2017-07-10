package br.uff.dac.sisreservas.model;

import br.uff.dac.sisreservas.model.Menu;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-09T18:25:43")
@StaticMetamodel(Menu.class)
public class Menu_ { 

    public static volatile SingularAttribute<Menu, Character> tipo;
    public static volatile SingularAttribute<Menu, Menu> subMenu;
    public static volatile SingularAttribute<Menu, String> nome;
    public static volatile SingularAttribute<Menu, Character> tipoUsuario;
    public static volatile SingularAttribute<Menu, Short> id;
    public static volatile SingularAttribute<Menu, String> url;
    public static volatile SingularAttribute<Menu, Boolean> status;

}