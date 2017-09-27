package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.GetOrganDao;
import com.zhidisoft.manage.dao.GetTaxerDao;

/**
 * 修改办税专员信息
 * Servlet implementation class ToEditTaxerServlet
 */
@WebServlet("/toEditTaxerServlet.do")
public class ToEditTaxerServlet extends HttpServlet {

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
		//获取需要修改的办税人员的id
		String id=req.getParameter("id");
		//通过id查询到该办税专员的信息,并传递给前端
		List<Map<String,String>> list=new GetTaxerDao().getTaxerInfo(id);
		req.setAttribute("taxer", list.get(0));
		//获取所有税务机关的信息，并传递给前端
		List<Map<String,String>> Organ=new GetOrganDao().getOrgan();
		req.setAttribute("organs", Organ);
		RequestDispatcher rd=req.getRequestDispatcher("/manage/jsp/editTaxer.jsp");
		rd.forward(req, resp);
	}
	
}
