package ltwebjpa.dao;

import java.util.List;

import ltwebjpa.entity.Category;

public interface ICategoryDAO {

	void insert(Category category);

	void update(Category category);

	void delete(int cateID) throws Exception;

	Category findID(int cateID);

	List<Category> findAll();

	List<Category> findCategoryName(String catename);

	List<Category> findAll(int page, int pagesize);

	int count();
}
