package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.GetIndustryDao;
import com.zhidisoft.manage.dao.GetOrganDao;
import com.zhidisoft.system.entity.User;
import com.zhidisoft.util.DBUtil;

/**
 * Servlet implementation class ToAddTaxPayerServlet
 */
@WebServlet("/toAddTaxPayerServlet.do")
public class ToAddTaxPayerServlet extends HttpServlet {

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
		//获取税务机关信息
		List<Map<String,String>> organs=new GetOrganDao().getOrgan();
		req.setAttribute("organs", organs);
		//获取行业信息
		List<Map<String,String>> industry=new GetIndustryDao().getIndustry();
		req.setAttribute("industrys", industry);
		//获取录入人员
		User user=(User)req.getSession().getAttribute("user");
		//获取当前时间
		Date date=new Date();
		req.setAttribute("curDate", new SimpleDateFormat("yyyy-MM-dd").format(date));
		Map<String,String> taxer=DBUtil.query("select * from tb_taxer t join tb_user u  on u.taxerId=t.id where u.id="+user.getId()).get(0);
		req.setAttribute("user", taxer);
		RequestDispatcher rd=req.getRequestDispatcher("/manage/jsp/addTaxPayer.jsp");
		rd.forward(req, resp);
	}
	

}
