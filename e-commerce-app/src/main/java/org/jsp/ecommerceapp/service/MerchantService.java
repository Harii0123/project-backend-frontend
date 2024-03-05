package org.jsp.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.MerchantDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exception.IdNotFoundException;
import org.jsp.ecommerceapp.exception.InvalidCredentialException;
import org.jsp.ecommerceapp.exception.MerchantNotFoundException;
import org.jsp.ecommerceapp.model.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MerchantService {
	@Autowired
	private MerchantDao merchantdao;
	public ResponseEntity<ResponseStructure<Merchant>>savemerchant(Merchant merchant){
		ResponseStructure<Merchant>structure=new ResponseStructure<>();
		structure.setBody(merchantdao.savemerchant(merchant));
		structure.setMessage("merchant saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Merchant>>(structure,HttpStatus.CREATED);
		
	}
	public ResponseEntity<ResponseStructure> updatemerchant( Merchant merchant) {
		Optional<Merchant>recMerchant=merchantdao.findbyid(merchant.getId());
		ResponseStructure<Merchant> structure=new ResponseStructure();
		if(recMerchant.isPresent()) {
			Merchant dbMerchant=recMerchant.get();
			dbMerchant.setEmail(merchant.getEmail());
			dbMerchant.setGst_number(merchant.getGst_number());
			dbMerchant.setName(merchant.getName());
			dbMerchant.setPassword(merchant.getPassword());
			dbMerchant.setPhone(merchant.getPhone());
			structure.setMessage("Merchant updated");
			structure.setBody(merchantdao.savemerchant(merchant));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED) ;
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure> findbyid(int id) {
		Optional<Merchant>recMerchant=merchantdao.findbyid(id);
		ResponseStructure<Merchant> structure=new ResponseStructure();
		if(recMerchant.isPresent()) {
			structure.setMessage("Merchant updated");
			structure.setBody(recMerchant.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ; 
		}
		throw new IdNotFoundException();
	}
	public ResponseEntity<ResponseStructure> deletebyid(int id) {
		Optional<Merchant>recMerchant=merchantdao.findbyid(id);
		ResponseStructure<String> structure=new ResponseStructure();
		if(recMerchant.isPresent()) {
			merchantdao.deletebyid(id);
			structure.setMessage("Merchant deleted");
			structure.setBody("deleted successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		throw new MerchantNotFoundException(null);
	}
	
	public ResponseEntity<ResponseStructure> findall(){
		ResponseStructure<List<Merchant>> structure=new ResponseStructure();
		List<Merchant>recMerchant=merchantdao.findall();
		
		if(recMerchant.size()>0) {
			structure.setMessage("Merchant find");
			structure.setBody(recMerchant);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		throw new MerchantNotFoundException(null);
	}
	public ResponseEntity<ResponseStructure> verifyMerchant( long phone, String password) {
		Optional<Merchant>recMerchant=merchantdao.verifyMerchant(phone,password);
		ResponseStructure<Merchant> structure=new ResponseStructure();
		if(recMerchant.isPresent()) {
			structure.setMessage("method find");
			structure.setBody(recMerchant.get());
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED) ; 
		}
		throw new InvalidCredentialException("wrong phone or password");
	}
	
	
	public ResponseEntity<ResponseStructure> findbyname(String name){
		ResponseStructure<List<Merchant>> structure=new ResponseStructure();
		List<Merchant>recMerchant=merchantdao.findbyname(name);
		if(recMerchant.size()>0) {
			structure.setMessage("Merchant find");
			structure.setBody(recMerchant);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		throw new MerchantNotFoundException(null);
	}

}


