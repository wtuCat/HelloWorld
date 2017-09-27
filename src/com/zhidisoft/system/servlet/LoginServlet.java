package com.zhidisoft.system.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.zhidisoft.system.dao.UserDao;
import com.zhidisoft.system.entity.User;
import com.zhidisoft.util.DBUtil;
import com.zhidisoft.util.EncryptUtil;

import net.sf.json.JSONObject;

/**
 * 用户登录的Servlet
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

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
		resp.setContentType("text/json,charset=utf-8");
		HttpSession session=req.getSession();
		//从前端拿到用户名和密码，并将密码进行加密
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		UserDao userDao=new UserDao();
		//通过用户名获取用户信息
		User user=userDao.getUser(username);
		//创建json对象
		JSONObject json=new JSONObject();
		//将用户名和密码放在dao验证
		if(user!=null){
			String md5=EncryptUtil.encryptMD5(password+user.getSalt());
			if(md5.equals(user.getPassword())){
				//一旦登录成功几把user保存在session中
				session.setAttribute("username", username);
				session.setAttribute("user", user);
				//获取用户id
				String userId=user.getId();
				//将list.get(0)保存在session中
				String sql="select * from tb_taxer t join tb_user u on t.recordUserId=u.id where u.id="+userId;
				List<Map<String,String>> list=DBUtil.query(sql);
				session.setAttribute("taxer", list.get(0));
				json.put("success", true);
				json.put("msg", "登录成功");
			}else{
				json.put("success", false);
				json.put("msg", "用户名或密码错误");
			}
			//将用户名保存在cookie中
			Cookie cookie=new Cookie("username", username);
			//判断前端是否记住用户名
			String remUser=req.getParameter("remUser");
			if(remUser.equals("on")){
				resp.addCookie(cookie);
				cookie.setMaxAge(7*24*3600);
			}else{
				resp.addCookie(cookie);
				cookie.setMaxAge(0);
			}
			//将信息传递到前端
			PrintWriter out=resp.getWriter();
			out.println(json);
			out.flush();
			out.close();
		}	
	}
}
