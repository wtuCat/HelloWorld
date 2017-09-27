package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.DeleteTaskDao;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DeleteTaskServlet
 */
@WebServlet("/deleteTaskServlet.do")
public class DeleteTaskServlet extends HttpServlet {

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
		//获取任务id
		String id=req.getParameter("id");
		JSONObject json=new JSONObject();
		if(id!=null){
			int rows=new DeleteTaskDao().deleteTask(id);
			if(rows>0){
				json.put("success", true);
				json.put("msg", "删除成功");
			}else{
				json.put("success", false);
				json.accumulate("msg", "删除失败");
			}
		}else{
			json.put("success", false);
			json.accumulate("msg", "删除失败");
		}
		PrintWriter out=resp.getWriter();
		out.println(json);
		out.flush();
		out.close();
		
	}
}
