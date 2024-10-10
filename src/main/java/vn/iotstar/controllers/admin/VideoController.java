package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.entity.Category;
import vn.iotstar.entity.Video;
import vn.iotstar.services.ICategoryService;
import vn.iotstar.services.IVideoService;
import vn.iotstar.services.impl.CategoryService;
import vn.iotstar.services.impl.VideoService;
import vn.iotstar.utils.Constant;

@WebServlet(urlPatterns = { "/admin/videos", "/admin/video", "/admin/video/add", "/admin/video/insert",
		"/admin/video/delete", "/admin/video/update", "/admin/video/edit" })
public class VideoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public IVideoService videoService = new VideoService();
	ICategoryService cateService = new CategoryService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		if (url.contains("/admin/video/add")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);

		} else if (url.contains("/admin/videos")) {
			List<Video> list = videoService.findAll();
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);

		} else if (url.contains("/admin/video/delete")) {
			String id = req.getParameter("id").toString();
			try {
				videoService.delete(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");

		} else if (url.contains("/admin/video/edit")) {
			String id = req.getParameter("id");
			Video video = videoService.findById(id);
			req.setAttribute("video", video);
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		String url = req.getRequestURI();

		// insert sẽ chịu trách nhiệm up ảnh sản phẩm
		if (url.contains("/admin/video/insert")) {
			// lay du lieu tu view
			String title = req.getParameter("title");
	    	String description = req.getParameter("description");
	    	int categoryId = Integer.parseInt(req.getParameter("categoryId"));
	    	int active = Integer.parseInt(req.getParameter("active"));
	    	String poster = req.getParameter("poster");
	    	int views = Integer.parseInt(req.getParameter("views"));

			// dua vao model
			Video video = new Video();
			String videoId = UUID.randomUUID().toString(); // Random Video Id khi insert
			video.setVideoId(videoId);
			video.setActive(active);
			video.setTitle(title);
			cateService = new CategoryService();
			video.setCategory(cateService.findById(categoryId));
			video.setDescription(description);
			video.setViews(views);

			// xu ly upload file
			String fname = "";
			String uploadPath = Constant.DIR;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}

			try {
				Part part = req.getPart("images1");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					// doi ten file neu co file trung
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					fname = System.currentTimeMillis() + "." + ext;

					// upload file
					part.write(uploadPath + "/" + fname); // co the them nhieu thu muc con khac nhau

					// ghi ten file vao data
					video.setPoster(fname);

				} else if (poster == null || poster.trim().isEmpty()) {
					video.setPoster("null.jpg");
				} else {
					video.setPoster(poster);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// truyen model vao service
			videoService.insert(video);

			// redirect
			resp.sendRedirect(req.getContextPath() + "/admin/videos");

			// Xu li UPDATE
		} else if (url.contains("/admin/video/update")) {
			
			// lay du lieu tu view
			String title = req.getParameter("title");
	    	String description = req.getParameter("description");
	    	int categoryId = Integer.parseInt(req.getParameter("categoryId"));
	    	int active = Integer.parseInt(req.getParameter("active"));
	    	String poster = req.getParameter("poster");
	    	int views = Integer.parseInt(req.getParameter("views"));
	    	String videoID = req.getParameter("id");
			
			//dua vao model
			Video video = videoService.findById(videoID);
			
			// Luu hinh cu
			Video videoOld = videoService.findById(videoID);
			String fileOld = videoOld.getPoster();

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
					video.setPoster(fname);

				} else if (poster == null || poster.trim().isEmpty()) {
					video.setPoster(fileOld);
				} else {
					video.setPoster(poster);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			// truyen model vao service _ UPDATE
			videoService.update(video);

			// redirect
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
