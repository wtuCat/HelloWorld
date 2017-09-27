package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.DeleteTaxerDao;
import com.zhidisoft.system.entity.User;

import net.sf.json.JSONObject;

/**
 * ɾ����˰רԱ
 * Servlet implementation class DeleteTaxerServlet
 */
@WebServlet("/deleteTaxerServlet.do")
public class DeleteTaxerServlet extends HttpServlet {

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
		//����json����
		JSONObject json=new JSONObject();
		//�������������
		PrintWriter out=resp.getWriter();
		//��ȡsession�е�user����
		User user=(User)req.getSession().getAttribute("user");
		if(user==null){
			json.put("success", false);
			json.put("msg", "δ��¼�������ߣ������µ�¼");
			out.println(json.toString());
			out.flush();
			out.close();
			return;
		}else{
			//��ȡҪ��ɾ���İ�˰Աid
			String id=req.getParameter("id");
			Integer rows=new DeleteTaxerDao().DeleteTaxer(id);
			//�ж��Ƿ�ɾ���ɹ�
			if(rows>0){
				json.put("success", true);
				json.put("msg", "ɾ���ɹ�");
			}else{
				json.put("success", false);
				json.put("msg", "ɾ��ʧ��");
			}
			out.println(json.toString());
			out.flush();
			out.close();
		}
		
	}
	

}
