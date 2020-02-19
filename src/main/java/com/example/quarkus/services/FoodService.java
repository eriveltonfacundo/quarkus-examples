package com.example.quarkus.services;

import com.example.quarkus.models.Food;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class FoodService {

    public Food update(Long id, Food food) {
        Food foodEntity = Food.findById(id);
        if (foodEntity == null)
            throw new WebApplicationException("Food with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        foodEntity.setName(food.getName());
        foodEntity.setCalories(food.getCalories());
        return foodEntity;
    }
}

