package br.uff.dac.sisreservas.controller;

import br.uff.dac.sisreservas.ejb.AndarFacadeLocal;
import br.uff.dac.sisreservas.ejb.CampusFacadeLocal;
import br.uff.dac.sisreservas.ejb.PredioFacadeLocal;
import br.uff.dac.sisreservas.model.Andar;
import br.uff.dac.sisreservas.model.Campus;
import br.uff.dac.sisreservas.model.Predio;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.context.RequestContext;

@Named(value = "andarController")
@ViewScoped
public class AndarController implements Serializable {

    @PersistenceContext(unitName = "br.uff.dac.sisreservas_unidade_persistencia")
    private EntityManager em;

    @EJB
    private CampusFacadeLocal campusEJB;
    private List<Campus> campi;
    private Campus campus;

    @EJB
    private PredioFacadeLocal predioEJB;
    private List<Predio> predios;
    private Predio predio;

    @EJB
    private AndarFacadeLocal andarEJB;
    private List<Andar> andares;
    private List<Andar> andaresFiltrados;
    private Andar andar;

    @PostConstruct
    public void init() {
        this.campi = campusEJB.findAll();
        this.campus = new Campus();
        this.predios = predioEJB.findAll();
        this.predio = new Predio();
        this.andares = andarEJB.findAll();
        this.andar = new Andar();
    }

    public List<Campus> getCampi() {
        return campi;
    }

    public void setCampi(List<Campus> campi) {
        this.campi = campi;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public List<Predio> getPredios() {
        return predios;
    }

    public void setPredios(List<Predio> predios) {
        this.predios = predios;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public List<Andar> getAndares() {
        return andares;
    }

    public void setAndares(List<Andar> andares) {
        this.andares = andares;
    }

    public List<Andar> getAndaresFiltrados() {
        return andaresFiltrados;
    }

    public void setAndaresFiltrados(List<Andar> andaresFiltrados) {
        this.andaresFiltrados = andaresFiltrados;
    }
    
    public Andar getAndar() {
        return andar;
    }

    public void setAndar(Andar andar) {
        this.andar = andar;
    }

    public void cadastrar() {
        try {
            Andar foundedAndar = null;
            if (this.andar.getIdAndar() != null) {
                foundedAndar = this.andarEJB.find(this.andar.getIdAndar());
            }

            if (foundedAndar == null) {
                this.criar();
            } else {
                this.editar();
            }
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#dlgCadastroAndar').modal('close');");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao cadastrar Andar!"));
        }
    }

    private void criar() {
        try {
            this.andar.setPredio(this.predio);
            this.andarEJB.create(this.andar);
            this.atualizarTabela();

            String nomeAndar = Integer.toString(this.andar.getNivel()) + " do Prédio " + this.predio.getNome() + "(Campus " + this.campus.getNome() + " ).";

            this.campus = new Campus();
            this.predio = new Predio();
            this.andar = new Andar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Andar " + nomeAndar + " registrado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao tentar cadastrar Andar!"));
        }
    }

    private void editar() {
        try {
            andarEJB.edit(this.andar);
            String nomeAndar = Integer.toString(this.andar.getNivel()) + " do Prédio " + this.predio.getNome() + "(Campus " + this.campus.getNome() + " ).";
            this.andares = andarEJB.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Andar " + nomeAndar + " editado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao editar Andar!"));
        }
    }

    public void atualizarTabela() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.campi = campusEJB.findAll();
        this.predios = predioEJB.findAll();
        this.andares = andarEJB.findAll();
        requestContext.update("formTabela:tabela");
    }

    public void excluir(Andar andar) {
        try {
            andarEJB.remove(andar);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Andar excluído com sucesso!"));
            atualizarTabela();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao excluir Andar!"));
        }
    }

    public void exibir(Andar andar) {
        this.andar = andar;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#dlgCadastroAndar').modal('open');"
                + "$('#dlgCadastroAndarTitulo').text(\"Atualizar Andar\");");
    }

    public void buscarPredios(AjaxBehaviorEvent abe) {
        try {
            this.predios = this.andarEJB.buscarPredios(this.campus.getIdCampus());
        } catch (Exception ex) {
            RequestContext.getCurrentInstance().execute("alert("+ex.getMessage()+")");
//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao buscar prédios!"));
        }

    }

}
