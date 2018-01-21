package net.ag.shoppersbackend.test;

import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import net.ag.shoppersbackend.dao.ProductDAO;
import net.ag.shoppersbackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context;
	private static ProductDAO productDAO;
	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.ag.shoppersbackend");
		context.refresh();
		productDAO = (ProductDAO) context.getBean("productDAO");
	}

	/*
	 * @Test public void testUpdateProduct() { //fetching and updating a product
	 * product = productDAO.get(6); product.setActive(true);
	 * assertEquals("Something went wrong while updating new product",true,
	 * productDAO.update(product)); }
	 */
	/*
	 * @Test public void testAddProduct() { product = new Product();
	 * product.setName("Beats HeadSets"); product.setBrand("Apple");
	 * product.setDescription("Best sound quality"); product.setUnitPrice(21000);
	 * product.setQuantity(5); product.setCategoryId(3); product.setSupplierId(2);
	 * product.setPurchases(0); product.setViews(0);
	 * assertEquals("Something went wrong while inserting a new product",true,
	 * productDAO.add(product)); }
	 */

	/*
	 * @Test public void testCRUDProduct() { //add operation
	 * 
	 * product = new Product(); product.setName("Apple Watch");
	 * product.setBrand("Apple"); product.setDescription("Best Apple product");
	 * product.setUnitPrice(35000); product.setQuantity(8);
	 * product.setCategoryId(3); product.setSupplierId(2); product.setPurchases(0);
	 * product.setViews(0);
	 * 
	 * assertEquals("Something went wrong while inserting a new product",true,
	 * productDAO.add(product));
	 * 
	 * //fetching and updating a product
	 * 
	 * product = productDAO.get(2); product.setName("Mobile");
	 * assertEquals("Something went wrong while updating new product",true,
	 * productDAO.update(product));
	 * 
	 * //deleting the product
	 * 
	 * assertEquals("Something went wrong while deleting the product",true,
	 * productDAO.delete(product));
	 * 
	 * //list
	 * assertEquals("Something went wrong while fetching the list of products!",6,
	 * productDAO.list().size());
	 * 
	 * 
	 * }
	 */

	@Test
	public void testListActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!", 5,
				productDAO.listActiveProducts().size());
	}

	@Test
	public void testListActiveProductsByCategory() {
		assertEquals("Something went wrong while fetching the list of products!", 3,
				productDAO.listActiveProductsByCategory(3).size());

		assertEquals("Something went wrong while fetching the list of products!", 2,
				productDAO.listActiveProductsByCategory(1).size());

	}

	@Test
	public void testGetLatestActiveProducts() {
		assertEquals("Something went wrong while fetching the list of products!", 3,
				productDAO.getLatestActiveProducts(3).size());

	}

}
