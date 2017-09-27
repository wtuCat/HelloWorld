package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.zhidisoft.manage.dao.AddTaxPayerDao;
import com.zhidisoft.manage.entity.Payer;
import com.zhidisoft.system.entity.User;

import net.sf.json.JSONObject;

/**
 * ������ӵ���˰����Ϣ
 * Servlet implementation class AddTaxPayerServlet
 */
@WebServlet("/addTaxPayerServlet.do")
public class AddTaxPayerServlet extends HttpServlet {

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
		Payer payer=new Payer();
		JSONObject json=new JSONObject();
		PrintWriter out=resp.getWriter();
//		//��ǰ�˻�ȡ��Ϣ
//		try {
//			BeanUtils.populate(payer, req.getParameterMap());
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (InvocationTargetException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
		payer.setPayerCode(req.getParameter("payerCode"));
		payer.setPayerName(req.getParameter("payerName"));
		payer.setBizAddress(req.getParameter("bizAddress"));
		payer.setBizAddressPhone(req.getParameter("bizAddressPhone"));
		//�ж�һ��˰����ص�id��������Ҫ��
		String taxOrganId=req.getParameter("taxOrganId");
		if(taxOrganId.equals("-1")){
			json.put("success", false);
			json.put("msg", "��ѡ��˰�����");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			payer.setTaxOrganId(taxOrganId);
		}
		//�ж�һЩ��ҵid��������Ҫ��
		String industryId=req.getParameter("industryId");
		if(industryId.equals("-1")){
			json.put("success", false);
			json.put("msg", "��ѡ����ҵ");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			payer.setIndustryId(industryId);
		}
		payer.setBizScope(req.getParameter("bizScope"));
		payer.setLegalPerson(req.getParameter("legalPerson"));
		payer.setLegalIdCard(req.getParameter("legalIdCard"));
		payer.setFinaceName(req.getParameter("finaceName"));
		payer.setFinaceIdCard(req.getParameter("finaceIdCard"));
		String legalIdCardImageURL=req.getParameter("legalIdCardImageURL");
		payer.setLegalIdCardImageURL(legalIdCardImageURL);
		String finaceIdCardImageURL=req.getParameter("finaceIdCardImageURL");
		payer.setFinaceIdCardImageURL(finaceIdCardImageURL);
		//��session�л�ȡ��¼�˵�id
		User user=(User)req.getSession().getAttribute("user");
		if(user==null){
			json.put("success", false);
			json.put("msg", "δ��¼��������,�뷵�ص�¼");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			payer.setUserId(user.getId());
		}
		String InvoiceType=req.getParameter("billType");
		if(InvoiceType.equals("-1")){
			payer.setInvoiceType("");
		}else if(InvoiceType.equals("1")){
			payer.setInvoiceType("��ֵ˰��ͨ��Ʊ");
		}else{
			payer.setInvoiceType("��ֵ˰ר�÷�Ʊ");
		}
		//����һ�����󣬵�����ӷ��������ݿ��������
		Integer rows=new AddTaxPayerDao().addTaxPayer(payer);
		
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
