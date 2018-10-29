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
import br.edu.utfpr.md.restapiwithjava.dao.KeywordDAO;
import br.edu.utfpr.md.restapiwithjava.model.Keyword;
import java.util.List;
import javax.inject.Inject;

@Controller
@Path("/keyword")
public class KeywordResource {
    
    @Inject
    private KeywordDAO keywordDAO;
    @Inject
    private Result result;

    @Post(value = {"", "/"})
    @Consumes("application/json")
    public void save(Keyword keyword) {
        try {
            keywordDAO.save(keyword);
            result.use(Results.json()).withoutRoot().from(keyword).serialize();
        } catch (Exception e) {
            result.use(Results.http()).setStatusCode(400);
            e.printStackTrace();
        }
    }

    @Put(value = {"", "/"})
    @Consumes("application/json")
    public void update(Keyword keyword) {
        keywordDAO.update(keyword);
        result.use(Results.json()).withoutRoot().from(keyword).serialize();
    }

    @Delete("/{id}")
    public void delete(int id) {
        Keyword p = keywordDAO.getById(id);
        if (p == null) {
            result.use(Results.status()).notFound();
        } else {
            keywordDAO.delete(p);
            // result.use(Results.status()).ok();
            result.use(Results.nothing());
        }
    }

    @Get("/{name}")
    public void getOne(String name) {
        List<Keyword> p = keywordDAO.getByName(name);
        result.use(Results.json()).withoutRoot().from(p).serialize();
    }

    @Get(value = {"", "/"})
    public void getAll() {
        List<Keyword> list = keywordDAO.findAll();
        result.use(Results.json()).withoutRoot().from(list).serialize();
    }
    @Get("/id/{id}")
    public void getOneByID(int id){
        Keyword p = keywordDAO.getById(id);
        result.use(Results.json()).withoutRoot().from(p).serialize();
    }
}
