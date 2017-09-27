package com.zhidisoft.manage.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UploadImgServlet
 */
@WebServlet("/uploadImg.do")
@MultipartConfig
public class UploadImgServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		req.setCharacterEncoding("utf-8");
		
		Part file=req.getPart("file");
		//在请求头中获取文件名
		String header=file.getHeader("content-disposition");
		String filename=header.substring(10+header.indexOf("filename="));
		filename=filename.substring(0, filename.length()-1);
		filename=UUID.randomUUID().toString()+filename;
		//另存为
		String path=getServletContext().getRealPath("/upload");
		File fileF=new File(path);
		if(!fileF.exists()){
			fileF.mkdirs();
		}
		String savePath=path+File.separator+filename;
		file.write(savePath);
		PrintWriter out=resp.getWriter();
		JSONObject json=new JSONObject();
		json.put("success", true);
		json.put("url", filename);
		out.println(json);
		out.flush();
		out.close();
	}
	

}
