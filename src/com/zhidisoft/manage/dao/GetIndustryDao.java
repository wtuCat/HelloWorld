package com.zhidisoft.manage.dao;

import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;

/**
 * ��ȡ������ҵ��dao
 * @author Administrator
 *
 */
public class GetIndustryDao {
	//��ȡ������ҵ��Ϣ
	public List<Map<String,String>> getIndustry(){
			String sql="select * from tb_industry";
		return DBUtil.query(sql);
	}
}
