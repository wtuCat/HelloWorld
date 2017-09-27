package com.zhidisoft.manage.dao;

import com.zhidisoft.util.DBUtil;

/**
 * 修改任务信息dao
 * @author Administrator
 *
 */
public class EditTaskDao {
	public int editTask(String subOrganId,String approverId,String executeId,String executeTime,String taskState,String id){
		String sql="update tb_tax_source set subOrganId=?,approverId=?,executeId=?,executeTime=?,taskState=? where id="+id;
		Object[] obj={subOrganId,approverId,executeId,executeTime,taskState};
		return DBUtil.update(sql,obj);
	}
}
