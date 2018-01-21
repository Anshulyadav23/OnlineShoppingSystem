package net.ag.shoppersstop.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import net.ag.shoppersbackend.dao.CategoryDAO;
import net.ag.shoppersbackend.dao.ProductDAO;
import net.ag.shoppersbackend.dto.Category;
import net.ag.shoppersbackend.dto.Product;
import net.ag.shoppersstop.exception.ProductNotFoundException;

@Controller
public class PageController {

	//slf4j functionality for the proper description in console
	private static final Logger logger = LoggerFactory.getLogger(PageController.class);

	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ProductDAO productDAO;
	
	
	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		logger.info("Inside PageController index method - INFO");
		logger.debug("Inside PageController index method - DEBUG");

		HashMap<String, Product> products = new HashMap<>();
		List<Category> category = categoryDAO.list();
		for (Category category2 : category) {
			List<Product> productdata = productDAO.listActiveProductsByCategory(category2.getId());
			if (!productdata.isEmpty()) {
				Collections.shuffle(productdata);
				products.put(category2.getName(), productdata.iterator().next());
			}

		}
		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("products", products);
		mv.addObject("userClickHome", true);
		return mv;
	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		
	    return mv;
	}

	/* Access Denied */
	@RequestMapping(value = "/access-denied")
	public ModelAndView accessDenied() {
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("title", "403- Access-Denied");
		mv.addObject("errorTitle", "Aha! Caught you");
		mv.addObject("errorDescription", "You are not authorized to view this page");
		return mv;
	}

	@RequestMapping(value = "/listproducts")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		// passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickAllProducts", true);
		return mv;
	}

	@RequestMapping(value = "/showproducts/{id}/products")
	public ModelAndView showCategoryProducts(@PathVariable("id") int id) {
        Category category = null;
		category = categoryDAO.get(id);
		List ls = new ArrayList();
		ls.add(category);
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", category.getName());
		mv.addObject("category", category);
		// passing the list of categories
		mv.addObject("categories", ls);
		mv.addObject("name", category.getName());
		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}



	@RequestMapping(value = "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable("id") int id) throws ProductNotFoundException {

		ModelAndView mv = new ModelAndView("page");
		Product product = productDAO.get(id);
		if (product == null)
			throw new ProductNotFoundException();

		product.setViews(product.getViews() + 1);

		productDAO.update(product);

		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);

		return mv;
	}
	
	

	/* login mapping */

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		ModelAndView mv = new ModelAndView("login");

		if (error != null) {
			mv.addObject("message", "Invalid username and password");
		}

		if (logout != null) {
			mv.addObject("logout", "User has successfully logged out..!!");
		}

		mv.addObject("title", "Login");
		return mv;
	}

	/* logout */

	@RequestMapping(value = "/perform-logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {

		// we are going to fetch the authentication

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {

			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		request.getSession().invalidate();

		return "redirect:/login?logout";
	}

}
