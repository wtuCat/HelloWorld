package com.zhidisoft.manage.dao;

import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;

/**
 * ��ѯһ����˰רԱ��ϸ��Ϣdao
 * */
public class GetTaxerDao {
	//ͨ��id��ȡһ����˰רԱ����Ϣ
	public List<Map<String,String>> getTaxerInfo(String id){
		String sql="select * from tb_taxer where id=?";
		Object[] obj={id};
		List<Map<String,String>> list=DBUtil.query(sql, obj);
		return list;
	}
	//��ȡ���а�˰רԱ����Ϣ
	public List<Map<String,String>> getTaxers(){
		String sql="select * from tb_taxer where state=0";
		List<Map<String,String>> list=DBUtil.query(sql);
		return list;
	}
}
