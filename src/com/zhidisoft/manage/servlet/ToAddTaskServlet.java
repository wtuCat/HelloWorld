package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.GetOrganDao;
import com.zhidisoft.manage.dao.GetTaxPayerInfoDao;
import com.zhidisoft.manage.dao.GetTaxerDao;
import com.zhidisoft.util.DBUtil;

/**
 * 预先把要新建的任务的基本信息传到前端
 * Servlet implementation class ToAddTaskServlet
 */
@WebServlet("/toAddTaskServlet.do")
public class ToAddTaskServlet extends HttpServlet {

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
		
		String id=req.getParameter("id");
		//下面6步都是向前端传值
		//如果是根据纳税人id传值
		if(id!=null&&id.length()>0){
			//1
			List<Map<String,String>> taxPayer=new GetTaxPayerInfoDao().getTaxPayerInfo(id);
			if(taxPayer!=null){
				req.setAttribute("payer", taxPayer.get(0));
			}
			
			//2
			String sql="select o.organName from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0 and p.id="+id;
			Map<String,String> organ=DBUtil.query(sql).get(0);
			if(organ!=null){
				req.setAttribute("organ", organ);
			}
			
			//3
			String sql2="select i.industryName from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0 and p.id="+id;
			Map<String,String> industryName=DBUtil.query(sql2).get(0);
			if(industryName!=null){
				req.setAttribute("industry", industryName);
			}
			//4
			List<Map<String,String>> list=DBUtil.query("select u.username from tb_taxer t join tb_user u join tb_tax_payer p on p.userId=u.id and u.taxerId=t.id where p.id="+id);
			if(list!=null&&!list.isEmpty()){
				req.setAttribute("user", list.get(0));
			}
		}
		//如果根据纳税人识别号传值
		String payerCode=req.getParameter("payerCode");
		if(payerCode!=null&&payerCode.length()>0){
			List<Map<String,String>> taxPayer=new GetTaxPayerInfoDao().getTaxPayer(payerCode);
			if(taxPayer.size()!=0){
				req.setAttribute("payer", taxPayer.get(0));
			}
			
			//2
			String sql="select o.organName from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0 and p.payerCode="+payerCode;
			List<Map<String,String>> organ=DBUtil.query(sql);
			if(organ.size()!=0){
				req.setAttribute("organ", organ.get(0));
			}
			
			//3
			String sql2="select i.industryName from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0 and p.payerCode="+payerCode;
			List<Map<String,String>> industryName=DBUtil.query(sql2);
			if(industryName.size()>0){
				req.setAttribute("industry", industryName.get(0));
			}
			//4
			List<Map<String,String>> list=DBUtil.query("select u.username from tb_taxer t join tb_user u join tb_tax_payer p on p.userId=u.id and u.taxerId=t.id where p.payerCode="+payerCode);
			if(list!=null&&!list.isEmpty()){
				req.setAttribute("user", list.get(0));
			}
		}
		//5向下达部门选项下拉框传值
		List<Map<String,String>> organs=new GetOrganDao().getOrgan();
		if(organs!=null){
			req.setAttribute("organs", organs);
		}
		
		//6向批准人和执行人选项下拉框传值
		List<Map<String,String>> taxers=new GetTaxerDao().getTaxers();
		if(taxers!=null){
			req.setAttribute("taxers", taxers);
		}
		//请求转发
		req.getRequestDispatcher("/manage/jsp/addTask.jsp").forward(req, resp);
		
	}
	
}
