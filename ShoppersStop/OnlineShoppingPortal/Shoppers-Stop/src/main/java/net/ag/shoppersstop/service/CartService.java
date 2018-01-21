package net.ag.shoppersstop.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.ag.shoppersbackend.dao.CartLineDAO;
import net.ag.shoppersbackend.dao.ProductDAO;
import net.ag.shoppersbackend.dto.Address;
import net.ag.shoppersbackend.dto.BackOrderCount;
import net.ag.shoppersbackend.dto.Cart;
import net.ag.shoppersbackend.dto.CartLine;
import net.ag.shoppersbackend.dto.ExtraOrder;
import net.ag.shoppersbackend.dto.Product;
import net.ag.shoppersbackend.dto.User;
import net.ag.shoppersstop.model.UserModel;

@Service("cartService")
public class CartService {

	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;
	
	//returns the cart of the user who has logged in
	public Cart getCart() {
		
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	public CartLine get(int id){
		 
		return cartLineDAO.get(id);
		
	}
	
	public List<CartLine> getCartLines(){
	 
		return cartLineDAO.list(this.getCart().getId());
		
	}
	
	public boolean updateCart(Cart cart){
		 
		return cartLineDAO.updateCart(cart);
		
	}
	
	public List<CartLine> getCartLinesByCartId(int cartid){
		 
		return cartLineDAO.list(this.getCart().getId());
		
	}
	
	public boolean updateProduct(Product product)
	{
		return productDAO.update(product);
	}

	public String updateCartLine(int cartLineId, int count) {
		 //fetch the cart line
		
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine==null) {
			return "result=error";
		 }
		else {
			
			Product product = cartLine.getProduct();
			
			double oldTotal  = cartLine.getTotal();
			
			if(product.getQuantity() <= count) {
				cartLine.setTotal(product.getUnitPrice()*product.getQuantity());
		 
			 }
			else
			{
				cartLine.setTotal(product.getUnitPrice()*count);
				//product.setQuantity(product.getQuantity()-count);
			}
			
			cartLine.setProductCount(count);
			
			cartLine.setBuyingPrice(product.getUnitPrice());
			
			
			cartLineDAO.update(cartLine);
			
			Cart cart = this.getCart();
			
			System.out.println(cart);
			
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			
			cartLineDAO.updateCart(cart);
			productDAO.update(product);
			
			return "result=updated";
			
			
 		}
		
	}

	public String deleteCartLine(int cartLineId) {
		
		//fetch the cart line
		
		CartLine cartLine  = cartLineDAO.get(cartLineId);
		
		if(cartLine == null) {
			return "result=error";
		 }
		else {
			
			//update the cart
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);
			//remove the cart line
			cartLineDAO.deleteBackOrderCount(cartLineDAO.getBackOrderCount(cartLine.getId()));
			cartLineDAO.remove(cartLine);
			return "result=deleted";
		
		}
	}
		 
		
		public String deleteCartLine(int cartLineId, Cart cart) {
			
			//fetch the cart line
			
			CartLine cartLine  = cartLineDAO.get(cartLineId);
			
			if(cartLine == null) {
				return "result=error";
			 }
			else {
				
				//update the cart
				
				cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
				cart.setCartLines(cart.getCartLines() - 1);
				cartLineDAO.updateCart(cart);
				//remove the cart line
				cartLineDAO.deleteBackOrderCount(cartLineDAO.getBackOrderCount(cartLine.getId()));
				cartLineDAO.remove(cartLine);
				
				return "result=deleted";
			}
		
		
		
	}

	public String addCartLine(int productId) {
	 
		String response = null;
		
		Cart cart = this.getCart();
		
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine == null) {
			
			//add new cart line
			cartLine = new CartLine();
			
			Product product  = productDAO.get(productId);
			
			cartLine.setCartId(cart.getId());
			
			cartLine.setProduct(product);
			
			cartLine.setBuyingPrice(product.getUnitPrice());
			
			cartLine.setProductCount(1);
			
			cartLine.setTotal(product.getUnitPrice());
			
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			
		    cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			
			cart.setCartLines(cart.getCartLines() + 1);
			
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
		}
		
	 	return response;
		
	 }
	
	public String addCartLine(int productId,Cart usercart) {
		 
		String response = null;
		
		Cart cart = usercart;
		
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine == null) {
			
			//add new cart line
			cartLine = new CartLine();
			
			Product product  = productDAO.get(productId);
			
			cartLine.setCartId(cart.getId());
			
			cartLine.setProduct(product);
			
			cartLine.setBuyingPrice(product.getUnitPrice());
			
			cartLine.setProductCount(1);
			
			cartLine.setTotal(product.getUnitPrice());
			
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			
		    cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			
			cart.setCartLines(cart.getCartLines() + 1);
			
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
		}
		
	 	return response;
		
	 }
	
	public String addCartLine(int productId,Cart usercart,int count) {
		 
		String response = null;
		
		Cart cart = usercart;
		
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine == null) {
			
			//add new cart line
			cartLine = new CartLine();
			
			Product product  = productDAO.get(productId);
			
			cartLine.setCartId(cart.getId());
			
			cartLine.setProduct(product);
			
			cartLine.setBuyingPrice(product.getUnitPrice());
			
			cartLine.setProductCount(count);
			
			cartLine.setTotal(product.getUnitPrice()*count);
			
			cartLine.setAvailable(true);
			
			cartLineDAO.add(cartLine);
			
		    cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			
			cart.setCartLines(cart.getCartLines() + 1);
			
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
		}
		
	 	return response;
		
	 }
	
	
	public Address getUserAddress()
	{
		Cart cart=this.getCart();
		User user=cart.getUser();
		Address address=cartLineDAO.getUserAddress(user.getId()+"");
		System.out.println(address.toString());
		return address;
		
	}
	
	public List<Product> getProductList()
	{
		Cart cart=this.getCart();
		User user=cart.getUser();
		
		List<CartLine> list=getCartLines();
		List<Product> products=new ArrayList<>();
		for (CartLine cartLine : list) {
			Product product=cartLine.getProduct();
			products.add(product);
		}
		
		return products;
	}
	
	public List<CartLine> getCartLine()
	{
		Cart cart=this.getCart();
		User user=cart.getUser();
		
		List<CartLine> list=getCartLines();
		
		return list;
	}
	
	public List<ExtraOrder> getBackOrder(int productId) 
	{
		return cartLineDAO.getBackOrder(productId);
	}
	
	public boolean addExtraOrder(ExtraOrder extraOrder)
	{
		return cartLineDAO.addExtraOrder(extraOrder);
	}
	
	public boolean deleteExtraOrder(ExtraOrder extraOrder)
	{
		return cartLineDAO.deleteExtraOrder(extraOrder);
	}
	
	
	public boolean addBackOrderCount(BackOrderCount backOrderCount) {
		return cartLineDAO.addBackOrderCount(backOrderCount);
	}

	public boolean deleteBackOrderCount(BackOrderCount backOrderCount) {
		return cartLineDAO.deleteBackOrderCount(backOrderCount);
	}

	public BackOrderCount getBackOrderCount(int cartlineID) {
		return cartLineDAO.getBackOrderCount(cartlineID);
	}
	
}
