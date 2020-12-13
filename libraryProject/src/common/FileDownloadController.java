package common;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download/*")
public class FileDownloadController extends HttpServlet {
	private static String B_IMAGE_FOLDER = "C:\\library\\books\\books_image";
	private static String N_IMAGE_FOLDER = "C:\\library\\notice\\notice_image";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getPathInfo();
		
		if(action.equals("/b_image.do")) {
			//이미지 파일 이름과 글 번호를 가져온다.
			String b_imageName = request.getParameter("b_imageName");
			String b_num = request.getParameter("b_num");
			
			OutputStream out = response.getOutputStream();
			//글 번호에 대한 파일 경로를 설정한다.
			String path = B_IMAGE_FOLDER + "\\" + b_imageName;
			File imageFile = new File(path);
			
			if(imageFile.isFile()) {
				response.setHeader("Cache-Control", "no-cache");
				//이미지 파일을 내려 받는 데 필요한 response에 헤더 정보를 설정한다.
				response.addHeader("Content-disposition", "attachment;fileName=" + b_imageName);
				FileInputStream in = new FileInputStream(imageFile);
				
				//버퍼를 이용해 한 번에 8Kb씩 전송한다.
				byte[] buffer = new byte[1024 * 8];
				while(true) {
					int count = in.read(buffer);
					if(count == -1)
						break;
					out.write(buffer, 0, count);
				}
				in.close();
				out.flush();
				out.close();
			}
		}else if(action.equals("/n_image.do")) {
			//이미지 파일 이름과 글 번호를 가져온다.
			String n_imageName = request.getParameter("imageFileName");
			String n_num = request.getParameter("n_num");
			
			OutputStream out = response.getOutputStream();
			//글 번호에 대한 파일 경로를 설정한다.
			String path = N_IMAGE_FOLDER + "\\" + n_imageName;
			File imageFile = new File(path);
			
			if(imageFile.isFile()) {
				response.setHeader("Cache-Control", "no-cache");
				//이미지 파일을 내려 받는 데 필요한 response에 헤더 정보를 설정한다.
				response.addHeader("Content-disposition", "attachment;fileName=" + n_imageName);
				FileInputStream in = new FileInputStream(imageFile);
				
				//버퍼를 이용해 한 번에 8Kb씩 전송한다.
				byte[] buffer = new byte[1024 * 8];
				while(true) {
					int count = in.read(buffer);
					if(count == -1)
						break;
					out.write(buffer, 0, count);
				}
				in.close();
				out.flush();
				out.close();
			}
		}
	}
}
