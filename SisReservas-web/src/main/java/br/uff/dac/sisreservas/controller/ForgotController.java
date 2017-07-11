package br.uff.dac.sisreservas.controller;

import br.uff.dac.sisreservas.ejb.UsuarioFacadeLocal;
import br.uff.dac.sisreservas.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named(value = "forgotController")
@ViewScoped
public class ForgotController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    private Usuario usuario;
    
    @PostConstruct
    public void init() {
        this.usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void recuperarSenha(){
    
        if (this.usuarioEJB.emailExiste(this.usuario)){
            //TODO Recuperar Senha
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Recuperar Senha!", "Um e-mail de recuperação de senha foi enviado para o e-mail informado."));
        }else{
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Recuperar Senha!", "O e-mail informado não existe!"));        
        }
        
    }
    
}
