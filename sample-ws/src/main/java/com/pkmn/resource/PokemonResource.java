package com.pkmn.resource;

import java.net.URI;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.pkmn.dto.PokemonDto;
import com.pkmn.exception.NotFoundException;
import com.pkmn.exception.ValidationException;
import com.pkmn.resource.request.RequestPokemon;
import com.pkmn.resource.response.ResponsePokemon;
import com.pkmn.service.PokemonService;

@Path("pokemon")
public class PokemonResource {

    @Context
    private UriInfo info;

    @Inject
    private PokemonService pokemonService;

    public PokemonResource() {
        // TODO Auto-generated constructor stub
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPokemon(RequestPokemon pokemon) throws ValidationException {
        String baseUri = info.getBaseUri().toString();

        if (pokemon == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }

        PokemonDto dto = pokemonService.createPokemon(pokemon.getNickname(), pokemon.getLevel(), pokemon.getSpeciesId());
        ResponsePokemon response = new ResponsePokemon(dto, baseUri);

        return Response.created(URI.create(response.getRef())).entity(response).build();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getPokemon(@PathParam("id") String id) throws NotFoundException, ValidationException {
        String baseUri = info.getBaseUri().toString();

        PokemonDto dto = pokemonService.getPokemonDto(id);
        ResponsePokemon response = new ResponsePokemon(dto, baseUri);

        return Response.ok(response).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response nicknamePokemon(@PathParam("id") String id, RequestPokemon pokemon) throws NotFoundException,
                    ValidationException {

        if (pokemon == null) {
            return Response.status(Status.BAD_REQUEST).build();
        }

        pokemonService.nicknamePokemon(id, pokemon.getNickname());

        return Response.noContent().build();

    }

}
