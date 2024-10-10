package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.impl.CategoryService;
import vn.iotstar.utils.Constant;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, // 1MB
		maxFileSize = 1024 * 1024 * 5, // 5MB
		maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories","/admin/category", "/admin/category/add", "/admin/category/insert",
		"/admin/category/delete", "/admin/category/update", "/admin/category/edit" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("/admin/category/add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);

		} else if (url.contains("/admin/categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");

		} else if (url.contains("/admin/category/edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findById(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		// insert sẽ chịu trách nhiệm up ảnh sản phẩm
		if (url.contains("/insert")) {
			// lay du lieu tu view
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			String images = req.getParameter("images");

			// dua vao model
			Category category = new Category();
			category.setCategoryname(categoryname);
			category.setStatus(status);

			// xu ly upload file
			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			try {
				Part part = req.getPart("images_up");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file neu co file trung
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					// upload file
					part.write(uploadPath + "/" + fname); // co the them nhieu thu muc con khac nhau

					// ghi ten file vao data
					category.setImages(fname);

				} else if (images == null || images.trim().isEmpty()) {
					category.setImages("null.jpg");
				} else {
					category.setImages(images);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// truyen model vao service
			cateService.insert(category);

			// redirect
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
			
			//Xu li UPDATE
		} else if (url.contains("/admin/category/update")) {
			// lay du lieu tu view
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			String images = req.getParameter("images");
			int id = Integer.parseInt(req.getParameter("categoryid"));
			
			// dua vao model
			Category category = new Category();
			category.setCategoryname(categoryname);
			category.setStatus(status);
			category.setCategoryid(id);
			
			//Luu hinh cu
			Category cateOld = cateService.findById(id);
			String fileOld = cateOld.getImages();
			
			// xu ly update image file
			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			try {
				Part part = req.getPart("images_up");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file neu co file trung
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					// upload file
					part.write(uploadPath + "/" + fname); // co the them nhieu thu muc con khac nhau

					// ghi ten file vao data
					category.setImages(fname);

				} else if (images == null || images.trim().isEmpty()) {
					category.setImages(fileOld);
				} else {
					category.setImages(images);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// truyen model vao service _ UPDATE
			cateService.update(category);

			// redirect
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
