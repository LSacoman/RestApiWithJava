package br.edu.utfpr.md.restapiwithjava.dao;

import br.edu.utfpr.md.restapiwithjava.model.Category;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class CategoryDAO extends GenericDAO<Integer, Category> {

    public CategoryDAO() {
        super();
    }
}
