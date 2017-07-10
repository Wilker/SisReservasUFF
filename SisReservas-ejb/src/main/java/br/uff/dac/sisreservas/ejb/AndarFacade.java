package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Andar;
import br.uff.dac.sisreservas.model.Predio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    public List<Predio> buscarPredios(Long idCampus) throws Exception {
        try{
            String jpql = "FROM Predio p WHERE p.campus.idCampus = ?1";
            Query query = em.createQuery(jpql);
            query.setParameter(1, idCampus);
            return query.getResultList();
        }catch(Exception ex){
            throw ex;
        } 
    }
    
}
