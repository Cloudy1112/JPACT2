package vn.iotstar.services.impl;

import java.util.List;

import vn.iotstar.dao.IVideoDao;
import vn.iotstar.dao.impl.VideoDao;
import vn.iotstar.entity.Video;
import vn.iotstar.services.IVideoService;

public class VideoService implements IVideoService {
	IVideoDao videodao = new VideoDao();

	@Override
	public Video findByTitle(String title) {
		return videodao.findByTitle(title);
	}

	@Override
	public List<Video> findAll() {
		return videodao.findAll();
	}

	@Override
	public Video findById(String videoId) {
		return videodao.findById(videoId);
	}

	@Override
	public void update(Video video) {
		videodao.update(video);
	}

	@Override
	public void delete(String videoId) throws Exception {
		videodao.delete(videoId);		
	}

	@Override
	public void insert(Video video) {
		videodao.insert(video);
		
	}
	
}
