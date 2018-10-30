package br.edu.utfpr.md.restapiwithjava.dao;

import br.edu.utfpr.md.restapiwithjava.model.Admin;
import br.edu.utfpr.md.restapiwithjava.model.Pessoa;
import br.edu.utfpr.md.restapiwithjava.model.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PessoaDAO extends GenericDAO<Integer, Pessoa> {

    public PessoaDAO() {
        super();
    }

    public Pessoa Autenticate(String username, String password) {
        Pessoa p = null;
        try {
            p = entityManager.createQuery(
                    "SELECT p FROM Pessoa p WHERE p.login = '" + username
                    + "' AND p.senha = '" + password + "'", Pessoa.class).getSingleResult();
            return p;
        } catch (Exception e) {           
            return p;
        }
    }
    
    public List<Integer> getDistinctCategories(Pessoa p){
        List<Integer> cat = null;
        try {
            //cat = entityManager.createQuery("SELECT p.")
        } catch (Exception e) {
        }
        return null;
    }
    
    @Override
    public void save(Pessoa p) {
        Pessoa entity;
        if(p.getLogin().contains("admin")){
            entity = new Admin(p);
        }else{
            entity = new User(p);
        }
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }
}
