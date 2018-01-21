package net.ag.shoppersbackend.dao;

import java.util.List;

import net.ag.shoppersbackend.dto.Address;
import net.ag.shoppersbackend.dto.Cart;
import net.ag.shoppersbackend.dto.ExtraOrder;
import net.ag.shoppersbackend.dto.User;

public interface UserDAO {
	
	// add an user 
	boolean addUser(User user);
	
	User getByEmail(String email);
	
	// add an address
	boolean addAddress(Address address);
	
	//alternative
	Address getBillingAddress(int userId);
	List<Address> listShippingAddresses(int userId);
	 
	//Address getBillingAddress(User user);
	//List<Address> listShippingAddresses(User user);
	
     User get(int id);
 
	 Address getAddress(int addressId);
		 
	 boolean updateAddress(Address address);
	 
	 List<ExtraOrder> getBackOrder();
	 
	 ExtraOrder getBackOrder(int id);
	
	
	
	
	
}
