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
 * 获取办税专员详细信息Servlet
 * Servlet implementation class GetTaxerInfoServlet
 */
@WebServlet("/getTaxerInfoServlet.do")
public class GetTaxerInfoServlet extends HttpServlet {

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
		String id=req.getParameter("id");
		//通过id查询到该办税专员的信息,并传递给前端
		List<Map<String,String>> list=new GetTaxerDao().getTaxerInfo(id);
		String organId=list.get(0).get("organId");
		//通过id查询到税务机关的名字,并传递给前端
		Map<String,String> Organ=new GetOrganDao().getOrganName(organId);
		req.setAttribute("organ", Organ);
		req.setAttribute("taxer", list.get(0));
		RequestDispatcher rd=req.getRequestDispatcher("/manage/jsp/taxerInfo.jsp");
		rd.forward(req, resp);
	}
	

}
