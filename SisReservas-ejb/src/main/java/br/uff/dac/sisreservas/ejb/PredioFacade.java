/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Predio;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ElbeMiranda
 */
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
