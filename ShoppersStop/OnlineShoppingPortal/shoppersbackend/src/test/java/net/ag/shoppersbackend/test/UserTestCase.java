package net.ag.shoppersbackend.test;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import net.ag.shoppersbackend.dao.UserDAO;
import net.ag.shoppersbackend.dto.Address;
import net.ag.shoppersbackend.dto.Cart;
import net.ag.shoppersbackend.dto.User;

public class UserTestCase {
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private User user=null;
	private Cart cart=null;
	private Address address=null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.ag.shoppersbackend");
		context.refresh();
		userDAO = (UserDAO)context.getBean("userDAO");
	}
/*	
	@Test
	public void testAdd() {
		user = new User();
		user.setFirstName("Anshul");
		user.setLastName("Gupta");
		user.setEmail("anshul8083@gmail.com");
		user.setContactNumber("6602382090");
		user.setRole("USER");
		user.setPassword("12345");
		
		// add the user
		assertEquals("Failed to add user" ,true, userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("410, franklin ST");
		address.setAddressLineTwo("Ave Apt-A");
		address.setCity("Warrensburg");
		address.setState("Missouri");
		address.setCountry("USA");
		address.setPostalCode("64093");
		address.setBilling(true);
		
		// link the user with the address using userId
		
		address.setUserId(user.getId());
		
		//add the address
		assertEquals("Failed to add address", true, userDAO.addAddress(address));
		
		if(user.getRole().equals("USER")){
			// create cart for this user
			
			cart = new Cart();
			cart.setUser(user);
			
			//add the cart
			assertEquals("Failed to add cart", true, userDAO.addCart(cart));
			
			//add a shipping address for this user
			
			address = new Address();
			address.setAddressLineOne("410, franklin ST");
			address.setAddressLineTwo("Ave Apt-A");
			address.setCity("Warrensburg");
			address.setState("Missouri");
			address.setCountry("USA");
			address.setPostalCode("64093");
			//set shipping to true
			address.setShipping(true);
			
			//link it with the user
			address.setUserId(user.getId());
			
			//add the shipping address
			assertEquals("Failed to add shipping address", true, userDAO.addAddress(address));
		}
		
	 }
	*/
	
/*	@Test
	public void testAdd() {
		user = new User();
		user.setFirstName("Anshul");
		user.setLastName("Gupta");
		user.setEmail("anshul8083@gmail.com");
		user.setContactNumber("6602382090");
		user.setRole("USER");
		user.setPassword("12345");
		
	     if(user.getRole().equals("USER")){
			// create cart for this user
			
			 cart = new Cart();
			 cart.setUser(user);
			 
			 //attach cart with the user
			 
			 user.setCart(cart);
			 
		}
	     //add the user
	     assertEquals("failed to add user", true, userDAO.addUser(user));
		
 	}
	*/
    
	
	/*@Test
	public void testUpdateCart() {
		//fetching user by email id
		user = userDAO.getByEmail("rj@gmail.com");
	 
		 //get the cart of the user
		cart = user.getCart();
		//System.out.println(cart.toString());
		 
		cart.setGrandTotal(111);
		
	   cart.setCartLines(2);
		
		
	//	assertEquals("failed to update the cart", true, userDAO.updateCart(cart));
		
	}
	*/
	
/*  @Test
	public void testAddAddress() {
		
		//adding a new user
		
		user = new User();
		user.setFirstName("Anshul");
		user.setLastName("Gupta");
		user.setEmail("anshul8083@gmail.com");
		user.setContactNumber("6602382090");
		user.setRole("USER");
		user.setPassword("12345");
		
		// add the user
		assertEquals("Failed to add user" ,true, userDAO.addUser(user));
		
		address = new Address();
		address.setAddressLineOne("410, franklin ST");
		address.setAddressLineTwo("Ave Apt-A");
		address.setCity("Warrensburg");
		address.setState("Missouri");
		address.setCountry("USA");
		address.setPostalCode("64093");
		address.setBilling(true);
		
		// attach user with address
		 address.setUser(user);
		
		//add the address
		assertEquals("Failed to add address", true, userDAO.addAddress(address));
	 // add the shipping address
		address = new Address();
		address.setAddressLineOne("410, franklin ST");
		address.setAddressLineTwo("Ave Apt-A");
		address.setCity("Warrensburg");
		address.setState("Missouri");
		address.setCountry("USA");
		address.setPostalCode("64093");
		//set shipping to true
		address.setShipping(true);
		address.setUser(user);
		
		assertEquals("Failed to add shipping address", true, userDAO.addAddress(address));
		
		
	}*/
	
	/*@Test
	public void testAddAddress() {
		 
		user = userDAO.getByEmail("ag@gmail.com");
		
		 // add the shipping address
			address = new Address();
			address.setAddressLineOne("410, franklin ST");
			address.setAddressLineTwo("Ave Apt-A");
			address.setCity("Biaora");
			address.setState("MP");
			address.setCountry("India");
			address.setPostalCode("64093");
			//set shipping to true
			address.setShipping(true);
			address.setUser(user);
			
			assertEquals("Failed to add shipping address", true, userDAO.addAddress(address));
 }
	*/
	
	@Test
	public void testGetAddresses() {
		user = userDAO.getByEmail("anshul8083@gmail.com");
		
		assertEquals("Failed to fetch the list of address and size doesnot match",2,
				//userDAO.listShippingAddresses(user).size());
				userDAO.listShippingAddresses(user.getId()).size());
		
		// assertEquals("Failed to fetch the billing address and size doesnot match","USA",
		//		userDAO.getBillingAddress(user).getCountry());

		
	}

	
	
	

}
