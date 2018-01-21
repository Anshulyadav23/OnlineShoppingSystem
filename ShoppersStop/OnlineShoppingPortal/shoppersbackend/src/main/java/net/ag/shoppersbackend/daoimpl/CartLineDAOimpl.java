package net.ag.shoppersbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.ag.shoppersbackend.dao.CartLineDAO;
import net.ag.shoppersbackend.dto.Address;
import net.ag.shoppersbackend.dto.BackOrderCount;
import net.ag.shoppersbackend.dto.Cart;
import net.ag.shoppersbackend.dto.CartLine;
import net.ag.shoppersbackend.dto.ExtraOrder;
import net.ag.shoppersbackend.dto.Product;
 
@Repository("cartLineDAO")
@Transactional
public class CartLineDAOimpl implements CartLineDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String query = "FROM CartLine WHERE cartId = :cartId AND product.id = :productId";
		try {
			
			return sessionFactory.getCurrentSession()
									.createQuery(query,CartLine.class)
										.setParameter("cartId", cartId)
										.setParameter("productId", productId)
											.getSingleResult();
			
		}catch(Exception ex) {
			return null;	
		}
		
	}

	@Override
	public boolean add(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().persist(cartLine);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartLine) {
		try {
			sessionFactory.getCurrentSession().update(cartLine);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean remove(CartLine cartLine) {	
		try {			
			sessionFactory.getCurrentSession().delete(cartLine);
			return true;
		}catch(Exception ex) {
			return false;
		}		
	}


	@Override
	public List<CartLine> list(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId";
		return sessionFactory.getCurrentSession()
								.createQuery(query, CartLine.class)
									.setParameter("cartId", cartId)
										.getResultList();		
	}

	@Override
	public CartLine get(int id) {		
		return sessionFactory.getCurrentSession().get(CartLine.class, Integer.valueOf(id));
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {			
			sessionFactory.getCurrentSession().update(cart);			
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId AND available = :available";
		return sessionFactory.getCurrentSession()
								.createQuery(query, CartLine.class)
									.setParameter("cartId", cartId)
									.setParameter("available", true)
										.getResultList();
	}

	
	@Override
	public Address getUserAddress(String userid) {
		String query = "FROM Address WHERE userId = :userid";
		return sessionFactory.getCurrentSession()
								.createQuery(query, Address.class)
									.setParameter("userid", Integer.parseInt(userid))
										.getResultList().iterator().next();
	}

	@Override
	public Product getProduct(String productid) {
		String query = "FROM Product WHERE id = :productid";
		return sessionFactory.getCurrentSession()
								.createQuery(query, Product.class)
									.setParameter("productid", productid)
										.getResultList().iterator().next();
	}

	@Override
	public List<CartLine> getCartLineByProduct(int productid) {
		String query = "FROM CartLine as C WHERE C.product.id = :productId";
		return sessionFactory.getCurrentSession()
								.createQuery(query, CartLine.class)
									.setParameter("productId", productid)
										.getResultList();
	}

	@Override
	public Cart getCartById(int id) {
		String query = "FROM Cart WHERE id = :cartLineId";
		
		
		List<Cart> data=sessionFactory.getCurrentSession()
		.createQuery(query, Cart.class)
			.setParameter("cartLineId", id)
				.getResultList();
		
		Cart cart=null;
		if(!data.isEmpty())
			cart=data.iterator().next();
		
		
		return cart;
				
	}

	@Override
	public boolean addExtraOrder(ExtraOrder extraOrder) {
		try {
			sessionFactory.getCurrentSession().persist(extraOrder);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<ExtraOrder> getBackOrder(int productId) {
		String query = "FROM ExtraOrder as E WHERE E.product.id = :productId";
		return sessionFactory.getCurrentSession()
								.createQuery(query, ExtraOrder.class)
									.setParameter("productId",productId)
										.getResultList();
	}

	@Override
	public boolean deleteExtraOrder(ExtraOrder extraOrder) {
		try {			
			sessionFactory.getCurrentSession().delete(extraOrder);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean addBackOrderCount(BackOrderCount backOrderCount) {
		try {
			sessionFactory.getCurrentSession().persist(backOrderCount);
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteBackOrderCount(BackOrderCount backOrderCount) {
		try {			
			sessionFactory.getCurrentSession().delete(backOrderCount);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public BackOrderCount getBackOrderCount(int cartlineID) {
		String query = "FROM BackOrderCount as B WHERE B.cartLine.id = :cartlineID";
		List<BackOrderCount> backOrderCount=sessionFactory.getCurrentSession()
				.createQuery(query, BackOrderCount.class)
				.setParameter("cartlineID",cartlineID)
					.getResultList();
		
		if(backOrderCount.isEmpty())
			return null;
		
		return backOrderCount.iterator().next();
				
	}

	 
	}

	
		

