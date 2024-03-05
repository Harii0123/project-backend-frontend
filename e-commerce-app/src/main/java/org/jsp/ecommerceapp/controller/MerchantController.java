package org.jsp.ecommerceapp.controller;

import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchants")
public class MerchantController {
	@Autowired
	private MerchantService merchantservice;
	@PostMapping
	public ResponseEntity<ResponseStructure<Merchant>> savemerchant(@RequestBody Merchant merchant) {
		return merchantservice.savemerchant(merchant);
	}
	@PutMapping 
	public ResponseEntity<ResponseStructure> updatemerchant(@RequestBody Merchant merchant) {
		return merchantservice.updatemerchant(merchant); 
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure> findbyid(@PathVariable(name = "id")int id) {
		return merchantservice.findbyid(id);
	}
	
	@DeleteMapping
	(value = "/{id}")
	public ResponseEntity<ResponseStructure> deletebyid(@PathVariable(name = "id")int id) {
		return merchantservice.deletebyid(id); 
	}
	@GetMapping
	public ResponseEntity<ResponseStructure> findall(){
     return merchantservice.findall();
	}
	

	@PostMapping("/verify-by-phone")
	public ResponseEntity<ResponseStructure> verifyMerchant(@RequestParam long phone, @RequestParam String password) {
		return merchantservice.verifyMerchant(phone, password);
	}
	
	@GetMapping("/find-by-name/{name}")
	public ResponseEntity<ResponseStructure> findbyname(@PathVariable String name){
	return merchantservice.findbyname(name);	
	}	

	
}


