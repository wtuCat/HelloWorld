package com.zhidisoft.manage.dao;

import com.zhidisoft.manage.entity.Source;
import com.zhidisoft.util.DBUtil;

/**
 * 添加新任务dao
 * @author Administrator
 *
 */
public class AddTaskDao {
	//
	public Integer addTask(Source s){
		String sql="insert into tb_tax_source(payerId,taskName,subOrganId,approverId,executeTime,executeId,taskState,recordUserId) values(?,?,?,?,?,?,?,?)";
		Object[] obj={s.getPayerId(),s.getTaskName(),s.getSubOrganId(),s.getApproverId(),s.getExecuteTime(),s.getExecuteId(),s.getTaskState(),s.getRecordUserId()};
		return DBUtil.update(sql, obj);
	}
}
