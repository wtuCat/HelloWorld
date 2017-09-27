package com.zhidisoft.manage.dao;

import com.zhidisoft.util.DBUtil;

/**
 * 
 *删除办税专员的dao
 * 
 */
public class DeleteTaxerDao {
	//根据id删除办税专员
	public Integer DeleteTaxer(String id){
		String sql="update tb_taxer set state=1 where id="+id;
		Integer rows=DBUtil.update(sql);
		return rows;
	}
}
