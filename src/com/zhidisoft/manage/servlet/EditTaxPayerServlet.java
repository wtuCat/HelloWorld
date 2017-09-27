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
 * 修改纳税人信息，并将数据注入数据库
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
		//判断用户是否在线
		User user=(User)req.getSession().getAttribute("user");
		if(user==null){
			json.put("success", false);
			json.put("msg", "未登录或以下线，请重新登录");
			out.println(json);
			out.flush();
			out.close();
			return;
		}
		//创建一个纳税人对象
		Payer p =new Payer();
		//判断税务机关有没有选择
		String taxOrganId=req.getParameter("taxOrganId");
		if(taxOrganId.equals("-1")){
			json.put("success", false);
			json.put("msg", "请选择税务机关");
			out.println(json);
			out.flush();
			out.close();
			return;
		}
		//判断行业有没有选择
		String industryId=req.getParameter("industryId");
		if(industryId.equals("-1")){
			json.put("success", false);
			json.put("msg", "请选择行业");
			out.println(json);
			out.flush();
			out.close();
			return;
		}
		//将前端数据封装到对象中
		try {
			BeanUtils.populate(p, req.getParameterMap());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//调用方法，判断是否修改成功
		Integer rows=new EditTaxPayerDao().updateTaxPayer(p);
		if(rows>0){
			json.put("success", true);
			json.put("msg", "修改成功");
		}else{
			json.put("success", false);
			json.put("msg", "修改失败");
		}
		//向前端反馈信息
		out.println(json);
		out.flush();
		out.close();
	}
	

}
