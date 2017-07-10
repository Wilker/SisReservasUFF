package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Andar;
import br.uff.dac.sisreservas.model.Predio;
import java.util.List;
import javax.ejb.Local;

@Local
public interface AndarFacadeLocal {

    void create(Andar andar);

    void edit(Andar andar);

    void remove(Andar andar);

    Andar find(Object id);

    List<Andar> findAll();

    List<Andar> findRange(int[] range);

    int count();
    
    List<Predio> buscarPredios(Long idCampus) throws Exception ;
}
