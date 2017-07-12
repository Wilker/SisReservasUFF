package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Andar;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AndarFacade extends AbstractFacade<Andar> implements AndarFacadeLocal {
    @PersistenceContext(unitName = "br.uff.dac.sisreservas_unidade_persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AndarFacade() {
        super(Andar.class);
    }
    
}
