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
import ltwebjpa.entity.Video;
import ltwebjpa.service.ICategoryService;
import ltwebjpa.service.IVideoService;
import ltwebjpa.service.implement.CategoryServiceImp;
import ltwebjpa.service.implement.VideoServiceImp;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 20, maxFileSize = 1024 * 1024 * 50, maxRequestSize = 1024 * 1024
		* 100)
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert", "/admin/video/edit",
		"/admin/video/update", "/admin/video/delete", "/admin/video/search", "/admin/video/find" })
public class VideoController extends HttpServlet {

	ICategoryService cateService = new CategoryServiceImp();
	IVideoService videoService = new VideoServiceImp();
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		List<Category> listcate = cateService.findAll();
		req.setAttribute("listcate", listcate);
		if (url.contains("videos")) {
			List<Video> list = videoService.findAll();
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-list.jsp").forward(req, resp);
		} else if (url.contains("add")) {
			req.getRequestDispatcher("/views/admin/video-add.jsp").forward(req, resp);
		} else if (url.contains("edit")) {
			int id = Integer.parseInt(req.getParameter("id"));
			Video video = videoService.findByID(id);
			req.setAttribute("video", video);
			req.getRequestDispatcher("/views/admin/video-edit.jsp").forward(req, resp);
		} else if (url.contains("delete")) {
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				videoService.delete(id);
				resp.sendRedirect(req.getContextPath() + "/admin/videos");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (url.contains("search")) {
			String findname = req.getParameter("titlesearch");
			List<Video> list = videoService.findByName(findname);
			req.setAttribute("listvideo", list);
			req.getRequestDispatcher("/views/admin/video-search.jsp").forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		if (url.contains("insert")) {
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			int active = Integer.parseInt(req.getParameter("active"));
			int categoryID = Integer.parseInt(req.getParameter("category"));
			String poster = "";
			String videoname = "";

			Video video = new Video();
			video.setTitle(title);
			video.setDescription(description);
			video.setActive(active);
			video.setCategory(cateService.findID(categoryID));
			String uploadPath = Constan.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					poster = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + poster);
					video.setPoster(poster);
				} else {
					video.setPoster(poster);
				}
				part = req.getPart("video");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					videoname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + videoname);
					video.setVideoname(videoname);
				} else {
					video.setVideoname(videoname);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			videoService.insert(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} else if (url.contains("update")) {
			int id = Integer.parseInt(req.getParameter("videoid"));
			String title = req.getParameter("title");
			String description = req.getParameter("description");
			int active = Integer.parseInt(req.getParameter("active"));
			String poster = "";
			String videoname = "";

			Video video = new Video();
			video.setVideoId(id);
			video.setTitle(title);
			video.setDescription(description);
			video.setActive(active);

			Video videoold = new Video();
			String posterold = videoold.getPoster();
			String videonameold = videoold.getVideoname();
			String uploadPath = Constan.UPLOAD_DIRECTORY;
			File uploadDir = new File(uploadPath);
			if (!uploadDir.exists()) {
				uploadDir.mkdir();
			}
			try {
				Part part = req.getPart("poster");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					poster = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + poster);
					video.setPoster(poster);
				} else {
					video.setPoster(posterold);
				}
				part = req.getPart("video");
				if (part.getSize() > 0) {
					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
					int index = filename.lastIndexOf(".");
					String ext = filename.substring(index + 1);
					videoname = System.currentTimeMillis() + "." + ext;
					part.write(uploadPath + "/" + videoname);
					video.setVideoname(videoname);
				} else {
					video.setVideoname(videonameold);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			videoService.update(video);
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} else if (url.contains("find")) {
			String findname = req.getParameter("titlesearch");
			resp.sendRedirect(req.getContextPath() + "/admin/video/search?titlesearch=" + findname);
		}
	}
}
