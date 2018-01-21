package net.ag.shoppersbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import net.ag.shoppersbackend.dao.CategoryDAO;
import net.ag.shoppersbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context;
	private static CategoryDAO categoryDAO;
	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("net.ag.shoppersbackend");
		context.refresh();
		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");
		System.out.println(context.containsBean("categoryDAO"));
	}

	@Test
	public void testAddCategory() {
		category = new Category();
		category.setName("One Plus");
		category.setDescription("description of Tab");
		category.setImageURL("Tab.png");
		assertEquals("Successfully added a category inside a table", true, categoryDAO.add(category));
	}

	/*
	 * @Test public void testGetCategory() { category = categoryDAO.get(1);
	 * assertEquals("Successfully fetched a single category from the table",
	 * "Television",category.getName()); }
	 */

	/*
	 * @Test public void testUpdateCategory() { category = categoryDAO.get(1);
	 * category.setName("Mobile");
	 * assertEquals("Successfully updated a single category from the table",true,
	 * categoryDAO.update(category)); }
	 */

	/*
	 * @Test public void testDeleteCategory() { category = categoryDAO.get(1);
	 * assertEquals("Successfully delete a single category from the table",false,
	 * categoryDAO.delete(category)); }
	 */

	/*
	 * @Test public void testListCategory() {
	 * assertEquals("Successfully fetched the list of category from the table",2,
	 * categoryDAO.list().size()); }
	 */

	/*
	 * @Test public void testCRUDCategory() { //add operation category = new
	 * Category(); category.setName("AppleWatch");
	 * category.setDescription("description of Watch");
	 * category.setImageURL("Watch.png");
	 * assertEquals("Something went wrong while inserting a new product",true,
	 * categoryDAO.add(category));
	 * 
	 * //fetching and updating a category category = categoryDAO.get(2);
	 * category.setName("Mobile");
	 * assertEquals("Something went wrong while updating new product",true,
	 * categoryDAO.update(category));
	 * 
	 * //delete the category category = categoryDAO.get(2);
	 * assertEquals("Something went wrong while deleting a product",true,
	 * categoryDAO.delete(category));
	 * 
	 * //returning a list of category
	 * assertEquals("Something went wrong while fetching a product",1,
	 * categoryDAO.list().size());
	 * 
	 * }
	  */

}
