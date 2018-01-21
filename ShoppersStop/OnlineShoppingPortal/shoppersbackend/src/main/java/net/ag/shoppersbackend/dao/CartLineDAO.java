package net.ag.shoppersbackend.dao;

import java.util.List;

import net.ag.shoppersbackend.dto.Address;
import net.ag.shoppersbackend.dto.BackOrderCount;
import net.ag.shoppersbackend.dto.Cart;
import net.ag.shoppersbackend.dto.CartLine;
import net.ag.shoppersbackend.dto.ExtraOrder;
import net.ag.shoppersbackend.dto.Product;
 
public interface CartLineDAO {


	public List<CartLine> list(int cartId);
	public CartLine get(int id);	
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean remove(CartLine cartLine);
	
	// fetch the CartLine based on cartId and productId
	public CartLine getByCartAndProduct(int cartId, int productId);		
		
	// updating the cart
	boolean updateCart(Cart cart);
	
	// list of available cartLine
	public List<CartLine> listAvailable(int cartId);
	
	public Address getUserAddress(String userid);
	
	public Product getProduct(String productid);
	
	public List<CartLine> getCartLineByProduct(int productid);
	
	public Cart getCartById(int id);
	
	public boolean addExtraOrder(ExtraOrder extraOrder);
	public List<ExtraOrder> getBackOrder(int productId);
	public boolean deleteExtraOrder(ExtraOrder extraOrder);
	public boolean addBackOrderCount(BackOrderCount backOrderCount);
	public boolean deleteBackOrderCount(BackOrderCount backOrderCount);
	public BackOrderCount getBackOrderCount(int cartlineID);
	
	
	
	
}
