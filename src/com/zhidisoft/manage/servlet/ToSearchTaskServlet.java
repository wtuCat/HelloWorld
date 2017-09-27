package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.GetIndustryDao;
import com.zhidisoft.manage.dao.GetOrganDao;

/**
 * 向表单中传递相关数据
 * Servlet implementation class ToSearchTaskServlet
 */
@WebServlet("/toSearchTaskServlet.do")
public class ToSearchTaskServlet extends HttpServlet {

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
		
		List<Map<String,String>> organs=new GetOrganDao().getOrgan();
		req.setAttribute("organs", organs);
		
		List<Map<String,String>> industrys=new GetIndustryDao().getIndustry();
		req.setAttribute("industrys", industrys);
		
		req.getRequestDispatcher("/manage/jsp/searchTask.jsp").forward(req, resp);
	}
	
}
