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
		//����һ��json����
		JSONObject json=new JSONObject();
		PrintWriter out=resp.getWriter();
		User user=(User)req.getSession().getAttribute("user");
		if(user!=null){
			String username=req.getParameter("username");
			//��ȡ���ݿ��ж�Ӧ������
			String password=user.getPassword();
			//��ǰ�˻�õľ�������м���
			String oldPassword=EncryptUtil.encryptMD5(req.getParameter("oldPassword")+user.getSalt());
			//�������������ݿ��е�������бȶ�
			if(oldPassword.equals(password)){
				String newPassword=req.getParameter("newPassword");
					String md5Password=EncryptUtil.encryptMD5(newPassword+user.getSalt());
					int rows=new UserDao().updatePassword(md5Password,username);
					if(rows>0){
						json.put("success", true);
						json.put("msg", "�����޸ĳɹ�");
					}else{
						json.put("success", false);
						json.put("msg", "�����޸�ʧ��");
					}
			}else{
				json.put("success", false);
				json.put("msg", "ԭ���벻��ȷ");
			}
			out.println(json);
			out.flush();
			out.close();
		}else{
			json.put("success", false);
			json.put("msg", "���¼");
			out.println(json);
			out.flush();
			out.close();
		}
		
	}
	
}
