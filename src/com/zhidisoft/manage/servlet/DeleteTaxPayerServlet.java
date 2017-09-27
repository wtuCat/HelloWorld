package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.util.DBUtil;

import net.sf.json.JSONObject;

/**
 * ɾ����˰��
 * Servlet implementation class DeleteTaxPayerServlet
 */
@WebServlet("/deleteTaxPayerServlet.do")
public class DeleteTaxPayerServlet extends HttpServlet {

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
		//��ȡҪ��ɾ������˰�˵�id
		String id=req.getParameter("id");
		Integer rows=DBUtil.update("update tb_tax_payer set removeState=1 where id="+id);
		//����json������ǰ�˴�������
		JSONObject json=new JSONObject();
		//�ж��Ƿ�ɾ���ɹ�
		if(rows>0){
			json.put("success", true);
			json.put("msg", "ɾ���ɹ�");
		}else{
			json.put("success", false);
			json.put("msg", "ɾ��ʧ��");
		}	
		PrintWriter out=resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
			
	}
	

}
