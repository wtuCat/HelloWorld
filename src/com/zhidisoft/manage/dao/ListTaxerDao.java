package com.zhidisoft.manage.dao;


import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;
	/**
	 * 办事专员分页dao
	 * 
	 * */
public class ListTaxerDao {
	//获取总记录数
	public int getCount(String taxerName){
		String sql="select count(*) c from tb_taxer where state=0";
		
		if(taxerName.length()>0){
			sql=sql+" and taxerName like '%"+taxerName+"%'";
		}
		return Integer.valueOf(DBUtil.query(sql).get(0).get("c"));
	}
	
	//查询办税专员
	public List<Map<String, String>> getPage(int pageNumber, int pageSize, String taxerName) {
		StringBuilder sb = new StringBuilder();
		//sql语句
		sb.append("select * from tb_taxer where state=0");
		//如果有查询参数就拼接在后面
		if (taxerName != null && taxerName.length() >0) {
			//办税专员名字用模糊查询
			sb.append(" and taxerName like '%"+taxerName+"%'");
		}
		//拼上limit子句
		sb.append(" limit ?,?");
		//占位符的数组
		Object[] obj={(pageNumber-1)*pageSize,pageSize};
		List<Map<String, String>> list = DBUtil.query(sb.toString(),obj);
		return list;
	}
}
