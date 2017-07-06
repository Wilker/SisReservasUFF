package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Campus;
import java.util.List;
import javax.ejb.Local;

@Local
public interface CampusFacadeLocal {

    void create(Campus campus);

    void edit(Campus campus);

    void remove(Campus campus);

    Campus find(Object id);

    List<Campus> findAll();

    List<Campus> findRange(int[] range);

    int count();
    
}
