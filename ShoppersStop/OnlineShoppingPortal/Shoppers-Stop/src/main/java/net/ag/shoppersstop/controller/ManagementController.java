package net.ag.shoppersstop.controller;

import java.util.ArrayList;
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
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import net.ag.shoppersbackend.dao.CartLineDAO;
import net.ag.shoppersbackend.dao.CategoryDAO;
import net.ag.shoppersbackend.dao.ProductDAO;
import net.ag.shoppersbackend.dao.UserDAO;
import net.ag.shoppersbackend.dto.Cart;
import net.ag.shoppersbackend.dto.CartLine;
import net.ag.shoppersbackend.dto.Category;
import net.ag.shoppersbackend.dto.ExtraOrder;
import net.ag.shoppersbackend.dto.Product;
import net.ag.shoppersbackend.dto.User;
import net.ag.shoppersstop.model.BackOrderModel;
import net.ag.shoppersstop.service.CartService;
import net.ag.shoppersstop.util.FileUploadUtility;
import net.ag.shoppersstop.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class ManagementController {

	@Autowired
	private CartService cartService;

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private CartLineDAO cartLineDAO;

	@Autowired
	private UserDAO userDAO;

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		Product nProduct = new Product(); //for generating UUI code for storing images

		nProduct.setSupplierId(2);
		nProduct.setActive(true);
		mv.addObject("product", nProduct);
		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product is submitted successfully");
			} else if (operation.equals("category")) {
				mv.addObject("message", "Category is submitted successfully");

			}
		}

		System.out.println(nProduct.toString());

		return mv;
	}

	@RequestMapping(value = "/backOrder")
	public ModelAndView showBackOrder() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickBackOrder", true);
		mv.addObject("title", "Back Orders");
		return mv;
	}

	//e-mail will be send to the user
	 
	@RequestMapping(value = "/{id}/backOrderEmail")
	public ModelAndView backOrderEmail(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickBackOrder", true);
		mv.addObject("title", "Manage Products");

		ExtraOrder extraOrder = userDAO.getBackOrder(id);

		if (extraOrder != null) {
			User user = extraOrder.getUser();
			Product product = extraOrder.getProduct();
			if (product.getQuantity() >= extraOrder.getProductCount()) {
				String message = "Hello Customer . The admin has send this email to let you know that following product "
						+ product.getName() + " available If you would like to purchase it browse our site and grab it";
				sendEmail(user.getEmail(), message);

			}

		}
		return mv;
	}

	@RequestMapping(value = "/{id}/product", method = RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable int id) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		// fetch the product from the database
		Product nProduct = productDAO.get(id);
		// set the product fetched from database
		mv.addObject("product", nProduct);
		return mv;
	}

	// handling product submission
	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results,
			Model model, HttpServletRequest request) {
		// handle image validation for new products
		if (mProduct.getId() == 0) {
			new ProductValidator().validate(mProduct, results);
		} else {
			if (!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}

		new ProductValidator().validate(mProduct, results);

		// check if there are any errors
		if (results.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for product submission!");
			return "page";
		}
		logger.info(mProduct.toString());
		// create a new product record
		if (mProduct.getId() == 0) {
			// create a product if id is zero
			productDAO.add(mProduct);
		} else {
			// update the product if id is not zero
			productDAO.update(mProduct);
			List<Cart> carts = new ArrayList<>();
			List<CartLine> cartLines = cartLineDAO.getCartLineByProduct(mProduct.getId());
			for (CartLine cartLine : cartLines) {
				Cart cart = cartLineDAO.getCartById(cartLine.getCartId());
				if (cart != null)
					carts.add(cart);
			}

			for (Cart cart : carts) {
				String message = "Dear Customer, The following item in your cart " + mProduct.getName() + " with price "
						+ mProduct.getUnitPrice() + " is available.";
				sendEmail(cart.getUser().getEmail(), message);
			}

			List<ExtraOrder> extraOrders = cartLineDAO.getBackOrder(mProduct.getId());

			for (ExtraOrder extraOrder : extraOrders) {
				User user = extraOrder.getUser();
				if (mProduct.getQuantity() > extraOrder.getProductCount()) {
					Cart cart = user.getCart();
					cartService.addCartLine(mProduct.getId(), cart,extraOrder.getProductCount());
					//cart.setGrandTotal(cart.getGrandTotal() + mProduct.getUnitPrice());
					mProduct.setQuantity(mProduct.getQuantity()-extraOrder.getProductCount());
					productDAO.update(mProduct);
					//cartService.updateCart(cart);
					cartService.deleteExtraOrder(extraOrder);
					String message = "Dear Customer, The product is in the stock " + mProduct.getName()
					+ " with price $" + mProduct.getUnitPrice()
					+ " And has been added to your cart .";
			        sendEmail(user.getEmail(), message);
				}
			}
		}

		if (!mProduct.getFile().getOriginalFilename().equals("")) {

			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}

		return "redirect:/manage/products?operation=product";
	}

	@RequestMapping(value = "/product/{id}/activation", method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		// going to fetch the data from the product

		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		// activating and deactivating based on the value of active field
		product.setActive(!product.isActive());
		// updating the product
		productDAO.update(product);
		// System.out.println(isActive);
		if (isActive) {
			List<Cart> carts = new ArrayList<>();
			List<CartLine> cartLines = cartLineDAO.getCartLineByProduct(id);
			for (CartLine cartLine : cartLines) {
				Cart cart = cartLineDAO.getCartById(cartLine.getCartId());
				if (cart != null)
				{
					carts.add(cart);
					cartService.deleteCartLine(cartLine.getId(),cart);
				}
				
				
			}

			for (Cart cart : carts) {
				String message = "Dear Customer, The product is out of stock right now." + product.getName()
						+ " with price $" + product.getUnitPrice()
						+ " We will notify you soon once it will be in stock. Thank You";
				sendEmail(cart.getUser().getEmail(), message);
			}

		}

		return (isActive) ? "you have successfully deactivated the product with id" + product.getId()
				: "you have successfully activated the product with id" + product.getId();
	}

	// to handle category submission
	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		// add the category
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";

	}

	// returning categories for all the request
	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return categoryDAO.list();
	}

	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}

	public void sendEmail(String useremail, String sendmessage) {
		String to = useremail;

		// Sender's email ID needs to be mentioned
		String from = "anshul8083@gmail.com";
		final String username = "anshul8083@gmail.com";// change accordingly
		final String password = "anshul@@";// change accordingly

		// Assuming you are sending email through relay.jangosmtp.net
		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "25");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
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
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Invoice");

			// Now set the actual message
			message.setText(sendmessage);

			// Send message
			Transport.send(message);

			System.out.println(message);

			System.out.println("Sent message successfully....");

		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}
	}

}
