package com.zhidisoft.manage.dao;

import com.zhidisoft.util.DBUtil;

/**
 * ͨ������idɾ��һ�������¼
 * @author Administrator
 *
 */
public class DeleteTaskDao {
	public int deleteTask(String id){
		String sql="update tb_tax_source set removeState=1 where id="+id;
		return DBUtil.update(sql);
	}
}
