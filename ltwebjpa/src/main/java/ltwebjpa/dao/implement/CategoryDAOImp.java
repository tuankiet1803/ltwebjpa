package ltwebjpa.dao.implement;

import java.util.List;

import jakarta.persistence.*;
import ltwebjpa.config.JPAConfig;
import ltwebjpa.dao.ICategoryDAO;
import ltwebjpa.entity.Category;

public class CategoryDAOImp implements ICategoryDAO {

	@Override
	public void insert(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Category category) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(category);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(int cateID) throws Exception{
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Category category = enma.find(Category.class, cateID);
			if(category != null) {
				enma.remove(category);

			}
			else {
				throw new Exception("Khong tim thay");
			}
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
		
	}

	@Override
	public Category findID(int cateID) {
		EntityManager enma = JPAConfig.getEntityManager();
		Category category = enma.find(Category.class, cateID);
		return category;
	}

	@Override
	public List<Category> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		return query.getResultList();
	}

	@Override
	public List<Category> findCategoryName(String catename) {
		EntityManager enma = JPAConfig.getEntityManager();
		String sql = "SELECT c FROM Category c WHERE c.categoryname like :catename";
		TypedQuery<Category> query = enma.createQuery(sql, Category.class);
		query.setParameter("catename", "%" + catename + "%");
		return query.getResultList();
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Category> query = enma.createNamedQuery("Category.findAll", Category.class);
		query.setFirstResult(page*pagesize);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String sql = "SELECT count(c) FROM Category c";
		jakarta.persistence.Query query = enma.createQuery(sql);
		return ((Long)query.getSingleResult()).intValue(); 
	}
}
