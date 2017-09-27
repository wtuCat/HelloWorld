package com.zhidisoft.manage.dao;




import com.zhidisoft.manage.entity.Taxer;
import com.zhidisoft.util.DBUtil;
/**
 * 添加办税专员dao
 * */
public class AddTaxerDao {
	public Integer AddTax(Taxer taxer){
		Object[] obj={taxer.getTaxerCode(),taxer.getTaxerName(),taxer.getMobile(),taxer.getAddress(),taxer.getSex(),taxer.getBirthday(),taxer.getEmail(),taxer.getOrganId()};
		Integer rows=DBUtil.update("insert into tb_taxer (taxerCode,taxerName,mobile,address,sex,birthday,email,organId) values(?,?,?,?,?,?,?,?)",obj);
		return rows;
	}
}
