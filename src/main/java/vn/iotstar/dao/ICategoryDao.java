package vn.iotstar.dao;

import java.util.List;

import vn.iotstar.entity.Category;

public interface ICategoryDao {

	List<Category> findByCategoryname(String catname);

	List<Category> findAll();

	Category findById(int cateid);

	void delete(int cateid) throws Exception;

	void update(Category category);

	void insert(Category category);

	int count();

}
