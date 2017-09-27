package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.SearchTaskDao;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class SearchTaskServlet
 */
@WebServlet("/searchTaskServlet.do")
public class SearchTaskServlet extends HttpServlet {

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
		String startDate=req.getParameter("startDate")!=""?req.getParameter("startDate"):null;
		String endDate=req.getParameter("endDate")!=""?req.getParameter("endDate"):null;
		//创建对象
		SearchTaskDao searchTask=new SearchTaskDao();
		//获取总记录数
		int count=searchTask.getCount(req.getParameter("payerCode"), req.getParameter("payerName"),req.getParameter("subOrganId"),req.getParameter("industryId"),startDate,endDate);
		//页面数
		Integer pageNum=Integer.parseInt(req.getParameter("page"))>0?Integer.parseInt(req.getParameter("page")):1;
		//页面记录数
		Integer pageSize=Integer.parseInt(req.getParameter("rows"))>0?Integer.parseInt(req.getParameter("rows")):10;
		//调用方法，在将数据传递到前端
		List<Map<String,String>> list=searchTask.searchTask(req.getParameter("payerCode"), req.getParameter("payerName"),req.getParameter("subOrganId"),req.getParameter("industryId"),startDate,endDate,pageNum,pageSize); 
		//new Map 用来接受查询的值，好通过json传入到前端
		Map<Object,Object> result = new HashMap<Object,Object>();
		result.put("total", count);
		result.put("rows",list);
		JSONObject jsonObject = JSONObject.fromObject(result);
		//通过I/O流
		PrintWriter writer = resp.getWriter();
		writer.print(jsonObject.toString());
		writer.flush();
		writer.close();
	}
	

}
