package com.zhidisoft.system.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;

/**
 * Servlet implementation class CheckCaptchaServlet
 */
@WebServlet("/CheckCaptchaServlet")
public class CheckCaptchaServlet extends HttpServlet {

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
		resp.setContentType("text/jsp;charset=UTF-8");
		//�õ�ҳ�����������֤��
		String yan = req.getParameter("captcha");
		//����jsonon����
		JSONObject json = new JSONObject();
		//���������
		PrintWriter out = resp.getWriter();
		//��ȡͼƬ�е���֤��
		String yanNew = req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString();
		//�ж��Ƿ�������֤��
		if(yan == null|| yan.trim().length() ==0||!yanNew.equalsIgnoreCase(yan)){
					//�ж��Ƿ���һ���ģ������ִ�Сд
						//����json������
						json.put("success", false);
						json.put("msg", "������������֤��");
					}else {
						//����json������
						json.put("success", true);
						json.put("msg", "");
					}
				out.print(json);
				out.flush();
				out.close();
	}
	
}
