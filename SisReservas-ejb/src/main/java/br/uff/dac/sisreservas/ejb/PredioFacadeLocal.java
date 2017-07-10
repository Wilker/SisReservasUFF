/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Predio;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author ElbeMiranda
 */
@Local
public interface PredioFacadeLocal {

    void create(Predio predio);

    void edit(Predio predio);

    void remove(Predio predio);

    Predio find(Object id);

    List<Predio> findAll();

    List<Predio> findRange(int[] range);

    int count();
    
}
