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


/**
 * 
 * 此Servlet请求转发向addTaxer.jsp，并传递下拉框数据，
 * Servlet implementation class ToAddTaxerServlet
 */
@WebServlet("/toAddTaxerServlet.do")
public class ToAddTaxerServlet extends HttpServlet {

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
		GetOrganDao getOrganIdDao=new GetOrganDao();
		List<Map<String,String>> organ=getOrganIdDao.getOrgan();
		req.setAttribute("organs", organ);
		RequestDispatcher rd=req.getRequestDispatcher("/manage/jsp/addTaxer.jsp");
		rd.forward(req, resp);
	}
	

}
