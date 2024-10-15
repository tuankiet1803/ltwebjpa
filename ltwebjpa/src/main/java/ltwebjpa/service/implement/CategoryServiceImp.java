package ltwebjpa.service.implement;

import java.util.List;

import ltwebjpa.dao.ICategoryDAO;
import ltwebjpa.dao.implement.CategoryDAOImp;
import ltwebjpa.entity.Category;
import ltwebjpa.service.ICategoryService;

public class CategoryServiceImp implements ICategoryService{
	ICategoryDAO cateDAO = new CategoryDAOImp();
	@Override
	public void insert(Category category) {
		cateDAO.insert(category);
		
	}

	@Override
	public void update(Category category) {
		cateDAO.update(category);
		
	}

	@Override
	public void delete(int cateID) throws Exception {
		cateDAO.delete(cateID);
		
	}

	@Override
	public Category findID(int cateID) {
		// TODO Auto-generated method stub
		return cateDAO.findID(cateID);
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return cateDAO.findAll();
	}

	@Override
	public List<Category> findCategoryName(String catename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Category> findAll(int page, int pagesize) {
		// TODO Auto-generated method stub
		return cateDAO.findAll(page, pagesize);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return cateDAO.count();
	}

}
