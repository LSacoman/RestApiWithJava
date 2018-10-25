package br.edu.utfpr.md.restapiwithjava.dao;

import br.edu.utfpr.md.restapiwithjava.model.Document;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class DocumentDAO extends GenericDAO<Integer, Document> {

    public DocumentDAO() {
        super();
    }
}
