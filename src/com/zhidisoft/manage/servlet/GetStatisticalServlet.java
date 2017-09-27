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

import com.zhidisoft.manage.dao.GetStatisticalDao;


import net.sf.json.JSONObject;

/**
 * 预先向前端传递未被调查企业页面数据,
 * Servlet implementation class GetStatisticalServlet
 */
@WebServlet("/getStatisticalServlet.do")
public class GetStatisticalServlet extends HttpServlet {

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
		//获得总记录数
		GetStatisticalDao getStatistical=new GetStatisticalDao();
		int count=getStatistical.getCount();
		//获得分页参数
		Integer pageNum=req.getParameter("page").isEmpty()?1:Integer.parseInt(req.getParameter("page"));
		Integer pageSize=req.getParameter("rows").isEmpty()?10:Integer.parseInt(req.getParameter("rows"));
		String payerCode=req.getParameter("payerCode");
		String payerName=req.getParameter("payerName");
		List<Map<String,String>> list=getStatistical.getStatisticals(payerCode,payerName,pageNum,pageSize);
		//new Map 用来接受查询的值，好通过json传入到前端
		Map<Object,Object> map=new HashMap<>();
		map.put("total", count);
		map.put("rows", list);
		JSONObject json=JSONObject.fromObject(map);
		//通过I/O流
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		writer.flush();
		writer.close();
		
	}
	

}
