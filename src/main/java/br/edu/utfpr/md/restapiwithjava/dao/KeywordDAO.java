package br.edu.utfpr.md.restapiwithjava.dao;

import br.edu.utfpr.md.restapiwithjava.model.Keyword;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class KeywordDAO extends GenericDAO<Integer, Keyword> {

    public KeywordDAO() {
        super();
    }

    public Keyword getByName(String name) {
        Keyword k = null;
        try {
            k = entityManager.createQuery(("SELECT e FROM Keyword e WHERE e.name LIKE '" + name + "'"), Keyword.class).getSingleResult();
        } catch (Exception e) {
        }
        return k;
    }
}
