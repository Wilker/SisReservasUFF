package br.uff.dac.sisreservas.controller;

import br.uff.dac.sisreservas.ejb.UsuarioFacadeLocal;
import br.uff.dac.sisreservas.model.Pessoa;
import br.uff.dac.sisreservas.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("loginController")
@ViewScoped
public class LoginController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    private Usuario usuario;

    private Pessoa pessoa;

    @PostConstruct
    public void init() {
        this.usuario = new Usuario();
        this.pessoa = new Pessoa();
        this.usuario.setIdPessoa(this.pessoa);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public void cadastrar() {
        try {
            this.usuario.setTipo('P'); //P = Usuário Padrão
            this.usuarioEJB.create(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro Realizado!", "Seu cadastro foi efetuado com sucesso e está pendente de aprovação de um dos nossos administradores. Assim que seu cadastro for avaliado você receberá um e-mail com as instruções necessárias para prosseguir."));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao tentar cadastrar Usuário!"));
        }
    }

    public void verificarLogin() {
        try {
            ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
            Usuario usuario = (Usuario) contexto.getSessionMap().get("usuario");

            if (usuario != null) {
                contexto.redirect("./protegido/principal.sis");
            }
        } catch (Exception ex) {
            // Log 
        }
    }

    public String login() {
        String redirecionamento = null;
        try {
            Usuario usuarioLogin = usuarioEJB.Login(this.usuario);
            this.usuario = usuarioLogin;
            if (usuarioLogin != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuarioLogin);
                redirecionamento = "/protegido/principal?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso!", "Usuário ou senha incorretos!"));
            }
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao efetuar o login!"));
        }
        return redirecionamento;
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("./../index.sis");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
