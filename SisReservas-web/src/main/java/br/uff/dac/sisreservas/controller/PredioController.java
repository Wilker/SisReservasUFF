package br.uff.dac.sisreservas.controller;

import br.uff.dac.sisreservas.ejb.AndarFacadeLocal;
import br.uff.dac.sisreservas.ejb.CampusFacadeLocal;
import br.uff.dac.sisreservas.ejb.PredioFacadeLocal;
import br.uff.dac.sisreservas.model.Andar;
import br.uff.dac.sisreservas.model.Campus;
import br.uff.dac.sisreservas.model.Predio;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

@Named(value = "predioController")
@ViewScoped
public class PredioController implements Serializable {

    @EJB
    PredioFacadeLocal predioEJB;

    @EJB
    CampusFacadeLocal campusEJB;

    @EJB
    AndarFacadeLocal andarEJB;

    private Predio predio;

    private List<Predio> predios;

    private List<Predio> prediosFiltrados;

    private Campus campus;

    private List<Campus> campi;

    private Andar andar;

    private List<Andar> andares;

    @PostConstruct
    public void init() {
        this.predio = new Predio();
        this.campus = new Campus();
        this.predios = predioEJB.findAll();
        this.prediosFiltrados = this.predios;
        this.campi = campusEJB.findAll();
        this.andar = new Andar();
        this.andares = this.predio.getAndares();
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public List<Predio> getPredios() {
        return predios;
    }

    public void setPredios(List<Predio> predios) {
        this.predios = predios;
    }

    public List<Predio> getPrediosFiltrados() {
        return prediosFiltrados;
    }

    public void setPrediosFiltrados(List<Predio> prediosFiltrados) {
        this.prediosFiltrados = prediosFiltrados;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }

    public List<Campus> getCampi() {
        return campi;
    }

    public void setCampi(List<Campus> campi) {
        this.campi = campi;
    }

    public Andar getAndar() {
        return andar;
    }

    public void setAndar(Andar andar) {
        this.andar = andar;
    }

    public List<Andar> getAndares() {
        return andares;
    }

    public void setAndares(List<Andar> andares) {
        this.andares = andares;
    }

    public void cadastrar() {
        try {
            Predio foundedPredio = null;
            if (this.predio.getIdPredio() != null) {
                foundedPredio = this.predioEJB.find(this.predio.getIdPredio());
            }

            if (foundedPredio == null) {
                this.criar();
            } else {
                this.editar();
            }
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#dlgCadastroPredio').modal('close');");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao cadastrar Predio!"));
        }
    }
    
      public void cadastrarAndar() {
        try {
            this.andar.setPredio(this.predio);
            this.andarEJB.create(this.andar);
            this.predio.getAndares().add(this.andar);
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#dlgCadastroAndar').modal('close');");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Andar registrado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao cadastrar Andar!"));
        }
    }

    private void criar() {
        try {
            this.predio.setCampus(this.campus);
            this.predioEJB.create(this.predio);
            this.atualizarTabela();

            String nomePredio = this.predio.getNome();
            this.predio = new Predio();
            this.campus = new Campus();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Predio " + nomePredio + " registrado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao tentar cadastrar Predio!"));
        }
    }

    private void editar() {
        try {
            predioEJB.edit(this.predio);
            this.predios = predioEJB.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Predio " + this.predio.getNome() + " editado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao editar Predio!"));
        }
    }

    public void atualizarTabela() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        this.predios = this.predioEJB.findAll();
        requestContext.update("formTabela:tabela");
    }

    public void excluir(Predio predio) {
        try {
            this.predioEJB.remove(predio);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Predio excluído com sucesso!"));
            atualizarTabela();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao excluir Predio!"));
        }
    }

    public void exibir(Predio predio) {
        this.predio = predio;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#dlgCadastroPredio').modal('open');"
                + "$('#dlgCadastroPredioTitulo').text(\"Atualizar Prédio\");");
    }

    public void exibirAndar(Predio predio) {
        this.predio = predio;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#dlgCadastroAndar').modal('open');");
    }
}
