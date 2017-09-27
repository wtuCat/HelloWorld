package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.system.dao.UserDao;
import com.zhidisoft.system.entity.User;
import com.zhidisoft.util.EncryptUtil;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UpdatePassword
 */
@WebServlet("/updatePassword.do")
public class UpdatePasswordServlet extends HttpServlet {

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
		//创建一个json对象
		JSONObject json=new JSONObject();
		PrintWriter out=resp.getWriter();
		User user=(User)req.getSession().getAttribute("user");
		if(user!=null){
			String username=req.getParameter("username");
			//获取数据库中对应的密码
			String password=user.getPassword();
			//将前端获得的旧密码进行加密
			String oldPassword=EncryptUtil.encryptMD5(req.getParameter("oldPassword")+user.getSalt());
			//将旧密码与数据库中的密码进行比对
			if(oldPassword.equals(password)){
				String newPassword=req.getParameter("newPassword");
					String md5Password=EncryptUtil.encryptMD5(newPassword+user.getSalt());
					int rows=new UserDao().updatePassword(md5Password,username);
					if(rows>0){
						json.put("success", true);
						json.put("msg", "密码修改成功");
					}else{
						json.put("success", false);
						json.put("msg", "密码修改失败");
					}
			}else{
				json.put("success", false);
				json.put("msg", "原密码不正确");
			}
			out.println(json);
			out.flush();
			out.close();
		}else{
			json.put("success", false);
			json.put("msg", "请登录");
			out.println(json);
			out.flush();
			out.close();
		}
		
	}
	
}
