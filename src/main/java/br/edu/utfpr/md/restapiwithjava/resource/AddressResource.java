package br.edu.utfpr.md.restapiwithjava.resource;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.edu.utfpr.md.restapiwithjava.dao.AddressDAO;
import br.edu.utfpr.md.restapiwithjava.model.Address;
import java.util.List;
import javax.inject.Inject;

@Controller
@Path("/address")
public class AddressResource {
    
    @Inject
    private AddressDAO addressDAO;
    @Inject
    private Result result;

    @Post(value = {"", "/"})
    @Consumes("application/json")
    public void save(Address address) {
        try {
            addressDAO.save(address);
            result.use(Results.json()).withoutRoot().from(address).serialize();
        } catch (Exception e) {
            result.use(Results.http()).setStatusCode(400);
            e.printStackTrace();
        }
    }

    @Put(value = {"", "/"})
    @Consumes("application/json")
    public void update(Address address) {
        addressDAO.update(address);
        result.use(Results.json()).withoutRoot().from(address).serialize();
    }

    @Delete("/{id}")
    public void delete(int id) {
        Address p = addressDAO.getById(id);
        if (p == null) {
            result.use(Results.status()).notFound();
        } else {
            addressDAO.delete(p);
            // result.use(Results.status()).ok();
            result.use(Results.nothing());
        }
    }

    @Get("/{id}")
    public void getOne(int id) {
        Address p = addressDAO.getById(id);
        result.use(Results.json()).withoutRoot().from(p).serialize();
    }

    @Get(value = {"", "/"})
    public void getAll() {
        List<Address> list = addressDAO.findAll();
        result.use(Results.json()).withoutRoot().from(list).serialize();
    }
}
