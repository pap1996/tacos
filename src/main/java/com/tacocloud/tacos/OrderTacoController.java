package com.tacocloud.tacos;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tacocloud.tacos.domain.TacoOrder;
import com.tacocloud.tacos.domain.User;
import com.tacocloud.tacos.repository.TacoOrderRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/orders")
@Slf4j
@SessionAttributes("tacoOrder")
public class OrderTacoController {
	
	
	@Autowired
	private TacoOrderRepository tacoOrderRepo;
	
	
	@ModelAttribute(name = "user")
	public User getAuthenUser(@AuthenticationPrincipal User user) {
		return user;
	};
	
	
	@GetMapping("/current")
	public String orderForm(@SessionAttribute TacoOrder tacoOrder, @ModelAttribute User user) {
		
		
//		tacoOrder.getTacos().stream().forEach(x -> {
//			log.info("In tacoOrder in OrderController: {}", x);
//		});
		
		
		log.info("User authen {}", user);
		
		tacoOrder.setUser(user);
		tacoOrder.setDeliveryName(user.getFullname());
		tacoOrder.setDeliveryStreet(user.getStreet());
		
		log.info("Taco Order {}", tacoOrder);
		
		
		return "orderForm";
	}
	
	
	@PostMapping
	public String processOrder(SessionStatus sessionStatus, @Valid TacoOrder tacoOrder, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "orderForm";
		}
		log.info("Order info: {}", tacoOrder);
		
		tacoOrderRepo.save(tacoOrder);
		
		sessionStatus.setComplete();
		
		return "redirect:/home";
	}

}
