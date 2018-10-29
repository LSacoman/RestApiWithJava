package br.edu.utfpr.md.restapiwithjava.resource;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.edu.utfpr.md.restapiwithjava.dao.PessoaDAO;
import br.edu.utfpr.md.restapiwithjava.model.Pessoa;
import br.edu.utfpr.md.restapiwithjava.security.JWTUtil;
import javax.inject.Inject;

@Controller
@Path("/auth")
public class AuthResource {

    @Inject
    private PessoaDAO pessoaDAO;
    @Inject
    private Result result;

    @Post(value = {"", "/"})
    @Consumes("application/json")
    public void autenticate(Pessoa pessoa) {
        if(pessoaDAO.Autenticate(pessoa)){
            String token = JWTUtil.createToken((long) pessoa.getId());
            result.use(Results.json()).withoutRoot().from(token).serialize();
        }
    }
}
