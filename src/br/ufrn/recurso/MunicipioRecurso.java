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

import com.google.gson.Gson;
import br.ufrn.dao.DAOMunicipio;
import br.ufrn.model.Municipio;

@Path("/municipio")
public class MunicipioRecurso {
	private DAOMunicipio daomunicipio = new DAOMunicipio();

	@POST
	@Path("/add")
	@Consumes("application/json")
	public void adicionarMunicipio(Municipio municipio){
		//Gson gson = new Gson();
		//Municipio cidade = gson.fromJson(local, Municipio.class);
		Municipio cidade = new Municipio();
		cidade.setNome(municipio.getNome());
		//municipio.setNome(cidade.getNome());
		daomunicipio.adicionarMunicipio(cidade);
		
		//return Response.ok().build();
	}

	@GET
	@Path("/consulta")
	@Produces("application/json")
	public List<Municipio> verMunicipios(){
		List listaMunicipios = daomunicipio.listarMuncipios(); 
		return listaMunicipios;
	}

	@GET
	@Path("/consulta/{id}")
	//@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Produces({ MediaType.APPLICATION_JSON})
	public Municipio verMunicipio(@PathParam("id") String id){
		return daomunicipio.listarMuncipio(id);
	}

}