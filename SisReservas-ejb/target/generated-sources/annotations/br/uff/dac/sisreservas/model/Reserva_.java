package br.uff.dac.sisreservas.model;

import br.uff.dac.sisreservas.model.Sala;
import br.uff.dac.sisreservas.model.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-07-13T22:59:22")
@StaticMetamodel(Reserva.class)
public class Reserva_ { 

    public static volatile SingularAttribute<Reserva, Date> horaFim;
    public static volatile ListAttribute<Reserva, Sala> salas;
    public static volatile SingularAttribute<Reserva, Date> dataFim;
    public static volatile SingularAttribute<Reserva, Usuario> usuario;
    public static volatile SingularAttribute<Reserva, Date> dataInicio;
    public static volatile SingularAttribute<Reserva, Long> idReserva;
    public static volatile SingularAttribute<Reserva, Date> horaInicio;
    public static volatile SingularAttribute<Reserva, String> descricao;
    public static volatile SingularAttribute<Reserva, Character> status;

}