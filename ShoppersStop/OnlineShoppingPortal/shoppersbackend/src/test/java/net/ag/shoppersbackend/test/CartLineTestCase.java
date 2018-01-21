package net.ag.shoppersbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import net.ag.shoppersbackend.dao.CartLineDAO;
import net.ag.shoppersbackend.dao.ProductDAO;
import net.ag.shoppersbackend.dao.UserDAO;
import net.ag.shoppersbackend.dto.Cart;
import net.ag.shoppersbackend.dto.CartLine;
import net.ag.shoppersbackend.dto.Product;
import net.ag.shoppersbackend.dto.User;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartLineDAO = null;
	private static ProductDAO productDAO = null;
	private static UserDAO userDAO = null;
	
	private Product product = null;
	private User user = null;
	private Cart cart = null;
	private CartLine cartLine = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.ag.shoppersbackend");
		context.refresh();
		productDAO = (ProductDAO)context.getBean("productDAO");
		userDAO = (UserDAO)context.getBean("userDAO");
		cartLineDAO = (CartLineDAO)context.getBean("cartLineDAO");
	  }
	
	@Test
	public void testAddNewCartLine() {
		
		//get the user 
	    user = userDAO.getByEmail("amit@gmail.com");
		//get the cart
	    cart = user.getCart();
	    //get the product
	    product = productDAO.get(1);
	    //create a new cart line
	    cartLine = new CartLine();
	    
	    cartLine.setBuyingPrice(product.getUnitPrice());
	   
	    cartLine.setProductCount(cartLine.getProductCount() + 1);
	    
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitPrice());
		
		cartLine.setAvailable(true);
		
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		assertEquals("Failed to add to the cart line", true, cartLineDAO.add(cartLine));
		
		cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
		cart.setCartLines(cart.getCartLines() + 1);
	    
		assertEquals("Failed to update the cart", true, cartLineDAO.updateCart(cart));

   }
	
	
  }
