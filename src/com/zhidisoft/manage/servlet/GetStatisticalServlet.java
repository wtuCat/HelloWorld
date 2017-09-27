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
 * Ԥ����ǰ�˴���δ��������ҵҳ������,
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
		//����ܼ�¼��
		GetStatisticalDao getStatistical=new GetStatisticalDao();
		int count=getStatistical.getCount();
		//��÷�ҳ����
		Integer pageNum=req.getParameter("page").isEmpty()?1:Integer.parseInt(req.getParameter("page"));
		Integer pageSize=req.getParameter("rows").isEmpty()?10:Integer.parseInt(req.getParameter("rows"));
		String payerCode=req.getParameter("payerCode");
		String payerName=req.getParameter("payerName");
		List<Map<String,String>> list=getStatistical.getStatisticals(payerCode,payerName,pageNum,pageSize);
		//new Map �������ܲ�ѯ��ֵ����ͨ��json���뵽ǰ��
		Map<Object,Object> map=new HashMap<>();
		map.put("total", count);
		map.put("rows", list);
		JSONObject json=JSONObject.fromObject(map);
		//ͨ��I/O��
		PrintWriter writer = resp.getWriter();
		writer.print(json.toString());
		writer.flush();
		writer.close();
		
	}
	

}
