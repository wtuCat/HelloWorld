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
 * ��ǰ�˻�ȡ���ݣ������ݿ���ע������
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
			json.put("msg", "δ��¼�������ߣ������µ�¼");
			out.println(json);
			out.flush();
			out.close();
			return;
		}
		
		Source s=new Source();
		//�ж�payerId��taskName
		String payerId=req.getParameter("payerId");
		String taskName=req.getParameter("taskName");
		if(payerId.isEmpty()||taskName.isEmpty()){
			json.put("success", false);
			json.put("msg", "���ʧ��");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			s.setPayerId(payerId);
			s.setTaskName(taskName);
		}
		
		//�ж��´ﲿ���Ƿ���д
		String subOrganId=req.getParameter("subOrganId");
		if(subOrganId==null){
			json.put("success", false);
			json.put("msg", "��ѡ���´ﲿ��");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			s.setSubOrganId(subOrganId);
		}
		//�ж���׼���Ƿ���д
		String approverId=req.getParameter("approverId");
		if(approverId.equals("-1")){
			json.put("success", false);
			json.put("msg", "��ѡ����׼��");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			s.setApproverId(approverId);
		}
		//�ж�ִ�����Ƿ���д
		String executeId=req.getParameter("executeId");
		if(executeId.equals("-1")){
			json.put("success", false);
			json.put("msg", "��ѡ��ִ����");
			out.println(json);
			out.flush();
			out.close();
			return;
		}else{
			s.setExecuteId(executeId);
		}
		//�ж�ִ��ʱ���Ƿ���д
		String executeTime=req.getParameter("executeTime");
		if(executeTime==""){
			json.put("success", false);
			json.put("msg", "������ִ��ʱ��");
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
//			json.put("msg", "��ע������ִ�����");
//			out.println(json);
//			out.flush();
//			out.close();
//			return;
//		}else{
			s.setTaskState(taskState);
//		}
		s.setRecordUserId(user.getId());
		//�ж��Ƿ���ӳɹ�
		Integer rows=new AddTaskDao().addTask(s);
		if(rows>0){
			json.put("success", true);
			json.put("msg", "��ӳɹ�");
		}else{
			json.put("success", false);
			json.put("msg", "���ʧ��");
		}
		out.println(json);
		out.flush();
		out.close();
	}
	

}
