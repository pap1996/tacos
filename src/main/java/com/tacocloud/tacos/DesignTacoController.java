package com.tacocloud.tacos;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tacocloud.tacos.domain.Ingredient;
import com.tacocloud.tacos.domain.Ingredient.Type;
import com.tacocloud.tacos.domain.Taco;
import com.tacocloud.tacos.domain.TacoOrder;
import com.tacocloud.tacos.repository.IngredientRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/design")
@Slf4j
public class DesignTacoController {
	
	@Autowired
	private IngredientRepository ingredientRepository;
	
	@ModelAttribute
	public void addIngredientsToModel(Model model) {
		
		List<Ingredient> ingredients = ingredientRepository.findAll();
		
		Type[] types = Ingredient.Type.values();
		
		for (Type type: types) {
			model.addAttribute(type.toString().toLowerCase()
					, filterByType(ingredients, type));
		}
		
	}
	
	
	@ModelAttribute(name="tacoOrder")
	public TacoOrder order() {
		return new TacoOrder();
	}
	
	@ModelAttribute(name ="taco")
	public Taco taco() {
		return new Taco();
	}
	
	@GetMapping
	public String showDesignForm() {
		return "design";
	}

	private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {

		return ingredients.stream()
							.filter(x -> x.getType().equals(type))
							.collect(Collectors.toList());
	}

}
