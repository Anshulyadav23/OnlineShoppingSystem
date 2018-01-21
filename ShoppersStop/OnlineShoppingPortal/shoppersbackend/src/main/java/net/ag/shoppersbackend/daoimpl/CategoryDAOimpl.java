package net.ag.shoppersbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.ag.shoppersbackend.dao.CategoryDAO;
import net.ag.shoppersbackend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOimpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> list() {
		String selectActiveCategory = "FROM Category WHERE active = :active";
		Query query = sessionFactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		return query.getResultList();
	}

	/*
	 * Getting single category
	 */
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override
	public boolean add(Category category) {

		try {
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	/*
	 * updating a single category
	 */

	@Override
	public boolean update(Category category) {
		try {
			// add category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(Category category) {

		category.setActive(false);
		try {
			// add category to the database table
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
