package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.DeleteTaxerDao;
import com.zhidisoft.system.entity.User;

import net.sf.json.JSONObject;

/**
 * 删除办税专员
 * Servlet implementation class DeleteTaxerServlet
 */
@WebServlet("/deleteTaxerServlet.do")
public class DeleteTaxerServlet extends HttpServlet {

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
		resp.setCharacterEncoding("utf-8");
		//创建json对象
		JSONObject json=new JSONObject();
		//创建输出流对象
		PrintWriter out=resp.getWriter();
		//获取session中的user对象
		User user=(User)req.getSession().getAttribute("user");
		if(user==null){
			json.put("success", false);
			json.put("msg", "未登录或已下线，请重新登录");
			out.println(json.toString());
			out.flush();
			out.close();
			return;
		}else{
			//获取要被删除的办税员id
			String id=req.getParameter("id");
			Integer rows=new DeleteTaxerDao().DeleteTaxer(id);
			//判断是否删除成功
			if(rows>0){
				json.put("success", true);
				json.put("msg", "删除成功");
			}else{
				json.put("success", false);
				json.put("msg", "删除失败");
			}
			out.println(json.toString());
			out.flush();
			out.close();
		}
		
	}
	

}
