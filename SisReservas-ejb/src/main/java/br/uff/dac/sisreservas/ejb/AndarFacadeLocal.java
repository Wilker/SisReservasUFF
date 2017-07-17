/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Andar;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ElbeMiranda
 */
@Local
public interface AndarFacadeLocal {

    void create(Andar andar);

    void edit(Andar andar);

    void remove(Andar andar);

    Andar find(Object id);

    List<Andar> findAll();

    List<Andar> findRange(int[] range);

    int count();
    
}
