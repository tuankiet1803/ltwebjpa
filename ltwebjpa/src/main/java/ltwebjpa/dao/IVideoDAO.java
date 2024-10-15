package ltwebjpa.dao;

import java.util.List;

import ltwebjpa.entity.Category;
import ltwebjpa.entity.Video;

public interface IVideoDAO {

	void insert(Video video);
	
	void update(Video video);
	
	void delete(int id) throws Exception;
	
	Video findByID(int id);
	
	List<Video> findAll();
	
	List<Video> findByName(String videoname);
	
	List<Video> findByCategory(Category category);
	
	int count();
}
