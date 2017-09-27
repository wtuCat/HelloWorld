package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.GetIndustryDao;
import com.zhidisoft.manage.dao.GetOrganDao;
import com.zhidisoft.util.DBUtil;

/**
 * 预先把要修改的纳税人信息传到前端
 * 
 */
@WebServlet("/toEditTaxPayerServlet.do")
public class ToEditTaxPayerServlet extends HttpServlet {

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

		//获取要修改的纳税人id
		String id=req.getParameter("id");
		String sql="select * from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0 and p.id="+id;
		Map<String,String> taxPayer=DBUtil.query(sql).get(0);
		//传值
		req.setAttribute("payer", taxPayer);
		//给税务机关下拉框传值
		String sql1="select o.id,o.organName from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0 and p.id="+id;
		Map<String,String> organ=DBUtil.query(sql1).get(0);
		req.setAttribute("organ", organ);
		List<Map<String,String>> organs=new GetOrganDao().getOrgan();
		req.setAttribute("organs", organs);
		//给行业选项下拉框传值
		String sql2="select i.id,i.industryName from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0 and p.id="+id;
		Map<String,String> industry=DBUtil.query(sql2).get(0);
		req.setAttribute("industry", industry);
		List<Map<String,String>> industrys=new GetIndustryDao().getIndustry();
		req.setAttribute("industrys", industrys);
		//
		List<Map<String,String>> list=DBUtil.query("select * from tb_taxer t join tb_user u join tb_tax_payer p on p.userId=u.id and u.taxerId=t.id where p.id="+id);
		req.setAttribute("user", list.get(0));
		req.getRequestDispatcher("/manage/jsp/editTaxPayer.jsp").forward(req, resp);
	}
	

}
