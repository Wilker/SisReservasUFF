package br.uff.dac.sisreservas.controller;

import br.uff.dac.sisreservas.ejb.AndarFacadeLocal;
import br.uff.dac.sisreservas.ejb.CampusFacadeLocal;
import br.uff.dac.sisreservas.ejb.PredioFacadeLocal;
import br.uff.dac.sisreservas.ejb.SalaFacadeLocal;
import br.uff.dac.sisreservas.model.Andar;
import br.uff.dac.sisreservas.model.Campus;
import br.uff.dac.sisreservas.model.Predio;
import br.uff.dac.sisreservas.model.Sala;
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

@Named(value = "salaController")
@ViewScoped
public class SalaController implements Serializable {

    @EJB
    SalaFacadeLocal salaEJB;

    @EJB
    AndarFacadeLocal andarEJB;

    @EJB
    PredioFacadeLocal predioEJB;

    @EJB
    CampusFacadeLocal campusEJB;

    private Sala sala;
    private List<Sala> salas;
    private List<Sala> salasFiltradas;

    private Andar andar;
    private List<Andar> andares;

    private Predio predio;
    private List<Predio> predios;

    private Campus campus;
    private List<Campus> campi;

    @PostConstruct
    public void init() {
        this.sala = new Sala();
        this.andar = new Andar();
        this.predio = new Predio();
        this.campus = new Campus();

        this.sala.setAndar(this.andar);
        this.andar.setPredio(this.predio);
        this.predio.setCampus(this.campus);

        this.salas = salaEJB.findAll();
        this.salasFiltradas = this.salas;
        this.andares = andarEJB.findAll();
        this.predios = predioEJB.findAll();
        this.campi = campusEJB.findAll();
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public List<Sala> getSalas() {
        return salas;
    }

    public void setSalas(List<Sala> salas) {
        this.salas = salas;
    }

    public List<Sala> getSalasFiltradas() {
        return salasFiltradas;
    }

    public void setSalasFiltradas(List<Sala> salasFiltradas) {
        this.salasFiltradas = salasFiltradas;
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

    public List<Andar> getAndaresFiltrados() {
        List<Andar> auxAndares = new ArrayList<>();
        for (Andar a : this.andarEJB.findAll()) {
            for (Sala s : this.salas) {
                if (s.getAndar().getIdAndar().equals(a.getIdAndar())) {
                    if (!auxAndares.contains(a)) {
                        auxAndares.add(a);
                    }
                }
            }
        }
        return auxAndares;
    }

    public List<Predio> getPredios() {
        return predios;
    }

    public void setPredios(List<Predio> predios) {
        this.predios = predios;
    }

    public List<Predio> getPrediosFiltrados() {
        List<Predio> auxPredios = new ArrayList<>();
        for (Predio p : this.predioEJB.findAll()) {
            for (Sala s : this.salas) {
                if (s.getAndar().getPredio().getIdPredio().equals(p.getIdPredio())) {
                    if (!auxPredios.contains(p)) {
                        auxPredios.add(p);
                    }
                }
            }
        }
        return auxPredios;
    }

    public List<Campus> getCampi() {
        return campi;
    }

    public List<Campus> getCampiFiltrados() {
        List<Campus> auxCampi = new ArrayList<>();
        for (Campus c : this.campi) {
            for (Sala s : this.salas) {
                if (s.getAndar().getPredio().getCampus().getIdCampus().equals(c.getIdCampus())) {
                    if (!auxCampi.contains(c)) {
                        auxCampi.add(c);
                    }
                }
            }
        }
        return auxCampi;
    }

    public void setCampi(List<Campus> campi) {
        this.campi = campi;
    }

    public void cadastrar() {
        try {
            Sala foundedSala = null;
            if (this.sala.getIdSala() != null) {
                foundedSala = this.salaEJB.find(this.sala.getIdSala());
            }

            if (foundedSala == null) {
                this.criar();
            } else {
                this.editar();
            }
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#dlgCadastroSala').modal('close');");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao cadastrar Sala!"));
        }
    }

    private void criar() {
        try {
            this.salaEJB.create(this.sala);
            this.atualizarTabela();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Sala registrada com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao tentar cadastrar Sala!"));
        }
    }

    private void editar() {
        try {
            salaEJB.edit(this.sala);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Sala editada com sucesso!"));
            atualizarTabela();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao editar Sala!"));
        }
    }

    public void atualizarTabela() {
        this.init();
        try {
            //RequestContext requestContext = RequestContext.getCurrentInstance();
            //requestContext.update(":formTabela:tabela");
            //Com Ajax não funcionou, a saída foi atualizar toda a página.
            FacesContext.getCurrentInstance().getExternalContext().redirect("./sala.sis");
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao atualizar tabela de Salas!"));
        }
    }

    public void excluir(Andar andar) {
        try {
            salaEJB.remove(sala);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Sala excluída com sucesso!"));
            atualizarTabela();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao excluir Sala!"));
        }
    }

    public void exibir(Sala sala) {
        this.sala = sala;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#dlgCadastroSala').modal('open');"
                + "$('#dlgCadastroSalaTitulo').text(\"Atualizar Sala\");");
    }

    public void filtrarPredios() {
        this.predios = new ArrayList<>();
        for (Predio p : this.predioEJB.findAll()) {
            if (p.getCampus().getIdCampus().equals(this.campus.getIdCampus())) {
                if(p.getAndares().size()>0)
                this.predios.add(p);
            }
        }
    }

    public void filtrarAndares() {
        this.andares = new ArrayList<>();
        for (Andar a : this.andarEJB.findAll()) {
            if (a.getPredio().getIdPredio().equals(this.predio.getIdPredio())) {
                this.andares.add(a);
            }
        }
    }

}
