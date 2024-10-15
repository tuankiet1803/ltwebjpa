package ltwebjpa.dao.implement;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import ltwebjpa.config.JPAConfig;
import ltwebjpa.dao.IUserDAO;
import ltwebjpa.entity.User;

public class UserDAIOImp implements IUserDAO{

	@Override
	public void insert(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(user);
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
	public void update(User user) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(user);
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
	public List<User> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public User findByPhone(String phone) {
		EntityManager enma = JPAConfig.getEntityManager();
		String sql = "SELECT u FROM User u WHERE u.phone = :phone";
		TypedQuery<User> query = enma.createQuery(sql, User.class);
		query.setParameter("phone", phone);
		User user = query.getSingleResult();
		return user;
	}

	@Override
	public User findByUsername(String username) {
		EntityManager enma = JPAConfig.getEntityManager();
		User user = enma.find(User.class, username);
		return user;
	}

	@Override
	public boolean checkExistEmail(String email) {
		EntityManager enma = JPAConfig.getEntityManager();
		String sql = "SELECT u FROM User u WHERE u.email = :email";
		TypedQuery<User> query = enma.createQuery(sql, User.class);
		query.setParameter("email", email);
		User user = query.getSingleResult();
		if(user != null)
			return true;
		return false;
	}

	@Override
	public boolean checkExistPhone(String phone) {
		EntityManager enma = JPAConfig.getEntityManager();
		String sql = "SELECT u FROM User u WHERE u.phone = :phone";
		TypedQuery<User> query = enma.createQuery(sql, User.class);
		query.setParameter("phone", phone);
		User user = query.getSingleResult();
		if(user != null)
			return true;
		return false;
	}
}
