package org.jsp.ecommerceapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.dao.UserDao;
import org.jsp.ecommerceapp.dto.ResponseStructure;
import org.jsp.ecommerceapp.exception.MerchantNotFoundException;
import org.jsp.ecommerceapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	@Autowired
	private UserDao userdao;
	
	public ResponseEntity<ResponseStructure<User>>saveuser(User user){
		ResponseStructure<User>structure=new ResponseStructure<>();
		structure.setBody(userdao.saveuser(user));
		structure.setMessage("user saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<User>>(structure,HttpStatus.CREATED);
		
	}
	public ResponseEntity<ResponseStructure> updateuser(User user) {
		Optional<User>recuser=userdao.findbyid(user.getId());
		ResponseStructure<User> structure=new ResponseStructure();
		if(recuser.isPresent()) {
			User dbuser=recuser.get();
			dbuser.setEmail(user.getEmail());
			dbuser.setName(user.getName());
			dbuser.setPassword(user.getPassword());
			dbuser.setPhone(user.getPhone());
			dbuser.setGender(user.getGender());
			structure.setMessage("user updated");
			structure.setBody(userdao.saveuser(user));
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED) ;
		}
		return new ResponseEntity<ResponseStructure>(structure, HttpStatus.NOT_FOUND) ;
	}
	public ResponseEntity<ResponseStructure> findbyid(int id) {
		Optional<User>recuser=userdao.findbyid(id);
		ResponseStructure<User> structure=new ResponseStructure();
		if(recuser.isPresent()) {
			structure.setMessage("user updated");
			structure.setBody(recuser.get());
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ; 
		}
		throw new MerchantNotFoundException(null); 
	}
	public ResponseEntity<ResponseStructure> deletebyid(int id) {
		Optional<User>recuser=userdao.findbyid(id);
		ResponseStructure<String> structure=new ResponseStructure();
		if(recuser.isPresent()) {
		    userdao.deletebyid(id);
			structure.setMessage("user deleted");
			structure.setBody("deleted successfully");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		throw new MerchantNotFoundException(null);
	}
	
	public ResponseEntity<ResponseStructure> findall(){
		ResponseStructure<List<User>> structure=new ResponseStructure();
		List<User>recuser=userdao.findall();
		
		if(recuser.size()>0) {
			structure.setMessage("user find");
			structure.setBody(recuser);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		structure.setMessage("user not Find");
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure>(structure, HttpStatus.NOT_FOUND) ;
	}
	public ResponseEntity<ResponseStructure> verifyuser( long phone, String password) {
		Optional<User>recuser=userdao.verifyuser(phone, password);
		ResponseStructure<User> structure=new ResponseStructure();
		if(recuser.isPresent()) {
			structure.setMessage("user find");
			structure.setBody(recuser.get());
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED) ; 
		}
		return new ResponseEntity<ResponseStructure>(structure, HttpStatus.NOT_FOUND) ; 
	}
	
	public ResponseEntity<ResponseStructure> verifyuserbyemail( String email, String password) {
		Optional<User>recuser=userdao.verifyuserbyemail(email, password);
		ResponseStructure<User> structure=new ResponseStructure();
		if(recuser.isPresent()) {
			structure.setMessage("user find");
			structure.setBody(recuser.get());
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.ACCEPTED) ; 
		}
		return new ResponseEntity<ResponseStructure>(structure, HttpStatus.NOT_FOUND) ; 
	}
	
	public ResponseEntity<ResponseStructure> findbyname(String name){
		ResponseStructure<List<User>> structure=new ResponseStructure();
		List<User>recuser=userdao.findbyname(name);
		if(recuser.size()>0) {
			structure.setMessage("user find");
			structure.setBody(recuser);
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure>(structure, HttpStatus.OK) ;
		}
		throw new MerchantNotFoundException(null);
}
}


