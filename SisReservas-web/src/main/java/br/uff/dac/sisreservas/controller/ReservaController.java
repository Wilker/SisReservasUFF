package br.uff.dac.sisreservas.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "reservaController")
@ViewScoped
public class ReservaController implements Serializable {

    @PostConstruct
    public void init(){
    }
    
}
