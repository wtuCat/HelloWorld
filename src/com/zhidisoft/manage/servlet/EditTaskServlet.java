package com.zhidisoft.manage.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhidisoft.manage.dao.EditTaskDao;
import com.zhidisoft.system.entity.User;

import net.sf.json.JSONObject;

/**
 * ���޸ĺ������ע�뵽���ݿ�
 * Servlet implementation class EditTaskServlet
 */
@WebServlet("/editTaskServlet.do")
public class EditTaskServlet extends HttpServlet {

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
		}
		//��ǰ�˻�ȡ����
		String subOrganId=req.getParameter("subOrganId");
		String approverId=req.getParameter("approverId");
		String executeId=req.getParameter("executeId");
		String executeTime=req.getParameter("executeTime");
		String taskState=req.getParameter("taskState");
		
		//������ע�����ݿ�
		String sourceId=req.getSession().getAttribute("sourceId").toString();
		int rows=new EditTaskDao().editTask(subOrganId, approverId, executeId, executeTime, taskState,sourceId);
		
		if(rows>0){
			json.put("success", true);
			json.put("msg", "�޸ĳɹ�");
		}else{
			json.put("success", false);
			json.put("msg", "�޸�ʧ��");
		}
		
		out.println(json);
		out.flush();
		out.close();
	}
	
}
