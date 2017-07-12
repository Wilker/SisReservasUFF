package br.uff.dac.sisreservas.controller;

import br.uff.dac.sisreservas.ejb.AndarFacadeLocal;
import br.uff.dac.sisreservas.ejb.CampusFacadeLocal;
import br.uff.dac.sisreservas.ejb.PredioFacadeLocal;
import br.uff.dac.sisreservas.ejb.RecursoFacadeLocal;
import br.uff.dac.sisreservas.ejb.SalaFacadeLocal;
import br.uff.dac.sisreservas.model.Andar;
import br.uff.dac.sisreservas.model.Campus;
import br.uff.dac.sisreservas.model.Predio;
import br.uff.dac.sisreservas.model.Recurso;
import br.uff.dac.sisreservas.model.Sala;
import br.uff.dac.sisreservas.model.TipoRecurso;
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

@Named(value = "recursoController")
@ViewScoped
public class RecursoController implements Serializable {

    @EJB
    RecursoFacadeLocal recursoEJB;

    @EJB
    SalaFacadeLocal salaEJB;

    @EJB
    AndarFacadeLocal andarEJB;

    @EJB
    PredioFacadeLocal predioEJB;

    @EJB
    CampusFacadeLocal campusEJB;

    private TipoRecurso[] tiposRecurso;
    
    private Recurso recurso;
    private List<Recurso> recursos;
    private List<Recurso> recursosFiltrados;

    private Sala sala;
    private List<Sala> salas;

    private Andar andar;
    private List<Andar> andares;

    private Predio predio;
    private List<Predio> predios;

    private Campus campus;
    private List<Campus> campi;

    @PostConstruct
    public void init() {
        this.recurso = new Recurso();
        this.sala = new Sala();
        this.andar = new Andar();
        this.predio = new Predio();
        this.campus = new Campus();

        this.recurso.setSala(this.sala);
        this.sala.setAndar(this.andar);
        this.andar.setPredio(this.predio);
        this.predio.setCampus(this.campus);

        this.tiposRecurso = TipoRecurso.values();
        this.recursos = recursoEJB.findAll();
        this.salas = salaEJB.findAll();
        this.andares = andarEJB.findAll();
        this.predios = predioEJB.findAll();
        this.campi = campusEJB.findAll();
    }

    public TipoRecurso[] getTiposRecurso() {
        return tiposRecurso;
    }

    public void setTiposRecurso(TipoRecurso[] tiposRecurso) {
        this.tiposRecurso = tiposRecurso;
    }
   
    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public List<Recurso> getRecursos() {
        return recursos;
    }

    public void setRecursos(List<Recurso> recursos) {
        this.recursos = recursos;
    }

    public List<Recurso> getRecursosFiltrados() {
        return recursosFiltrados;
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
        List<Sala> auxSalas = new ArrayList<>();
        for (Sala s : this.salaEJB.findAll()) {
            for (Recurso r : this.recursos) {
                if (r.getSala().getIdSala().equals(s.getIdSala())) {
                    if (!auxSalas.contains(s)) {
                        auxSalas.add(s);
                    }
                }
            }
        }
        return auxSalas;
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
            Recurso foundedRecurso = null;
            if (this.recurso.getIdRecurso() != null) {
                foundedRecurso = this.recursoEJB.find(this.recurso.getIdRecurso());
            }

            if (foundedRecurso == null) {
                this.criar();
            } else {
                this.editar();
            }
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("$('#dlgCadastroRecurso').modal('close');");
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao cadastrar Recurso!"));
        }
    }

    private void criar() {
        try {
            this.recursoEJB.create(this.recurso);
            this.atualizarTabela();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Recurso registrado com sucesso!"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao tentar cadastrar Recurso!"));
        }
    }

    private void editar() {
        try {
            recursoEJB.edit(this.recurso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Recurso editado com sucesso!"));
            atualizarTabela();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao editar recurso!"));
        }
    }

    public void atualizarTabela() {
        this.init();
        try {
            //RequestContext requestContext = RequestContext.getCurrentInstance();
            //requestContext.update(":formTabela:tabela");
            //Com Ajax não funcionou, a saída foi atualizar toda a página.
            FacesContext.getCurrentInstance().getExternalContext().redirect("./recurso.sis");
        } catch (IOException ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao atualizar tabela de Recursos!"));
        }
    }

    public void excluir(Andar andar) {
        try {
            recursoEJB.remove(recurso);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso!", "Recurso excluído com sucesso!"));
            atualizarTabela();
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Aviso!", "Erro ao excluir recurso!"));
        }
    }

    public void exibir(Recurso recurso) {
        this.recurso = recurso;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        requestContext.execute("$('#dlgCadastroRecurso').modal('open');"
                + "$('#dlgCadastroRecursoTitulo').text(\"Atualizar Recurso\");");
    }

    public void filtrarPredios() {
        this.predios = new ArrayList<>();
        for (Predio p : this.predioEJB.findAll()) {
            if (p.getCampus().getIdCampus().equals(this.campus.getIdCampus())) {
                if (p.getAndares().size() > 0) {
                    this.predios.add(p);
                }
            }
        }
    }

    public void filtrarAndares() {
        this.andares = new ArrayList<>();
        for (Andar a : this.andarEJB.findAll()) {
            if (a.getPredio().getIdPredio().equals(this.predio.getIdPredio())) {
                if (a.getSalas().size() > 0) {
                    this.andares.add(a);
                }
            }
        }
    }

    public void filtrarSalas() {
        this.salas = new ArrayList<>();
        for (Sala a : this.salaEJB.findAll()) {
            if (a.getAndar().getIdAndar().equals(this.andar.getIdAndar())) {
                this.salas.add(a);
            }
        }
    }
}
