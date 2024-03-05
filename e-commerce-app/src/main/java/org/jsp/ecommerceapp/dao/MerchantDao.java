package org.jsp.ecommerceapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.ecommerceapp.model.Merchant;
import org.jsp.ecommerceapp.repository.MerchantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MerchantDao {
	@Autowired
	private MerchantRepository merchantrepository;
	public Merchant savemerchant(Merchant merchant) {
		return merchantrepository.save(merchant);
	} 
	public Merchant updatemerchant(Merchant merchant) {
		return merchantrepository.save(merchant);
	}
	public Optional<Merchant> findbyid(int id) {
		return merchantrepository.findById(id);
	}
	public boolean deletebyid(int id) {
		Optional<Merchant> dbMerchant = findbyid(id);
		if (dbMerchant.isPresent()) {
			merchantrepository.delete(dbMerchant.get());
			return true;
		}
		return false;

	}

	public List<Merchant> findall() {
		return merchantrepository.findAll();
	}
	public Optional<Merchant> verifyMerchant(long phone, String password) {
		return merchantrepository.verify(phone, password);
	}

	public List<Merchant> findbyname(String name) {
		return merchantrepository.findbyname(name);
	}
}

