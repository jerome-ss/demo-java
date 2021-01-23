package com.jerome.uploadFile;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.util.UUID;

/**
 * 
 * @ClassName: UploadServlet
 * @Description: 文件上传
 * @author jerome
 * @date 2015年9月30日 上午9:53:21
 *
 */
@WebServlet(name = "uploadServlet", urlPatterns = { "/uploadServlet" })
@MultipartConfig // 标识Servlet支持文件上传
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// 获取文件部件part
		Part part = request.getPart("upfile");
		// 获取文件服务器头部信息
		String root = request.getServletContext().getRealPath("/");
		String name = part.getHeader("content-disposition");
		String ext = name.substring(name.lastIndexOf("."), name.length() - 1);
		String filename = root + "/" + UUID.randomUUID().toString() + ext; // 保存
		System.out.println(filename);
		part.write(filename);
	
	}
	/* jsp
	<form action="testServlet" method="post" enctype="multipart/form-data">
		选择文件:<input type="file" name="upfile"><br> 
		<input type="submit" value="提交">
	</form>
	*/

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
