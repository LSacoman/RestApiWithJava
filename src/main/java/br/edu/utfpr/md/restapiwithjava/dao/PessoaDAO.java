package br.edu.utfpr.md.restapiwithjava.dao;

import br.edu.utfpr.md.restapiwithjava.model.Admin;
import br.edu.utfpr.md.restapiwithjava.model.Category;
import br.edu.utfpr.md.restapiwithjava.model.Pessoa;
import br.edu.utfpr.md.restapiwithjava.model.User;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

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
    
    public List<Category> getDistinctCategories(Pessoa p){
        List<Category> lista = null;
        try {
            TypedQuery<Category> cat = entityManager.createQuery(""
                    + "SELECT "
                    + "     DISTINCT(d.category) "
                    + "FROM "
                    + "     Pessoa p, "
                    + "     Document d "
                    + "WHERE "
                    + "     p.documents = d"
                    + "     AND p.id = 15", Category.class);
            
            
            
            // SELECT distinct(d.category_id) 
            // FROM tb_document d, tb_pessoa_tb_document p 
            // WHERE p.documents_ID = d.ID and p.Pessoa_ID = 1
            lista  = cat.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
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
