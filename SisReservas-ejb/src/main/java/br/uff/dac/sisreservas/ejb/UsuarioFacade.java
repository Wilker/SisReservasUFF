package br.uff.dac.sisreservas.ejb;

import br.uff.dac.sisreservas.model.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "br.uff.dac.sisreservas_unidade_persistencia")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario Login(Usuario user) {
        Usuario usuario = null;
        String consulta;
        try {
            consulta = "FROM Usuario u WHERE u.email = ?1 and u.senha = ?2 ";
            Query query = em.createQuery(consulta);
            query.setParameter(1, user.getEmail());
            query.setParameter(2, user.getSenha());
            List<Usuario> lista = query.getResultList();
            if (!lista.isEmpty()) {
                usuario = lista.get(0);
            }
        } catch (Exception ex) {
            throw ex;
        }
        return usuario;
    }

    @Override
    public boolean emailExiste(Usuario user) {
        String consulta;
        try {
            consulta = "FROM Usuario u WHERE u.email = ?1";
            Query query = em.createQuery(consulta);
            query.setParameter(1, user.getEmail());
            List<Usuario> lista = query.getResultList();
            return !lista.isEmpty();
        } catch (Exception ex) {
            throw ex;
        }
    }

}
