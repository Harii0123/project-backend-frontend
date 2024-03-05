package org.jsp.ecommerceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.User;
import org.jsp.ecommerceapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
	@Autowired
	private UserRepository userrepository;
	
	public User saveuser(User user) {
		return userrepository.save(user);
	}
	
	public User updateuser(User user) {
		return userrepository.save(user);
	}
	
	public Optional<User> findbyid(int id) {
		return userrepository.findById(id);
	}
	public boolean deletebyid(int id) {
		Optional<User> dbuser = findbyid(id);
		if (dbuser.isPresent()) {
			userrepository.delete(dbuser.get());
			return true;
		}
		return false;

	}

	public List<User> findall() {
		return userrepository.findAll();
	}
	public Optional<User> verifyuser(long phone, String password) {
		return userrepository.verify(phone, password);
	}

	public Optional<User> verifyuserbyemail(String email, String password) {
		return userrepository.verify(email, password);
	}
	public List<User> findbyname(String name) {
		return userrepository.findbyname(name);
	}
}


