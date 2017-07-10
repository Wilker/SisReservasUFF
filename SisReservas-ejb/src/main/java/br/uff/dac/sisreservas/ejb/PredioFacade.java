package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Predio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PredioFacade extends AbstractFacade<Predio> implements PredioFacadeLocal {
    @PersistenceContext(unitName = "br.uff.dac.sisreservas_unidade_persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PredioFacade() {
        super(Predio.class);
    }
    
}
