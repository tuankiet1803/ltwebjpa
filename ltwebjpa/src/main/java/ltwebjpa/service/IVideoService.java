package ltwebjpa.service;

import java.util.List;

import ltwebjpa.entity.Video;

public interface IVideoService {
	void insert(Video video);

	void update(Video video);

	void delete(int id) throws Exception;
	
	Video findByID(int id);

	List<Video> findAll();

	List<Video> findByName(String videoname);

	int count();
}