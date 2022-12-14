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

import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.eclipse.microprofile.openapi.annotations.tags.Tags;

import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.transaction.Transactional;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;


@Path("/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "restaurante")
public class RestauranteResource {

    @GET
    public List<Restaurante> buscarTodos() {
        return Restaurante.listAll();
    }
    
    @POST
    @Transactional
    public Response criar(Restaurante dto) {
    	dto.persist();
    	
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{id}")
    @Transactional
    public Response atualizar(@PathParam("id") Long id, Restaurante dto ) {
    	
    	Optional<PanacheEntityBase> restauranteOp = Restaurante.findByIdOptional(id);
    	
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException();
    	}
    	
    	Restaurante restaurante = (Restaurante) restauranteOp.get();
    	
    	restaurante.nome = dto.nome;
    	dto.persist();
    	
    	return Response.status(Status.OK).build();
    }
    
    
    @DELETE
    @Path("{id}")
    @Transactional
    public void deletar(@PathParam("id") Long id, Restaurante dto ) {
    	
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(id);
    	
    	restauranteOp.ifPresentOrElse(Restaurante::delete, () -> {
    		throw new NotFoundException();
    	});
    	
    }    
    
    
    @GET
    @Path("{idRestaurante}/pratos")
    @Tag(name = "prato")
    public List<Restaurante> buscarPratos(@PathParam("idRestaurante") Long idRestaurante) {
    	Optional<PanacheEntityBase> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante N??o Localizado");
    	}
    	    	
    	return Prato.list("restaurante", restauranteOp.get());
    }
    
    @POST
    @Path("{idRestaurante}/pratos")
    @Transactional
    @Tag(name = "prato")
    public Response criarPrato(@PathParam("idRestaurante") Long idRestaurante, Prato dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante N??o Localizado");
    	}
    	
    	Prato prato = new Prato();
    	
    	prato.nome = dto.nome;
    	prato.descricao = dto.descricao;
    	prato.preco = dto.preco;
    	
    	prato.restaurante = restauranteOp.get();
    	    	
    	dto.persist();
    	
    	return Response.status(Status.CREATED).build();
    }
    
    @PUT
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name = "prato")
    public Response atualizarPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id, Prato dto) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante N??o Localizado");
    	}
    	
    	Optional<Prato> pratoOp = Prato.findByIdOptional(id);
    	
    	if(pratoOp.isEmpty()) {
    		throw new NotFoundException("Prato N??o Localizado");
    	}
    	
    	Prato prato = pratoOp.get();
    	
    	prato.nome = dto.nome;
    	prato.descricao = dto.descricao;
    	prato.preco = dto.preco;
    	
    	dto.persist();
    	
    	return Response.status(Status.OK).build();
    }
    
    @DELETE
    @Path("{idRestaurante}/pratos/{id}")
    @Transactional
    @Tag(name = "prato")
    public Response removerPrato(@PathParam("idRestaurante") Long idRestaurante, @PathParam("id") Long id) {
    	Optional<Restaurante> restauranteOp = Restaurante.findByIdOptional(idRestaurante);
        
    	if(restauranteOp.isEmpty()) {
    		throw new NotFoundException("Restaurante N??o Localizado");
    	}
    	
    	Optional<Prato> pratoOp = Prato.findByIdOptional(id);
    	
    	if(pratoOp.isEmpty()) {
    		throw new NotFoundException("Prato N??o Localizado");
    	}
    	
    	pratoOp.ifPresentOrElse(Prato::delete, () -> {
    		throw new NotFoundException();
    	});
    	
    	return Response.status(Status.OK).build();
    }
    
    
    
}