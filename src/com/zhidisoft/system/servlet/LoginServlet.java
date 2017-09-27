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
 * �û���¼��Servlet
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
		//��ǰ���õ��û��������룬����������м���
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		UserDao userDao=new UserDao();
		//ͨ���û�����ȡ�û���Ϣ
		User user=userDao.getUser(username);
		//����json����
		JSONObject json=new JSONObject();
		//���û������������dao��֤
		if(user!=null){
			String md5=EncryptUtil.encryptMD5(password+user.getSalt());
			if(md5.equals(user.getPassword())){
				//һ����¼�ɹ�����user������session��
				session.setAttribute("username", username);
				session.setAttribute("user", user);
				//��ȡ�û�id
				String userId=user.getId();
				//��list.get(0)������session��
				String sql="select * from tb_taxer t join tb_user u on t.recordUserId=u.id where u.id="+userId;
				List<Map<String,String>> list=DBUtil.query(sql);
				session.setAttribute("taxer", list.get(0));
				json.put("success", true);
				json.put("msg", "��¼�ɹ�");
			}else{
				json.put("success", false);
				json.put("msg", "�û������������");
			}
			//���û���������cookie��
			Cookie cookie=new Cookie("username", username);
			//�ж�ǰ���Ƿ��ס�û���
			String remUser=req.getParameter("remUser");
			if(remUser.equals("on")){
				resp.addCookie(cookie);
				cookie.setMaxAge(7*24*3600);
			}else{
				resp.addCookie(cookie);
				cookie.setMaxAge(0);
			}
			//����Ϣ���ݵ�ǰ��
			PrintWriter out=resp.getWriter();
			out.println(json);
			out.flush();
			out.close();
		}	
	}
}
