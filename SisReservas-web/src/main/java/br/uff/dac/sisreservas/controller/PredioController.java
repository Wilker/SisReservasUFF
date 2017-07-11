package br.uff.dac.sisreservas.controller;

import br.uff.dac.sisreservas.ejb.CampusFacadeLocal;
import br.uff.dac.sisreservas.ejb.PredioFacadeLocal;
import br.uff.dac.sisreservas.model.Campus;
import br.uff.dac.sisreservas.model.Predio;
import java.io.IOException;
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

    private Predio predio;

    private List<Predio> predios;

    private List<Predio> prediosFiltrados;

    private Campus campus;

    private List<Campus> campi;

    @PostConstruct
    public void init() {
        this.predio = new Predio();
        this.campus = new Campus();
        this.predio.setCampus(this.campus);

        this.predios = predioEJB.findAll();
        this.prediosFiltrados = this.predios;

        this.campi = campusEJB.findAll();

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

    public List<Campus> getCampi() {
        return campi;
    }

    public void setCampi(List<Campus> campi) {
        this.campi = campi;
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
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao cadastrar Prédio!"));
        }
    }

    private void criar() {
        try {
            this.predioEJB.create(this.predio);
            String nomePredio = this.predio.getNome();
            this.atualizarTabela();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Prédio " + nomePredio + " registrado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao tentar cadastrar Prédio!"));
        }
    }

    private void editar() {
        try {
            predioEJB.edit(this.predio);
            this.atualizarTabela();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Prédio " + this.predio.getNome() + " editado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao editar Prédio!"));
        }
    }

    private void atualizarTabela() {
        this.init();
        try {
            //RequestContext requestContext = RequestContext.getCurrentInstance();
            //requestContext.update(":formTabela:tabela");
            //Com Ajax não funcionou, a saída foi atualizar toda a página.
            FacesContext.getCurrentInstance().getExternalContext().redirect("./predio.sis");
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao atualizar tabela de Prédios!"));
        }
    }

    public void excluir(Predio predio) {
        try {
            this.predioEJB.remove(predio);
            this.atualizarTabela();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Prédio excluído com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao excluir Prédio!"));
        }
    }

    public void exibir(Predio predio) {
        this.predio = predio;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#dlgCadastroPredio').modal('open');"
                + "$('#dlgCadastroPredioTitulo').text(\"Atualizar Prédio\");");
    }
}
