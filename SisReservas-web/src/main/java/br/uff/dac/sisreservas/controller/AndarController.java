package br.uff.dac.sisreservas.controller;

import br.uff.dac.sisreservas.ejb.AndarFacadeLocal;
import br.uff.dac.sisreservas.ejb.CampusFacadeLocal;
import br.uff.dac.sisreservas.ejb.PredioFacadeLocal;
import br.uff.dac.sisreservas.model.Andar;
import br.uff.dac.sisreservas.model.Campus;
import br.uff.dac.sisreservas.model.Predio;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;

@Named(value = "andarController")
@ViewScoped
public class AndarController implements Serializable {

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
        this.campus = new Campus();
        this.predio = new Predio();
        this.andar = new Andar();
        this.andar.setPredio(this.predio);

        this.campi = campusEJB.findAll();
        this.predios = predioEJB.findAll();
        this.andares = andarEJB.findAll();
        this.andaresFiltrados = this.andares;
    }

    public List<Campus> getFilteredCampi() {
        List<Campus> auxCampi = new ArrayList<>();
        for (Campus c : this.campi) {
            for (Andar a : this.andares) {
                if (a.getPredio().getCampus().getIdCampus().equals(c.getIdCampus())) {
                    if (!auxCampi.contains(c)) {
                        auxCampi.add(c);
                    }
                }
            }
        }
        return auxCampi;
    }

    public List<Campus> getCampi() {
        return this.campi;
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
        List<Predio> auxPredios = new ArrayList<>();
        for (Predio p : this.predios) {
            for (Andar a : this.andares) {
                if (a.getPredio().getIdPredio().equals(p.getIdPredio())) {
                    if (!auxPredios.contains(p)) {
                        auxPredios.add(p);
                    }
                }
            }
        }
        return auxPredios;
    }

    public void setPredios(List<Predio> predios) {
        this.predios = predios;
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
            this.andarEJB.create(this.andar);
            this.atualizarTabela();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Andar registrado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao tentar cadastrar Andar!"));
        }
    }

    private void editar() {
        try {
            andarEJB.edit(this.andar);
            this.andares = andarEJB.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Andar editado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao editar Andar!"));
        }
    }

    public void atualizarTabela() {
        this.init();
        try {
            //RequestContext requestContext = RequestContext.getCurrentInstance();
            //requestContext.update(":formTabela:tabela");
            //Com Ajax não funcionou, a saída foi atualizar toda a página.
            FacesContext.getCurrentInstance().getExternalContext().redirect("./andar.sis");
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao atualizar tabela de Andares!"));
        }
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

    public void filtrarPredios() {
        this.predios = this.predioEJB.findAll();
        this.predios.clear();
        for (Predio p : this.predioEJB.findAll()) {
            if (p.getCampus().getIdCampus().equals(this.campus.getIdCampus())) {
                this.predios.add(p);
            }
        }
    }

}
