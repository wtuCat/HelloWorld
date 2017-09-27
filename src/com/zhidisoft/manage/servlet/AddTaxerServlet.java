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

import com.zhidisoft.manage.dao.AddTaxerDao;
import com.zhidisoft.manage.entity.Taxer;
import com.zhidisoft.system.entity.User;

import net.sf.json.JSONObject;

/**
 * 添加办税专员的servlet
 * Servlet implementation class AddTaxerServlet
 */
@WebServlet("/addTaxerServlet.do")
public class AddTaxerServlet extends HttpServlet {

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
		resp.setContentType("text/html");
		//创建办税专员对象
		Taxer taxer=new Taxer();
		
		//创建json对象
		JSONObject json=new JSONObject();
		
		//创建输出流对象，通过IO流把信息反馈到前端
		PrintWriter out=resp.getWriter();
		
		//获取session中的user对象
		User user=(User)req.getSession().getAttribute("user");
		if(user!=null){
			try {
				//从前端获取数据封装到实体类
				BeanUtils.populate(taxer, req.getParameterMap());
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			json.put("success", false);
			json.put("msg", "未登录或已下线，请登录");
			out.println(json);
			out.flush();
			out.close();
			return;
		}
		
		
		
		//判断是否添加成功
		String birthday=taxer.getBirthday();
		if(birthday==""){
			json.put("success", false);
			json.put("msg", "请填写生日");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			AddTaxerDao addTaxerDao=new AddTaxerDao();
			Integer rows=addTaxerDao.AddTax(taxer);
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
	

}
