package com.zhidisoft.manage.dao;

import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;

/**
 * 获取关于行业的dao
 * @author Administrator
 *
 */
public class GetIndustryDao {
	//获取所有行业信息
	public List<Map<String,String>> getIndustry(){
			String sql="select * from tb_industry";
		return DBUtil.query(sql);
	}
}
