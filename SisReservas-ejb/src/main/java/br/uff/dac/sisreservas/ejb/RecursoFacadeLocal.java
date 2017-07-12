package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Recurso;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RecursoFacadeLocal {

    void create(Recurso recurso);

    void edit(Recurso recurso);

    void remove(Recurso recurso);

    Recurso find(Object id);

    List<Recurso> findAll();

    List<Recurso> findRange(int[] range);

    int count();
    
}
