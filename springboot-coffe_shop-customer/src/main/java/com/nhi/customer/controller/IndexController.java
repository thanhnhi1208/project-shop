package com.nhi.customer.controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nhi.libary.model.Customer;
import com.nhi.libary.repository.CustomerRepository;
import com.nhi.libary.repository.ProductRepository;
import com.nhi.libary.service.CustomerService;

@Controller
public class IndexController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ChatBotController chatBotController;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	
	@GetMapping("/index")
	public String index(Model model, Authentication authentication) throws FileNotFoundException, IOException {
		model.addAttribute("shoppingCart", customerRepository.findByEmail(authentication.getName()).getShoppingCart());
		model.addAttribute("customer", customerRepository.findByEmail(authentication.getName()));
		return "homeCoffee";
	}
	
	@GetMapping("/findCurrentCustomer")
	@ResponseBody
	public Customer findCurrentCustomer(Authentication authentication) {
		
		return customerService.findByEmail(authentication.getName());
	}
}
