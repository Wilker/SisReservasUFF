package br.uff.dac.sisreservas.controller;

import br.uff.dac.sisreservas.ejb.UsuarioFacadeLocal;
import br.uff.dac.sisreservas.model.Pessoa;
import br.uff.dac.sisreservas.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

@Named("usuarioController")
@ViewScoped
public class UsuarioController implements Serializable {

    @EJB
    private UsuarioFacadeLocal usuarioEJB;

    private Usuario usuario;

    private List<Usuario> usuarios;

    private Pessoa pessoa;

    @PostConstruct
    public void init() {
        this.usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        this.pessoa = this.usuario.getIdPessoa();
        this.usuario.setIdPessoa(this.pessoa);
        this.usuarios = this.usuarioEJB.findAll();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public boolean isLogado(Usuario user) {
        return this.usuario.getIdPessoa().getIdPessoa().equals(user.getIdPessoa().getIdPessoa());
    }

    public boolean isUsuarioAutenticado() {
        Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
        return user != null;
    }

    public void cadastrarPeloUsuarioExterno() {
        try {
            this.usuario.setTipo('P'); //P = Usuário Padrão
            this.usuarioEJB.create(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Cadastro Realizado!", "Seu cadastro foi efetuado com sucesso e está pendente de aprovação de um dos nossos administradores. Assim que seu cadastro for avaliado você receberá um e-mail com as instruções necessárias para prosseguir."));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao tentar cadastrar Usuário!"));
        }
    }

    public void cadastrarPeloAdministrador() {
        try {
            Usuario foundedUser = this.usuarioEJB.find(this.usuario.getIdPessoa().getIdPessoa());
            if (foundedUser == null) {
                this.criar();
            } else {
                this.editar();
            }
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#dlgCadastroUsuario').modal('close');");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao tentar cadastrar Usuário!"));
        }
    }

    private void criar() {
        try {
            this.usuarioEJB.create(this.usuario);
            this.atualizarTabela();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Cadastro realizado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao tentar cadastrar Usuário!"));
        }
    }

    private void editar() {
        try {
            this.usuarioEJB.edit(this.usuario);
            this.atualizarTabela();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Usuário " + this.usuario.getIdPessoa().getNome() + " editado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao editar Usuário!"));
        }
    }

    public void atualizarTabela() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.usuarios = this.usuarioEJB.findAll();
        requestContext.update(":formTabela:tabela");
    }

    public void excluir(Usuario usuario) {
        try {
            this.usuarioEJB.remove(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Usuário excluído com sucesso!"));
            this.usuarios = this.usuarioEJB.findAll();
            this.atualizarTabela();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao excluir Usuário!"));
        }
    }

    public void exibir(Usuario usuario) {
        this.usuario = usuario;
        this.pessoa = this.usuario.getIdPessoa();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#dlgCadastroUsuario').modal('open');"
                                + "setTimeout(function () {"
                                + "                             $('ul.tabs').tabs('select_tab', 'tabDadosPessoais');"
                                + "                        }, 100);"
                                + "$('#dlgCadastroUsuarioTitulo').text(\"Atualizar Usuário\");");
    }
}
