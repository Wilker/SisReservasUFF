package br.uff.dac.sisreservas.controller;

import br.uff.dac.sisreservas.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("templateController")
@ViewScoped
public class TemplateController implements Serializable {

    private Usuario usuario;
    
    private String nomeUsuario;
    
    @PostConstruct
    public void init(){
        this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.nomeUsuario = this.usuario.getIdUsuario().getNome().split(" ")[0];
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public void verificarLogin() {
        try {
            ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
            Usuario usuario = (Usuario) contexto.getSessionMap().get("usuario");
            
            if (usuario == null){
                contexto.redirect("./../usuario.xhtml");
            }
        } catch (Exception ex) {
            // Log 
        }
    }

    
    
}