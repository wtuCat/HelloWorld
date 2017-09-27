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
		//拿到页面的输入框的验证码
		String yan = req.getParameter("captcha");
		//创建jsonon对象
		JSONObject json = new JSONObject();
		//创建输出流
		PrintWriter out = resp.getWriter();
		//获取图片中的验证码
		String yanNew = req.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY).toString();
		//判断是否输入验证码
		if(yan == null|| yan.trim().length() ==0||!yanNew.equalsIgnoreCase(yan)){
					//判断是否是一样的，不区分大小写
						//存入json对象中
						json.put("success", false);
						json.put("msg", "请重新输入验证码");
					}else {
						//存入json对象中
						json.put("success", true);
						json.put("msg", "");
					}
				out.print(json);
				out.flush();
				out.close();
	}
	
}
