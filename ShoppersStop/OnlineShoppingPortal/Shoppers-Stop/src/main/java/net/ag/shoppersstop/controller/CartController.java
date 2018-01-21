package net.ag.shoppersstop.controller;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import net.ag.shoppersbackend.dao.CartLineDAO;
import net.ag.shoppersbackend.dao.ProductDAO;
import net.ag.shoppersbackend.dao.UserDAO;
import net.ag.shoppersbackend.dto.Address;
import net.ag.shoppersbackend.dto.BackOrderCount;
import net.ag.shoppersbackend.dto.Cart;
import net.ag.shoppersbackend.dto.CartLine;
import net.ag.shoppersbackend.dto.ExtraOrder;
import net.ag.shoppersbackend.dto.Product;
import net.ag.shoppersbackend.dto.User;
import net.ag.shoppersstop.model.UserModel;
import net.ag.shoppersstop.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	
	int productquantity=0,cartlinequantity=0;
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/show")
	public ModelAndView showCart(@RequestParam(name = "result", required=false)String result) {
		
		ModelAndView mv = new ModelAndView("page");
		
		if(result!=null) {
			
			switch(result) {
			
			case "updated":
				mv.addObject("message", "Cart Line has been updated successfully");
				break;
			case "added":
				mv.addObject("message", "Cart Line has been updated successfully");
				break;
            case "deleted":
				mv.addObject("message", "Cart Line has been deleted successfully");
				break;
			case "error":
				mv.addObject("message", "Something went wrong");
				break;
			case "insufficient":
				mv.addObject("message", "product available "+productquantity+" and product ordered "+cartlinequantity+" that's why we are only charging for "+productquantity+" and will notify you once the product is in our stock please check your e-mail and confirm your order");
				mv.addObject("remain", cartlinequantity-productquantity);
				break;
			 }
			
		 }
		mv.addObject("title", "User Cart");
		mv.addObject("userClickShowCart", true);
		mv.addObject("cartLines", cartService.getCartLines());
        return mv;
	}
	
	//Refresh the cart
	
	@RequestMapping("/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId, @RequestParam int count)
	{
		CartLine cartLine=cartService.get(cartLineId);
		Product product=cartLine.getProduct();
		String response = "";
		if(product.getQuantity()<count)
		{
			productquantity=product.getQuantity();
			cartlinequantity=count;
			cartService.updateCartLine(cartLineId, product.getQuantity());
			BackOrderCount backOrderCount=new BackOrderCount();
			backOrderCount.setCartLine(cartService.get(cartLineId));
			backOrderCount.setProductCount(cartlinequantity-productquantity);
			cartService.addBackOrderCount(backOrderCount);
			return "redirect:/cart/show?result=insufficient";
		}
		else
			response=cartService.updateCartLine(cartLineId, count);
		
		
		return "redirect:/cart/show?"+response;
		
	 }
	

	@RequestMapping("/{cartLineId}/delete")
	public String updateCart(@PathVariable int cartLineId)
	{
		String response = cartService.deleteCartLine(cartLineId);
		
		return "redirect:/cart/show?"+response;
		
	 }
	

	@RequestMapping("/add/{productId}/product")
	public String addCart(@PathVariable int productId)
	{
		String response = cartService.addCartLine(productId);
		
		return "redirect:/cart/show?"+response;
	 }
	
	@RequestMapping("/orderconfirm")
	public ModelAndView orderConfirm()
	{
		//String response = cartService.addCartLine(productId);
		ModelAndView mv = new ModelAndView("orderconfirmation");	
		return mv;
		
	 }
	
	//if user clicked yes
	
	@RequestMapping("/confirmbackorder")
	public ModelAndView confirmBackOrder(HttpServletRequest request) throws MalformedURLException
	{
		String userid=request.getParameter("user");
		User user=userDAO.get(Integer.parseInt(userid));
		Cart cart=user.getCart();
		List<CartLine> cartlines=cartService.getCartLinesByCartId(cart.getId());
		
		for (CartLine cartLine : cartlines) {
			BackOrderCount backOrderCount=cartService.getBackOrderCount(cartLine.getId());
			Product product=cartLine.getProduct();
			  
			if(backOrderCount!=null)
			{
		      ExtraOrder extraOrder=new ExtraOrder();
		 	  extraOrder.setProduct(product);
			  extraOrder.setUser(user);
			  extraOrder.setProductCount(backOrderCount.getProductCount());
			  cartService.addExtraOrder(extraOrder);
			}
			product.setQuantity(0);
			productDAO.update(product);
			cartService.deleteCartLine(cartLine.getId());
		}
		
		
		cart.setGrandTotal(0);
		cart.setCartLines(0);
		cartService.updateCart(cart);
	    //String response = cartService.addCartLine(productId);
		ModelAndView mv = new ModelAndView("orderconfirmation");	
		return mv;
		
	 }
	
	//if user wants to cancel whole order
	
	@RequestMapping("/cancelOrder")
	public ModelAndView cancelOrder(HttpServletRequest request)
	{
		String userid=request.getParameter("user");
		User user=userDAO.get(Integer.parseInt(userid));
		Cart cart=user.getCart();
		List<CartLine> cartlines=cartService.getCartLinesByCartId(cart.getId());
		//Cart cart2=null;
		for (CartLine cartLine : cartlines) {
			cartService.deleteCartLine(cartLine.getId());
		}
		
		cart.setGrandTotal(0);
		cart.setCartLines(0);
		cartService.updateCart(cart);
		//String response = cartService.addCartLine(productId);
		ModelAndView mv = new ModelAndView("orderconfirmation");	
		return mv;
		
	 }
	
	
	
	@RequestMapping("/backorder")
	public ModelAndView backorder(HttpServletRequest request)
	{
		String user=request.getParameter("user");
		//String response = cartService.addCartLine(productId);
		ModelAndView mv = new ModelAndView("backorderconfirm");	
		mv.addObject("user", user);
		return mv;
		
	 }
	
	@RequestMapping("/listproducts")
	public ModelAndView listproducts()
	{
		//String response = cartService.addCartLine(productId);
		ModelAndView mv = new ModelAndView("listproducts");	
		return mv;
		
	 }
	
	//condition validated for sending mail
	
	@RequestMapping("/validate")
	public ModelAndView validate(@RequestParam String remain) throws MalformedURLException
	{
		ModelAndView mv = new ModelAndView("order-address");	
		UserModel user=(UserModel)session.getAttribute("userModel");
		Cart  cart=user.getCart();
		Address address = cartService.getUserAddress();
		mv.addObject("address", address);
		boolean remaincheck=false, shipcheck=false;
		mv.addObject("products",cartService.getProductList());
		List<CartLine> cartline=cartService.getCartLine();
		mv.addObject("cartline",cartline );
		String remainmessage="Dear Customer, Click on the below link to confirm your back order of ";
		String shippedmessage="Dear Customer, The admin has send the Invoice to let you know that following product ";
		double totalamount=0;
		for (CartLine cartLine2 : cartline) {
			Product  product=cartLine2.getProduct();
			if(product.getQuantity()<=cartLine2.getProductCount())
			{
				//product.setQuantity(0);
				BackOrderCount backOrderCount=cartService.getBackOrderCount(cartLine2.getId());
				remainmessage+=product.getName()+", ";
				//cartService.updateProduct(product);
				remaincheck=true;
				
 			}
			else
			{
				shipcheck=true;
				shippedmessage+=product.getName()+" in quantity "+cartLine2.getProductCount()+" and of price "+cartLine2.getTotal()+", ";
				product.setQuantity(product.getQuantity()-cartLine2.getProductCount());
				productDAO.update(product);
				//cart.setGrandTotal(cart.getGrandTotal()-cartLine2.getTotal());
				
				cartService.updateCart(cart);
				cartService.deleteCartLine(cartLine2.getId());
				
			}
			
			totalamount+=cartLine2.getTotal();
		}
		
		if(remaincheck)
		{
			remainmessage+= " ";
			URL url= new URL("http://localhost:8080/Shoppers-Stop/cart/backorder?user="+user.getId());
			sendEmail(user.getEmail(), remainmessage, url);
		}
		
		if(shipcheck)
		{
			shippedmessage+= " has been shipped to your address. ";
			URL url= new URL("http://localhost:8080/Shoppers-Stop/index?user="+user.getId());
			sendEmail(user.getEmail(), shippedmessage, url);
		}
		
		 mv.addObject("totalamount", totalamount );
		
		 return mv;
	 }
	
	public void sendEmail(String useremail,String sendmessage,URL url)
	{
		   String to = useremail;

	      // Sender's email ID needs to be mentioned
	      String from = "anshul8083@gmail.com";
	      final String username = "anshul8083@gmail.com";//change accordingly
	      final String password = "anshul@@";//change accordingly

	      // Assuming you are sending email through relay.jangosmtp.net
	      String host = "smtp.gmail.com";

	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", "25");

	      // Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
		   }
	         });

	      try {
		   // Create a default MimeMessage object.
		   Message message = new MimeMessage(session);
		
		   
		   // Set From: header field of the header.
		   message.setFrom(new InternetAddress(from));
		
		   // Set To: header field of the header.
		   message.setRecipients(Message.RecipientType.TO,
	               InternetAddress.parse(to));
		
		   // Set Subject: header field
		   message.setSubject("Invoice ");
		
		   // Now set the actual message
		   String urldata="<a href='"+url+"'>Click on the link the following link to proceed</a>";
		   message.setContent("<h1>"+sendmessage+"</h1>"+" "+urldata,"text/html"); 
		   

		   // Send message
		   Transport.send(message);
		   
		   System.out.println(message);

		   System.out.println("Sent message successfully....");

	      } catch (MessagingException e) {
	         System.out.println(e.getMessage());
	      }
	}
	
     
}
