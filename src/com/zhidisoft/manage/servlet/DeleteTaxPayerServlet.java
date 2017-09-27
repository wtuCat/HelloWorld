package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.util.DBUtil;

import net.sf.json.JSONObject;

/**
 * 删除纳税人
 * Servlet implementation class DeleteTaxPayerServlet
 */
@WebServlet("/deleteTaxPayerServlet.do")
public class DeleteTaxPayerServlet extends HttpServlet {

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
		//获取要被删除的纳税人的id
		String id=req.getParameter("id");
		Integer rows=DBUtil.update("update tb_tax_payer set removeState=1 where id="+id);
		//创建json对象向前端传递数据
		JSONObject json=new JSONObject();
		//判断是否删除成功
		if(rows>0){
			json.put("success", true);
			json.put("msg", "删除成功");
		}else{
			json.put("success", false);
			json.put("msg", "删除失败");
		}	
		PrintWriter out=resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
			
	}
	

}
