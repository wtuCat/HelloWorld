package com.zhidisoft.manage.dao;

import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;

/**
 * 查询一个办税专员详细信息dao
 * */
public class GetTaxerDao {
	//通过id获取一个办税专员的信息
	public List<Map<String,String>> getTaxerInfo(String id){
		String sql="select * from tb_taxer where id=?";
		Object[] obj={id};
		List<Map<String,String>> list=DBUtil.query(sql, obj);
		return list;
	}
	//获取所有办税专员的信息
	public List<Map<String,String>> getTaxers(){
		String sql="select * from tb_taxer where state=0";
		List<Map<String,String>> list=DBUtil.query(sql);
		return list;
	}
}
