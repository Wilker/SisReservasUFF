package br.uff.dac.sisreservas.controller;

import br.uff.dac.sisreservas.ejb.CampusFacadeLocal;
import br.uff.dac.sisreservas.model.Campus;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

@Named("campusController")
@ViewScoped
public class CampusController implements Serializable {

    @EJB
    CampusFacadeLocal campusEJB;

    private Campus campus;

    private List<Campus> campi;

    @PostConstruct
    public void init() {
        this.campus = new Campus();
        this.campi = campusEJB.findAll();
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

    public void cadastrar() {
        try {
            campusEJB.create(this.campus);
            this.campi = campusEJB.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Campus " + this.campus.getNome() + " registrado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao cadastrar Campus!"));
        }
    }

    public void editar() {
        try {
            campusEJB.edit(this.campus);
            this.campi = campusEJB.findAll();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Campus " + this.campus.getNome() + " editado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao editar Campus!"));
        }
    }

    public void excluir(Campus campus) {
        try {
            campusEJB.remove(campus);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Campus excluído com sucesso!"));
            this.campi = campusEJB.findAll();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao excluir Campus!"));
        }
    }

    public void exibir(Campus campus) {
        this.campus = campus;
    }

}
