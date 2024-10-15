package ltwebjpa.controller.admin;

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
import ltwebjpa.Constan;
import ltwebjpa.entity.Category;
import ltwebjpa.service.ICategoryService;
import ltwebjpa.service.implement.CategoryServiceImp;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert", 
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete"})
public class CategoryController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	public ICategoryService cateService = new CategoryServiceImp();
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		if (url.contains("categories")) {
			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);
			req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Category category = cateService.findID(id);
			req.setAttribute("cate", category);
			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				cateService.delete(id);
				resp.sendRedirect(req.getContextPath() + "/admin/categories");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		if (url.contains("insert")) {
			String name = req.getParameter("catename");
			int status = Integer.parseInt(req.getParameter("status"));
			String image = "";

			Category category = new Category();
			category.setCategoryname(name);
			category.setStatus(status);
			String uploadPath = Constan.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("image");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					image = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + image);
					category.setImages(image);
				} else {
					category.setImages("avatar.png");
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			cateService.insert(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		} else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("cateid"));
			String name = req.getParameter("catename");
			int status = Integer.parseInt(req.getParameter("status"));
			String image = "";

			Category category = new Category();
			category.setCategoryId(id);
			category.setCategoryname(name);
			category.setStatus(status);

			Category cateold = cateService.findID(id);
			String imageold = cateold.getImages();
			String uploadPath = Constan.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("image");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					image = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + image);
					category.setImages(image);
				} else {
					category.setImages(imageold);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}
}
