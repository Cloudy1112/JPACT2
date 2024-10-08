package vn.iotstar.services;

import java.util.List;

import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.ICategoryDao;
import vn.iotstar.entity.Category;

public class CategoryService implements ICategoryService {
	ICategoryDao catedao = new CategoryDao();
	
	@Override
	public List<Category> findByCategoryname(String catname) {
		// TODO Auto-generated method stub
		return catedao.findByCategoryname(catname);
	}

	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return catedao.findAll();
	}

	@Override
	public Category findById(int cateid) {
		// TODO Auto-generated method stub
		return catedao.findById(cateid);
	}

	@Override
	public void delete(int cateid) throws Exception {
		catedao.delete(cateid);
		
	}

	@Override
	public void update(Category category) {
		catedao.update(category);	
	}

	@Override
	public void insert(Category category) {
		catedao.insert(category);
	}

}
