package com.github.nfers.ifood.cadastro;

import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.transaction.Transactional;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

    @GET
    @Path("{idRestaurante}/pratos")
    public void buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
    	Optional<PanacheEntityBase> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante Não Localizado");
    	}
    	

    }
  
}