/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Recurso;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ElbeMiranda
 */
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
