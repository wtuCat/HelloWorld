package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.beanutils.BeanUtils;

import com.zhidisoft.manage.dao.AddTaxerDao;
import com.zhidisoft.manage.entity.Taxer;
import com.zhidisoft.system.entity.User;

import net.sf.json.JSONObject;

/**
 * ��Ӱ�˰רԱ��servlet
 * Servlet implementation class AddTaxerServlet
 */
@WebServlet("/addTaxerServlet.do")
public class AddTaxerServlet extends HttpServlet {

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
		resp.setContentType("text/html");
		//������˰רԱ����
		Taxer taxer=new Taxer();
		
		//����json����
		JSONObject json=new JSONObject();
		
		//�������������ͨ��IO������Ϣ������ǰ��
		PrintWriter out=resp.getWriter();
		
		//��ȡsession�е�user����
		User user=(User)req.getSession().getAttribute("user");
		if(user!=null){
			try {
				//��ǰ�˻�ȡ���ݷ�װ��ʵ����
				BeanUtils.populate(taxer, req.getParameterMap());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			json.put("success", false);
			json.put("msg", "δ��¼�������ߣ����¼");
			out.println(json);
			out.flush();
			out.close();
			return;
		}
		
		
		
		//�ж��Ƿ���ӳɹ�
		String birthday=taxer.getBirthday();
		if(birthday==""){
			json.put("success", false);
			json.put("msg", "����д����");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			AddTaxerDao addTaxerDao=new AddTaxerDao();
			Integer rows=addTaxerDao.AddTax(taxer);
			if(rows>0){
				json.put("success", true);
				json.put("msg", "��ӳɹ�");
			}else{
				json.put("success", false);
				json.put("msg", "���ʧ��");
			}
			out.println(json);
			out.flush();
			out.close();
		}
		
	}
	

}
