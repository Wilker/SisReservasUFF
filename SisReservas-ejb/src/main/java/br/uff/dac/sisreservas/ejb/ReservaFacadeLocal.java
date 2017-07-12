package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Reserva;
import java.util.List;
import javax.ejb.Local;

@Local
public interface ReservaFacadeLocal {

    void create(Reserva reserva);

    void edit(Reserva reserva);

    void remove(Reserva reserva);

    Reserva find(Object id);

    List<Reserva> findAll();

    List<Reserva> findRange(int[] range);

    int count();
    
}
