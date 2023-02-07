package com.tacocloud.tacos;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.tacocloud.tacos.domain.TacoOrder;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/orders")
@Slf4j
@SessionAttributes("tacoOrder")
public class OrderTacoController {
	
	@GetMapping("/current")
	public String orderForm(@SessionAttribute TacoOrder tacoOrder) {
		
		
		tacoOrder.getTacos().stream().forEach(x -> {
			log.info("In tacoOrder in OrderController: {}", x);
		});
		
		return "orderForm";
	}
	
	
	@PostMapping
	public String processOrder(SessionStatus sessionStatus, @Valid TacoOrder tacoOrder, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "orderForm";
		}
		log.info("Order info: {}", tacoOrder);
		
		sessionStatus.setComplete();
		
		return "redirect:/home";
	}

}
