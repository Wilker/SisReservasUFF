package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Campus;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CampusFacade extends AbstractFacade<Campus> implements CampusFacadeLocal {
    @PersistenceContext(unitName = "br.uff.dac.sisreservas_unidade_persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CampusFacade() {
        super(Campus.class);
    }
    
}
