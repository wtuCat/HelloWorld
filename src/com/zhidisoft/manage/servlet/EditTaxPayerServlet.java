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

import com.zhidisoft.manage.entity.Payer;
import com.zhidisoft.system.entity.User;

import net.sf.json.JSONObject;

import com.zhidisoft.manage.dao.EditTaxPayerDao;
/**
 * �޸���˰����Ϣ����������ע�����ݿ�
 * 
 */
@WebServlet("/editTaxPayerServlet.do")
public class EditTaxPayerServlet extends HttpServlet {

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
		JSONObject json=new JSONObject();
		PrintWriter out=resp.getWriter();
		//�ж��û��Ƿ�����
		User user=(User)req.getSession().getAttribute("user");
		if(user==null){
			json.put("success", false);
			json.put("msg", "δ��¼�������ߣ������µ�¼");
			out.println(json);
			out.flush();
			out.close();
			return;
		}
		//����һ����˰�˶���
		Payer p =new Payer();
		//�ж�˰�������û��ѡ��
		String taxOrganId=req.getParameter("taxOrganId");
		if(taxOrganId.equals("-1")){
			json.put("success", false);
			json.put("msg", "��ѡ��˰�����");
			out.println(json);
			out.flush();
			out.close();
			return;
		}
		//�ж���ҵ��û��ѡ��
		String industryId=req.getParameter("industryId");
		if(industryId.equals("-1")){
			json.put("success", false);
			json.put("msg", "��ѡ����ҵ");
			out.println(json);
			out.flush();
			out.close();
			return;
		}
		//��ǰ�����ݷ�װ��������
		try {
			BeanUtils.populate(p, req.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//���÷������ж��Ƿ��޸ĳɹ�
		Integer rows=new EditTaxPayerDao().updateTaxPayer(p);
		if(rows>0){
			json.put("success", true);
			json.put("msg", "�޸ĳɹ�");
		}else{
			json.put("success", false);
			json.put("msg", "�޸�ʧ��");
		}
		//��ǰ�˷�����Ϣ
		out.println(json);
		out.flush();
		out.close();
	}
	

}
