package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Menu;
import java.util.List;
import javax.ejb.Local;

@Local
public interface MenuFacadeLocal {

    void create(Menu menu);

    void edit(Menu menu);

    void remove(Menu menu);

    Menu find(Object id);

    List<Menu> findAll();

    List<Menu> findRange(int[] range);

    int count();
    
}
