  

package net.ag.shoppersstop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.ag.shoppersbackend.dao.ProductDAO;
import net.ag.shoppersbackend.dao.UserDAO;
import net.ag.shoppersbackend.dto.ExtraOrder;
import net.ag.shoppersbackend.dto.Product;
import net.ag.shoppersbackend.dto.User;
import net.ag.shoppersstop.model.BackOrderModel;

@Controller
@RequestMapping("/json/data")
public class jsonDataController {

	@Autowired
	private ProductDAO productDAO;
	

	@Autowired
	private UserDAO userDAO;
	
	@RequestMapping("listproducts")
	@ResponseBody //to get the data in the form of j son
	public List<Product> getAllProducts()
	{
		return  productDAO.listActiveProducts();
	}
	
	@RequestMapping("/admin/listproducts")
	@ResponseBody
	public List<Product> getAllProductsForAdmin()
	{
		return  productDAO.list();
	}
	
	@RequestMapping("/admin/backorder")
	@ResponseBody
	public List<BackOrderModel> getAllBackOrderForAdmin()
	{
		List<BackOrderModel> backOrderModels=new ArrayList<>();
		List<ExtraOrder> extraOrders=userDAO.getBackOrder();
		
		for (ExtraOrder extraOrder : extraOrders) {
			User user=extraOrder.getUser();
			Product product=extraOrder.getProduct();
			
			BackOrderModel backOrderModel=new BackOrderModel();
			backOrderModel.setProductId(product.getId());
			backOrderModel.setProductname(product.getName());
			backOrderModel.setQuantityavailable(product.getQuantity());
			backOrderModel.setQuantityneeded(extraOrder.getProductCount());
			backOrderModel.setUserid(extraOrder.getId());
			backOrderModel.setUsername(user.getEmail());
			backOrderModels.add(backOrderModel);
			
			
		}
		
		return  backOrderModels;
	}
	
	
	@RequestMapping("/showproducts/{id}/products")
	@ResponseBody
	public List<Product> getProductsByCategory(@PathVariable("id") int id)
	{
		return  productDAO.listActiveProductsByCategory(id);
	}
	
}
