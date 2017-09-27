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

import com.zhidisoft.manage.entity.Taxer;
import com.zhidisoft.util.DBUtil;

import net.sf.json.JSONObject;

/**
 * �޸İ�˰רԱ����Ϣ����servlet��ǰ�˻�ȡ����ͨ��sql�޸�����޸����ݿ�����
 * Servlet implementation class EditTaxerServlet
 */
@WebServlet("/editTaxerServlet.do")
public class EditTaxerServlet extends HttpServlet {

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
		Taxer taxer=new Taxer();
		try {
			//��ǰ�˻�ȡ���ݴ���taxer����
			BeanUtils.populate(taxer, req.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Object[] obj={taxer.getTaxerCode(),taxer.getTaxerName(),taxer.getMobile(),taxer.getAddress(),taxer.getSex(),taxer.getBirthday(),taxer.getEmail(),taxer.getOrganId()};
		String sql="update tb_taxer set taxerCode=?,taxerName=?,mobile=?,address=?,sex=?,birthday=?,email=?,organId=? where id="+taxer.getId();
		//�޸����ݿ����ݣ��ж�rows�Ƿ����0�����ж��Ƿ��޸ĳɹ�
		Integer rows=DBUtil.update(sql, obj);
		JSONObject json=new JSONObject();
		if(rows>0){
			json.put("success", true);
			json.put("msg", "�޸ĳɹ�");
		}else{
			json.put("success", false);
			json.put("msg", "�޸�ʧ��");
		}
		//����ʾ��Ϣͬ��json��ʽ����ǰ��
		PrintWriter out=resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
	
	

}
