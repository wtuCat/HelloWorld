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
 * 保存添加的纳税人信息
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
//		//从前端获取信息
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
		//判断一下税务机关的id符不符合要求
		String taxOrganId=req.getParameter("taxOrganId");
		if(taxOrganId.equals("-1")){
			json.put("success", false);
			json.put("msg", "请选择税务机关");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			payer.setTaxOrganId(taxOrganId);
		}
		//判断一些行业id符不符合要求
		String industryId=req.getParameter("industryId");
		if(industryId.equals("-1")){
			json.put("success", false);
			json.put("msg", "请选择行业");
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
		//从session中获取登录人的id
		User user=(User)req.getSession().getAttribute("user");
		if(user==null){
			json.put("success", false);
			json.put("msg", "未登录或以下线,请返回登录");
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
			payer.setInvoiceType("增值税普通发票");
		}else{
			payer.setInvoiceType("增值税专用发票");
		}
		//创建一个对象，调用添加方法向数据库添加数据
		Integer rows=new AddTaxPayerDao().addTaxPayer(payer);
		
		if(rows>0){
			json.put("success", true);
			json.put("msg", "添加成功");
		}else{
			json.put("success", false);
			json.put("msg", "添加失败");
		}
		out.println(json);
		out.flush();
		out.close();
	}
	

}
