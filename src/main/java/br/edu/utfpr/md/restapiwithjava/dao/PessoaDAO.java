package br.edu.utfpr.md.restapiwithjava.dao;


import br.edu.utfpr.md.restapiwithjava.model.Pessoa;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PessoaDAO extends GenericDAO<Integer, Pessoa> {

    public PessoaDAO() {
        super();
    }
    
    public boolean Autenticate(Pessoa pessoa){
        Pessoa q = entityManager.createQuery(
                "SELECT p FROM Pessoa p WHERE p.login = " 
                        + pessoa.getLogin() + 
                        " AND p.senha = " + pessoa.getSenha() + "", Pessoa.class).getSingleResult();
        if(q != null){
            return true;
        }
        return false;
    }
}