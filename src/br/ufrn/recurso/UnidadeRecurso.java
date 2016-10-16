package br.ufrn.recurso;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.ufrn.dao.DAOUnidade;
import br.ufrn.model.Unidade;

@Path("/unidade")
public class UnidadeRecurso {
	private DAOUnidade daounidade = new DAOUnidade(); 
	
	
	@POST
	@Path("/add")
	@Consumes("application/json")
	public Response adicionarUnidade(Unidade unidade){
		//Gson gson = new Gson();
		//Municipio cidade = gson.fromJson(local, Municipio.class);
		unidade.setNome(unidade.getNome());
		unidade.setMunicipio(unidade.getMunicipio());
		//municipio.setNome(cidade.getNome());
		daounidade.adicionarUnidade(unidade);
		return Response.ok().build();
	}

	@GET
	@Path("/consulta")
	@Produces("application/json")
	public List<Unidade> verMunicipios(){
		List listaunidades =  daounidade.listarUnidades();
		return listaunidades;
	}

	@GET
	@Path("/consulta/{id}")
	//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON})
	public Unidade verUnidade(@PathParam("id") String id){
		return daounidade.listarUnidade(id);
	}

}