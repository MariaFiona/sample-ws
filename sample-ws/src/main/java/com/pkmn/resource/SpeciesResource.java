package com.pkmn.resource;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.pkmn.db.entity.definitions.PokemonType;
import com.pkmn.dto.SpeciesDto;
import com.pkmn.resource.response.ResponseSpecies;
import com.pkmn.service.SpeciesService;

/**
 * Root resource
 */
@Path("species")
public class SpeciesResource {
    @Context
    private UriInfo info;

    @Inject
    private SpeciesService speciesService;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecies(@PathParam("id") Long id) {
        SpeciesDto dto = speciesService.getSpeciesDto(id);
        String baseUri = info.getBaseUri().toString();

        ResponseSpecies response = new ResponseSpecies(dto, baseUri);
        return Response.ok(response).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecieses(@QueryParam("name") String name, @QueryParam("type") PokemonType type) {

        String baseUri = info.getBaseUri().toString();

        List<SpeciesDto> dtos = speciesService.getSpeciesDtos(name, type);

        List<ResponseSpecies> responseList = new ArrayList<ResponseSpecies>();

        for (SpeciesDto dto : dtos) {
            responseList.add(new ResponseSpecies(dto, baseUri));
        }

        GenericEntity<List<ResponseSpecies>> entity = new GenericEntity<List<ResponseSpecies>>(responseList) {
        };

        return Response.ok(entity).build();
    }
}
