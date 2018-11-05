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
import br.edu.utfpr.md.restapiwithjava.dao.CategoryDAO;
import br.edu.utfpr.md.restapiwithjava.dao.PessoaDAO;
import br.edu.utfpr.md.restapiwithjava.model.Category;
import br.edu.utfpr.md.restapiwithjava.model.Pessoa;
import br.edu.utfpr.md.restapiwithjava.security.Autenticado;
import java.util.List;
import javax.inject.Inject;

@Controller
@Path("/category")
public class CategoryResource {

    @Inject
    private CategoryDAO categoryDAO;
    @Inject
    private PessoaDAO pessoaDAO;
    @Inject
    private Result result;

    @Autenticado
    @Post(value = {"", "/"})
    @Consumes("application/json")
    public void save(Category category) {
        try {
            categoryDAO.save(category);
            result.use(Results.json()).withoutRoot().from(category).serialize();
        } catch (Exception e) {
            result.use(Results.http()).setStatusCode(400);
            e.printStackTrace();
        }
    }

    @Autenticado
    @Put(value = {"", "/"})
    @Consumes("application/json")
    public void update(Category category) {
        categoryDAO.update(category);
        result.use(Results.json()).withoutRoot().from(category).serialize();
    }

    @Autenticado
    @Delete("/{id}")
    public void delete(int id) {
        Category p = categoryDAO.getById(id);
        if (p == null) {
            result.use(Results.status()).notFound();
        } else {
            categoryDAO.delete(p);
            // result.use(Results.status()).ok();
            result.use(Results.nothing());
        }
    }

    @Autenticado
    @Get("/{id}")
    public void getOne(int id) {
        Category p = categoryDAO.getById(id);
        result.use(Results.json()).withoutRoot().from(p).serialize();
    }

    @Autenticado
    @Get(value = {"", "/"})
    public void getAll() {
        List<Category> list = categoryDAO.findAll();
        result.use(Results.json()).withoutRoot().from(list).serialize();
    }

    @Autenticado
    @Get(value = {"/person/{id}"})
    public void getAllCategories(int id) {
        // obtem todas as categorias de um usuario
        Pessoa p = pessoaDAO.getById(id);
        List<Category> categories = pessoaDAO.getDistinctCategories(p);
        result.use(Results.json()).withoutRoot().from(categories).serialize();
    }
}
