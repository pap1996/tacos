package com.tacocloud.tacos.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tacocloud.tacos.domain.Taco;
import com.tacocloud.tacos.repository.TacoRepository;

@RestController
@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8080")
public class TacoController {
	
	@Autowired
	private TacoRepository tacoRepo;
	
	
	@GetMapping(params = "recent")
	public Iterable<Taco> recentTacos() {
		PageRequest page = PageRequest.of(0, 12,Sort.by("createdAt").descending());
		
		return tacoRepo.findAll(page).getContent();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity tacoById(@PathVariable("id") Long id) {
		
		Optional<Taco> optTaco = tacoRepo.findById(id);
		
		if (optTaco.isPresent()) {
			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

}
