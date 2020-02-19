package com.example.quarkus.contollers;

import com.example.quarkus.models.Food;
import com.example.quarkus.services.FoodService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON_TYPE;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/foods")
@Produces(APPLICATION_JSON)
@Consumes(APPLICATION_JSON)
public class FoodResource {

    @Inject
    private FoodService foodService;

    @GET
    public List<Food> findAll() {
        return Food.listAll();
    }

    @POST
    @Transactional
    public Response create(@Valid Food food) {
        try {
            Food.persist(food);
            return Response.ok(food).status(201).build();
        } catch (ConstraintViolationException e) {
            return Response.status(400).entity(e.getConstraintViolations()).build();
        }

    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, @Valid Food food) {
        Food entity = Food.findById(id);
        if (entity == null)
            return Response.ok("Food was not found").type(APPLICATION_JSON_TYPE).build();

        Food foodEntity = foodService.update(id, food);
        return Response.ok(foodEntity).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        Food foodEntity = Food.findById(id);
        if (foodEntity == null)
            throw new WebApplicationException("Food with id " + id + " does not exist.", NOT_FOUND);
        foodEntity.delete();
        return Response.status(204).build();
    }
}
