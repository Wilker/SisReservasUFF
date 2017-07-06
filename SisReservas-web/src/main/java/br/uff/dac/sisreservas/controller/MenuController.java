package br.uff.dac.sisreservas.controller;

import br.uff.dac.sisreservas.ejb.MenuFacadeLocal;
import br.uff.dac.sisreservas.model.Menu;
import br.uff.dac.sisreservas.model.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

@Named("menuController")
@SessionScoped
public class MenuController implements Serializable {

    @EJB
    private MenuFacadeLocal menuEJB;

    private List<Menu> menus;
    
    private MenuModel model;

    @PostConstruct
    public void init() {
        this.listaMenus();
        this.model = new DefaultMenuModel();
        this.configurarPermissoes();
    }

    public MenuModel getModel() {
        return model;
    }

    public void setModel(MenuModel model) {
        this.model = model;
    }

    public void listaMenus() {
        try {
            this.menus = this.menuEJB.findAll();
        } catch (Exception ex) {
            //
        }
    }

    public void configurarPermissoes() {
        try {
            Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            for (Menu menu : this.menus) {
                if (menu.getTipo() == 'S' && ((menu.getTipoUsuario() == user.getTipo()) | (menu.getTipoUsuario() == 'G')) ) {
                    DefaultSubMenu firstSubmenu = new DefaultSubMenu(menu.getNome());
                    for (Menu mnu : this.menus) {
                        Menu submenu = mnu.getSubMenu();
                        if (submenu != null) {
                            if (submenu.getId().equals(menu.getId())) {
                                DefaultMenuItem item = new DefaultMenuItem(mnu.getNome());
                                item.setUrl(mnu.getUrl());
                                firstSubmenu.addElement(item);
                            }
                        }
                    }
                    this.model.addElement(firstSubmenu);
                } else {
                    if (menu.getSubMenu() == null && ((menu.getTipoUsuario() == user.getTipo()) | (menu.getTipoUsuario() == 'G'))) {
                        DefaultMenuItem item = new DefaultMenuItem(menu.getNome());
                        item.setUrl(menu.getUrl());
                        this.model.addElement(item);
                    }

                }
            }
        } catch (Exception ex) {

        }
    }
}
