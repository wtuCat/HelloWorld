package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.GetTaxPayerInfoDao;
import com.zhidisoft.util.DBUtil;

/**
 * 从分页列表中获取信息，把数据传递到详细信息页面
 * Servlet implementation class GetTaxPayerInfoServlet
 */
@WebServlet("/getTaxPayerInfoServlet.do")
public class GetTaxPayerInfoServlet extends HttpServlet {

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
		//获取id
		String id=req.getParameter("id");
		//向前端传递数据
		List<Map<String,String>> TaxPayerInfoList=new GetTaxPayerInfoDao().getTaxPayerInfo(id);
		if(TaxPayerInfoList.size()>0){
			req.setAttribute("payer", TaxPayerInfoList.get(0));
			req.setAttribute("organ", TaxPayerInfoList.get(0));
			req.setAttribute("industry", TaxPayerInfoList.get(0));
		}
		
		List<Map<String,String>> list=DBUtil.query("select * from tb_taxer t join tb_user u join tb_tax_payer p on p.userId=u.id and u.taxerId=t.id where p.id="+id);
		if(list.size()>0){
			req.setAttribute("user", list.get(0));
		}
		//请求转发
		req.getRequestDispatcher("/manage/jsp/payerInfo.jsp").forward(req, resp);
	}
	
}
