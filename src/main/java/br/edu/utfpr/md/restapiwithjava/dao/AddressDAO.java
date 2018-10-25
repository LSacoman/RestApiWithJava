package br.edu.utfpr.md.restapiwithjava.dao;

import br.edu.utfpr.md.restapiwithjava.model.Address;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class AddressDAO extends GenericDAO<Integer, Address> {

    public AddressDAO() {
        super();
    }
}
