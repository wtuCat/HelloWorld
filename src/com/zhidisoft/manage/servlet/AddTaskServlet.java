package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import com.zhidisoft.manage.dao.AddTaskDao;
import com.zhidisoft.manage.entity.Source;
import com.zhidisoft.system.entity.User;


import net.sf.json.JSONObject;

/**
 * 从前端获取数据，向数据库中注入数据
 * Servlet implementation class AddTaskServlet
 */
@WebServlet("/addTaskServlet.do")
public class AddTaskServlet extends HttpServlet {

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
		User user=(User)req.getSession().getAttribute("user");
		if(user==null){
			json.put("success", false);
			json.put("msg", "未登录或已下线，请重新登录");
			out.println(json);
			out.flush();
			out.close();
			return;
		}
		
		Source s=new Source();
		//判断payerId，taskName
		String payerId=req.getParameter("payerId");
		String taskName=req.getParameter("taskName");
		if(payerId.isEmpty()||taskName.isEmpty()){
			json.put("success", false);
			json.put("msg", "添加失败");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			s.setPayerId(payerId);
			s.setTaskName(taskName);
		}
		
		//判断下达部门是否填写
		String subOrganId=req.getParameter("subOrganId");
		if(subOrganId==null){
			json.put("success", false);
			json.put("msg", "请选择下达部门");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			s.setSubOrganId(subOrganId);
		}
		//判断批准人是否填写
		String approverId=req.getParameter("approverId");
		if(approverId.equals("-1")){
			json.put("success", false);
			json.put("msg", "请选择批准人");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			s.setApproverId(approverId);
		}
		//判断执行人是否填写
		String executeId=req.getParameter("executeId");
		if(executeId.equals("-1")){
			json.put("success", false);
			json.put("msg", "请选择执行人");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			s.setExecuteId(executeId);
		}
		//判断执行时间是否填写
		String executeTime=req.getParameter("executeTime");
		if(executeTime==""){
			json.put("success", false);
			json.put("msg", "请填入执行时间");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			s.setExecuteTime(executeTime);
		}
		String taskState=req.getParameter("taskState");
//		if(taskState==null){
//			json.put("success", false);
//			json.put("msg", "请注明任务执行情况");
//			out.println(json);
//			out.flush();
//			out.close();
//			return;
//		}else{
			s.setTaskState(taskState);
//		}
		s.setRecordUserId(user.getId());
		//判断是否添加成功
		Integer rows=new AddTaskDao().addTask(s);
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
