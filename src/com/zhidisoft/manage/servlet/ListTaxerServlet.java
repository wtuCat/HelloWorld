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

import com.zhidisoft.manage.dao.ListTaxerDao;


import net.sf.json.JSONObject;

/**
 * ��˰רԱ��ҳServlet
 * Servlet implementation class ListTaxerServlet
 */
@WebServlet("/ListTaxerServlet.do")
public class ListTaxerServlet extends HttpServlet {

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
		resp.setContentType("text/html");
		//��������������һ����Ŀ�жϣ��������ǰ��û�д�����ֵ���ͻὫpage��ֵΪ1��rows��ֵΪ10
		Integer pageNumber = StringUtils.isEmpty(req.getParameter("page"))?1:Integer.parseInt(req.getParameter("page"));
		//rows����ÿҳ������
		Integer pageSize = StringUtils.isEmpty(req.getParameter("rows"))?10:Integer.parseInt(req.getParameter("rows"));
		//����ListTaxerDao����
		ListTaxerDao listTaxerDao=new ListTaxerDao();
		//��ȡ�ܼ�¼��
		String taxerName=req.getParameter("taxerName");
		int count=listTaxerDao.getCount(taxerName);
		List<Map<String, String>> taxerList=listTaxerDao.getPage(pageNumber, pageSize, taxerName);
		//new Map �������ܲ�ѯ��ֵ����ͨ��json���뵽ǰ�ˣ���ߺ��涼����������̶���ʽ
		Map<Object,Object> result = new HashMap<>();
		result.put("total", count);
		result.put("rows",taxerList);
		JSONObject jsonObject = JSONObject.fromObject(result);
		//ͨ��I/O��
		PrintWriter writer = resp.getWriter();
		writer.print(jsonObject.toString());
		writer.flush();
		writer.close();
	}
	
}
