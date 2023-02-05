package com.tacocloud.tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.tacocloud.tacos.domain.Ingredient;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {

}
