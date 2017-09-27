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
 * �޸İ�˰רԱ��Ϣ
 * Servlet implementation class ToEditTaxerServlet
 */
@WebServlet("/toEditTaxerServlet.do")
public class ToEditTaxerServlet extends HttpServlet {

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
		//��ȡ��Ҫ�޸ĵİ�˰��Ա��id
		String id=req.getParameter("id");
		//ͨ��id��ѯ���ð�˰רԱ����Ϣ,�����ݸ�ǰ��
		List<Map<String,String>> list=new GetTaxerDao().getTaxerInfo(id);
		req.setAttribute("taxer", list.get(0));
		//��ȡ����˰����ص���Ϣ�������ݸ�ǰ��
		List<Map<String,String>> Organ=new GetOrganDao().getOrgan();
		req.setAttribute("organs", Organ);
		RequestDispatcher rd=req.getRequestDispatcher("/manage/jsp/editTaxer.jsp");
		rd.forward(req, resp);
	}
	
}
