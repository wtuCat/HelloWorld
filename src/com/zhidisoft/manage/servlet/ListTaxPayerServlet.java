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

import org.apache.commons.lang.StringUtils;

import com.zhidisoft.manage.dao.GetPayerDao;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class ListTaxPayerServlet
 */
@WebServlet("/listTaxPayerServlet.do")
public class ListTaxPayerServlet extends HttpServlet {

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
		String payerCode=req.getParameter("payerCode");
		String payerName=req.getParameter("payerName");
		System.out.println();
		//这边括号里面的是一个三目判断，就是如果前段没有传过来值，就会将page赋值为1，rows赋值为10
		Integer pageNumber = StringUtils.isEmpty(req.getParameter("page"))?1:Integer.parseInt(req.getParameter("page"));
		//rows代表每页多少条
		Integer pageSize = StringUtils.isEmpty(req.getParameter("rows"))?10:Integer.parseInt(req.getParameter("rows"));
		GetPayerDao Payer=new GetPayerDao();
		//获取总记录数
		int count=Payer.getcount();
		//获取所有纳税人列表信息
		List<Map<String,String >> list=Payer.getPayer(payerCode,payerName,pageNumber,pageSize);
		//new Map 用来接受查询的值，好通过json传入到前端
		Map<Object,Object> result = new HashMap<>();
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
