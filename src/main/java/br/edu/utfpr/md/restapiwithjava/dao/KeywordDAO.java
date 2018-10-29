package br.edu.utfpr.md.restapiwithjava.dao;

import br.edu.utfpr.md.restapiwithjava.model.Keyword;
import java.util.List;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class KeywordDAO extends GenericDAO<Integer, Keyword> {

    public KeywordDAO() {
        super();
    }
    
    public List<Keyword> getByName(String name) {
        return entityManager.createQuery(("SELECT e FROM Keyword e WHERE e.name LIKE '" + name + "'")).getResultList();
    }
}
