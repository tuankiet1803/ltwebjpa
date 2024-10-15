package ltwebjpa.service.implement;

import java.util.List;

import ltwebjpa.dao.IVideoDAO;
import ltwebjpa.dao.implement.VideoDAOImp;
import ltwebjpa.entity.Video;
import ltwebjpa.service.IVideoService;

public class VideoServiceImp implements IVideoService{

	IVideoDAO videoDAO = new VideoDAOImp();
	@Override
	public void insert(Video video) {
		// TODO Auto-generated method stub
		videoDAO.insert(video);
	}

	@Override
	public void update(Video video) {
		// TODO Auto-generated method stub
		videoDAO.update(video);
	}

	@Override
	public void delete(int id) throws Exception {
		// TODO Auto-generated method stub
		videoDAO.delete(id);
	}

	@Override
	public List<Video> findAll() {
		// TODO Auto-generated method stub
		return videoDAO.findAll();
	}

	@Override
	public List<Video> findByName(String videoname) {
		// TODO Auto-generated method stub
		return videoDAO.findByName(videoname);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return videoDAO.count();
	}

	@Override
	public Video findByID(int id) {
		// TODO Auto-generated method stub
		return videoDAO.findByID(id);
	}

}
