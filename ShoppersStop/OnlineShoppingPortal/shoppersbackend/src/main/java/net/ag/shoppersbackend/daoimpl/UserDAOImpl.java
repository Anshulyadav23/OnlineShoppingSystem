package net.ag.shoppersbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.ag.shoppersbackend.dao.UserDAO;
import net.ag.shoppersbackend.dto.Address;
import net.ag.shoppersbackend.dto.Cart;
import net.ag.shoppersbackend.dto.ExtraOrder;
import net.ag.shoppersbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean addUser(User user) {
		 try {
			 sessionFactory.getCurrentSession().persist(user);
			 return true;
		 	}
		 	 catch(Exception ex) 
		 {
			 ex.printStackTrace();
			 return false;
		 }
		
	}

	@Override
	public boolean addAddress(Address address) {
		 try {
			 sessionFactory.getCurrentSession().persist(address);
			 return true;
		 	}
		 	 catch(Exception ex) 
		 {
			 ex.printStackTrace();
			 return false;
		 }
	}



	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email = :email";
		 try {
			User user= sessionFactory.getCurrentSession().
					createQuery(selectQuery, User.class).
					setParameter("email", email).
					getSingleResult();
			
			//System.out.println(user.getCart().toString());
			return user;
		 }
		 	catch(Exception ex) {
			//ex.printStackTrace();
			return null;
		}
	 }

	@Override
	public Address getBillingAddress(int userId) {
		String selectQuery = "FROM Address WHERE userId = :userId AND billing = :isBilling";
		try{
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Address.class)
						.setParameter("userId", userId)
						.setParameter("isBilling", true)
						.getSingleResult();
		}
		catch(Exception ex) {
			return null;
		}
	}

	@Override
	public List<Address> listShippingAddresses(int userId) {
		String selectQuery = "FROM Address WHERE userId = :userId AND shipping = :isShipping ORDER BY id DESC";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Address.class)
						.setParameter("userId", userId)
						.setParameter("isShipping", true)
							.getResultList();
		
	}

	@Override
	public User get(int id) {
		try {			
			return sessionFactory.getCurrentSession().get(User.class, id);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public Address getAddress(int addressId) {
		try {			
			return sessionFactory.getCurrentSession().get(Address.class, addressId);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	@Override
	public boolean updateAddress(Address address) {
		try {			
			sessionFactory.getCurrentSession().update(address);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public List<ExtraOrder> getBackOrder() {
		String selectQuery = "FROM ExtraOrder";
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,ExtraOrder.class)
							.getResultList();
	}

	@Override
	public ExtraOrder getBackOrder(int id) {
		try {			
			return sessionFactory.getCurrentSession().get(ExtraOrder.class, id);			
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

}
