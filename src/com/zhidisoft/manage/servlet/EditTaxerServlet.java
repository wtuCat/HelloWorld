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
 * 修改办税专员的信息，此servlet从前端获取数据通过sql修改语句修改数据库数据
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
			//从前端获取数据传给taxer对象
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
		//修改数据库数据，判断rows是否大于0，来判定是否修改成功
		Integer rows=DBUtil.update(sql, obj);
		JSONObject json=new JSONObject();
		if(rows>0){
			json.put("success", true);
			json.put("msg", "修改成功");
		}else{
			json.put("success", false);
			json.put("msg", "修改失败");
		}
		//将提示信息同过json方式传给前端
		PrintWriter out=resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
	}
	
	

}
