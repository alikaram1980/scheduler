package com.example.web;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dao.ProductRepository;
import com.example.entity.Product;
import com.example.messaging.MessageReceiver;
import com.example.messaging.MessageSender;

@RestController
public class ProductRestService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	MessageSender messageSender;
	
	static final Logger LOG = LoggerFactory.getLogger(ProductRestService.class);
	
	@RequestMapping(value="/Products",method=RequestMethod.GET)
	public List<Product> listProducts(){
	return productRepository.findAll();
		
	}
	//@Secured(value={"ROLE_ADMIN"})
	@PreAuthorize("hasRole('ROLE_USER')")
	@RequestMapping(value="/Products/{id}",method=RequestMethod.GET)
	public Product getProduct(@PathVariable(name="id")Long id){
	return productRepository.findOne(id);
		
	}
	@Secured(value={"ROLE_ADMIN"})
	@RequestMapping(value="/Products",method=RequestMethod.POST)
	public Product getProduct(@RequestBody Product p){
		messageSender.sendMessage(p);
		
	//return productRepository.save(p);
		return p; 
	}
	
	@Secured(value={"ROLE_ADMIN"})
	@Cacheable("products")
	@RequestMapping(value="/Fetch",method=RequestMethod.GET)
	public Page<Product> fetchAll(
			@RequestParam(name = "mc",defaultValue="")String mc,
			@RequestParam(name = "page",defaultValue="0")int page,
			@RequestParam(name = "size",defaultValue="4")int size){
		
		
		
		 LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		
		
		
		
	return productRepository.getAllProduct
			("%"+mc+"%",new PageRequest(page,size));	
	}
	
	@RequestMapping(value="/Products/{id}",method=RequestMethod.PUT)
	public Product update(@PathVariable(name="id")Long id,@RequestBody Product p){
		p.setId(id);
	return productRepository.save(p);	
	}
	
	
//	@RequestMapping("/login")
//	public String login() {
//		return "login";
//	}
//	
	
}