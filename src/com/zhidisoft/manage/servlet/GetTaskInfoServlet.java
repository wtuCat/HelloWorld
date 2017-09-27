package com.zhidisoft.manage.servlet;

import java.io.IOException;

import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zhidisoft.manage.dao.SearchTaskDao;

/**
 * �����������
 * Servlet implementation class GetTaskInfoServlet
 */
@WebServlet("/getTaskInfoServlet.do")
public class GetTaskInfoServlet extends HttpServlet {

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
		Map<String,String> map=null;
		//��ȡ�����id
		String id=req.getParameter("id");
		if(id!=null){
			//ͨ��id��ѯ��������
			map=new SearchTaskDao().getTaskInfo(id);
		}
		 //�����ݴ��ݵ�ǰ��
		 req.setAttribute("payer", map);
		 req.setAttribute("organ", map);
		 req.setAttribute("industry", map);
		 req.setAttribute("user", map);
		 req.setAttribute("task", map);
		 req.setAttribute("subOrgan", map);
		 req.setAttribute("approverTaxer", map);
		 req.setAttribute("executeTaxer", map);
		req.getRequestDispatcher("manage/jsp/taskInfo.jsp").forward(req, resp);
	}
	

}
