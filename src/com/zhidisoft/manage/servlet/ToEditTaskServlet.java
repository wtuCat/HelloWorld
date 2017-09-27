package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.GetOrganDao;
import com.zhidisoft.manage.dao.GetTaxerDao;
import com.zhidisoft.manage.dao.SearchTaskDao;

/**
 * 此servlet是将要修改的任务信息传到前端，以供修改
 * Servlet implementation class ToEditTaskServlet
 */
@WebServlet("/toEditTaskServlet.do")
public class ToEditTaskServlet extends HttpServlet {

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
		
		//获取任务id
		String id=req.getParameter("id");
		req.getSession().setAttribute("sourceId", id);
		//获取纳税人基本信息
		Map<String,String> map=new SearchTaskDao().getTaskInfo(id);
		if(map!=null){
			 //将数据传递到前端
			 req.setAttribute("payer", map);
			 req.setAttribute("organ", map);
			 req.setAttribute("industry", map);
			 req.setAttribute("user", map);
			 req.setAttribute("task", map);
			 req.setAttribute("subOrgan", map);
			 req.setAttribute("approverTaxer", map);
			 req.setAttribute("executeTaxer", map);
		}
		 //给下达部门下拉框传值
		 List<Map<String,String>> organs=new GetOrganDao().getOrgan();
		 req.setAttribute("organs", organs);
		 //给批准人和执行人下拉框传值
		 List<Map<String,String>> taxers=new GetTaxerDao().getTaxers();
		 req.setAttribute("taxers", taxers);
		 req.setAttribute("taxers", taxers);
		 req.getRequestDispatcher("manage/jsp/editTask.jsp").forward(req, resp);
	}
	

}
