package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Sala;
import java.util.List;
import javax.ejb.Local;

@Local
public interface SalaFacadeLocal {

    void create(Sala sala);

    void edit(Sala sala);

    void remove(Sala sala);

    Sala find(Object id);

    List<Sala> findAll();

    List<Sala> findRange(int[] range);

    int count();
    
}
