package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Video;

public interface IVideoDao {

	Video findByTitle(String title);

	List<Video> findAll();

	Video findById(String videoId);

	void update(Video video);

	void delete(String id) throws Exception;

	void insert(Video video);

}
