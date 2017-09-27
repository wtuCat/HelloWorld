package com.zhidisoft.manage.dao;

import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;

/**
 * 获取关于纳税人的信息的dao
 * @author Administrator
 *
 */
public class GetPayerDao {
	//获取分页总记录数
	public int getcount(){
		String sql="select count(*) c from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0";
		return Integer.valueOf(DBUtil.query(sql).get(0).get("c"));
	}
	
	//多表查询，分页
	public List<Map<String,String >> getPayer(String payerCode,String payerName,Integer pageNumber,Integer pageSize){
		String sql="select * from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0";
		//如果按 纳税人识别号查询
		if(payerCode!=null&&payerCode.length()>0){
			sql=sql+" and payerCode="+payerCode;
		}
		//如果按纳税人名称查询
		if(payerName!=null&&payerName.length()>0){
			sql=sql+" and payerName like '%"+payerName+"%'";
		}
		sql=sql+" limit ?,?";
		Object[] obj={(pageNumber-1)*pageSize,pageSize};
		return DBUtil.query(sql,obj);
	}
}
