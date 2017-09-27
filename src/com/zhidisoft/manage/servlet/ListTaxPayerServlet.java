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
		//��������������һ����Ŀ�жϣ��������ǰ��û�д�����ֵ���ͻὫpage��ֵΪ1��rows��ֵΪ10
		Integer pageNumber = StringUtils.isEmpty(req.getParameter("page"))?1:Integer.parseInt(req.getParameter("page"));
		//rows����ÿҳ������
		Integer pageSize = StringUtils.isEmpty(req.getParameter("rows"))?10:Integer.parseInt(req.getParameter("rows"));
		GetPayerDao Payer=new GetPayerDao();
		//��ȡ�ܼ�¼��
		int count=Payer.getcount();
		//��ȡ������˰���б���Ϣ
		List<Map<String,String >> list=Payer.getPayer(payerCode,payerName,pageNumber,pageSize);
		//new Map �������ܲ�ѯ��ֵ����ͨ��json���뵽ǰ��
		Map<Object,Object> result = new HashMap<>();
		result.put("total", count);
		result.put("rows",list);
		JSONObject jsonObject = JSONObject.fromObject(result);
		//ͨ��I/O��
		PrintWriter writer = resp.getWriter();
		writer.print(jsonObject.toString());
		writer.flush();
		writer.close();
	}
	
}
