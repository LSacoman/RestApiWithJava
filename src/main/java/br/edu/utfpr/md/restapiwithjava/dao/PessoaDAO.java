package br.edu.utfpr.md.restapiwithjava.dao;


import br.edu.utfpr.md.restapiwithjava.model.Pessoa;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PessoaDAO extends GenericDAO<Integer, Pessoa> {

    public PessoaDAO() {
        super();
    }
}