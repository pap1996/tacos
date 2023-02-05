package com.tacocloud.tacos;

import java.util.Collection;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.tacocloud.tacos.domain.Ingredient;
import com.tacocloud.tacos.repository.IngredientRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RepositoryTest {
	
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	
	@Test
	public void testIngredientRepo() {
		
	int size = ((Collection<Ingredient>) ingredientRepository.findAll()).size();
	
	Assertions.assertEquals(9, size);
	}

}
