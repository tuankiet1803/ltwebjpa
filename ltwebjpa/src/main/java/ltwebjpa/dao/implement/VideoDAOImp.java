package ltwebjpa.dao.implement;

import java.util.List;

import jakarta.persistence.*;
import ltwebjpa.config.JPAConfig;
import ltwebjpa.dao.IVideoDAO;
import ltwebjpa.entity.Category;
import ltwebjpa.entity.Video;

public class VideoDAOImp implements IVideoDAO{

	@Override
	public void insert(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.persist(video);
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
	public void update(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			enma.merge(video);
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
	public void delete(int id) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			Video category = enma.find(Video.class, id);
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
	public List<Video> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);
		return query.getResultList();
	}

	@Override
	public List<Video> findByName(String videoname) {
		EntityManager enma = JPAConfig.getEntityManager();
		String sql = "SELECT v FROM Video v WHERE c.title like :videoname";
		TypedQuery<Video> query = enma.createQuery(sql, Video.class);
		query.setParameter("videoname", "%" + videoname + "%");
		return query.getResultList();
	}

	@Override
	public int count() {
		EntityManager enma = JPAConfig.getEntityManager();
		String sql = "SELECT count(v) FROM Category v";
		jakarta.persistence.Query query = enma.createQuery(sql);
		return ((Long)query.getSingleResult()).intValue(); 
	}

	@Override
	public Video findByID(int id) {
		EntityManager enma = JPAConfig.getEntityManager();
		Video video = enma.find(Video.class, id);
		return video;
	}

}
