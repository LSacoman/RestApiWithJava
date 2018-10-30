package br.edu.utfpr.md.restapiwithjava.model;

import javax.persistence.Entity;

@Entity
public class Admin extends Pessoa{

    public Admin() {
        super();
    }
    
    // extends Pessoa

    public Admin(int id, String nome, String login, String senha, Address address) {
        super(id, nome, login, senha, address);
    }
    
    public Admin (Pessoa p){
        super(p.getId(), p.getNome(), p.getLogin(), p.getSenha(), p.getAddress());
    }
    
    
}
