package com.zhidisoft.manage.dao;

import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;
/**
 * 关于查询税务机关的dao
 * @author Administrator
 *
 */
public class GetOrganDao {
	//查询所有税务机关
	public List<Map<String,String>> getOrgan(){
		String sql="select * from tb_tax_organ where state=0";
		List<Map<String,String>> list=DBUtil.query(sql);
		return list;
	}
	
	//根据id查询到对应的税务机关名称
	public Map<String,String> getOrganName(String id){
		String sql="select * from tb_tax_organ where id="+id;
		return DBUtil.query(sql).get(0);
	}
}
