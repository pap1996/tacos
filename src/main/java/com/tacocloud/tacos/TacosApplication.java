package com.tacocloud.tacos;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.tacocloud.tacos.domain.Ingredient;
import com.tacocloud.tacos.domain.Ingredient.Type;
import com.tacocloud.tacos.domain.Taco;
import com.tacocloud.tacos.repository.IngredientRepository;
import com.tacocloud.tacos.repository.TacoRepository;
import com.tacocloud.tacos.repository.UserRepository;

@SpringBootApplication//(exclude = DataSourceAutoConfiguration.class)
@EntityScan(basePackages = { "com.tacocloud.tacos.domain" })
public class TacosApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(TacosApplication.class, args);
	}

	
//	@Bean
//	public ApplicationRunner dataLoader(IngredientRepository repo) {
//		return args -> {
//			repo.save(new Ingredient("FLTO", "Flour Tortilla", Type.WRAP));
//			repo.save(new Ingredient("COTO", "Corn Tortilla", Type.WRAP));
//			repo.save(new Ingredient("GRBF", "Ground Beef", Type.PROTEIN));
//			repo.save(new Ingredient("CARN", "Carnitas", Type.PROTEIN));
//			repo.save(new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES));
//			repo.save(new Ingredient("LETC", "Lettuce", Type.VEGGIES));
//			repo.save(new Ingredient("CHED", "Cheddar", Type.CHEESE));
//			repo.save(new Ingredient("JACK", "Monterrey Jack", Type.CHEESE));
//			repo.save(new Ingredient("SLSA", "Salsa", Type.SAUCE));
//			repo.save(new Ingredient("SRCR", "Sour Cream", Type.SAUCE)); 
//		};
//	}
	
	// Load data to test api
	@Bean
	public CommandLineRunner dataLoader(IngredientRepository repo, UserRepository userRepo, PasswordEncoder encoder,
			TacoRepository tacoRepo) {
		return args -> {
			Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
			Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
			Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
			Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
			Ingredient tomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
			Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
			Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
			Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
			Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
			Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);
			repo.save(flourTortilla);
			repo.save(cornTortilla);
			repo.save(groundBeef);
			repo.save(carnitas);
			repo.save(tomatoes);
			repo.save(lettuce);
			repo.save(cheddar);
			repo.save(jack);
			repo.save(salsa);
			repo.save(sourCream);
			Taco taco1 = new Taco();
			taco1.setName("Carnivore");
			taco1.setIngredients(Arrays.asList(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
			tacoRepo.save(taco1);
			Taco taco2 = new Taco();
			taco2.setName("Bovine Bounty");
			taco2.setIngredients(Arrays.asList(cornTortilla, groundBeef, cheddar, jack, sourCream));
			tacoRepo.save(taco2);
			Taco taco3 = new Taco();
			taco3.setName("Veg-Out");
			taco3.setIngredients(Arrays.asList(flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
			tacoRepo.save(taco3);
		};
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// TODO Auto-generated method stub
		registry.addViewController("/").setViewName("home");
//		registry.addViewController("/login");
	}
	
}
