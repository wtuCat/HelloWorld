package com.zhidisoft.manage.dao;

import com.zhidisoft.util.DBUtil;

/**
 * 
 *ɾ����˰רԱ��dao
 * 
 */
public class DeleteTaxerDao {
	//����idɾ����˰רԱ
	public Integer DeleteTaxer(String id){
		String sql="update tb_taxer set state=1 where id="+id;
		Integer rows=DBUtil.update(sql);
		return rows;
	}
}
