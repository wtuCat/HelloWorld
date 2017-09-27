package com.zhidisoft.manage.dao;

import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;

/**
 * 获取未被调查企业的相关方法
 * @author Administrator
 *
 */
public class GetStatisticalDao {
	//获得总记录数
	public int getCount(){
		String sql="SELECT count(*) c FROM tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id WHERE p.removeState=0 and p.id NOT IN(SELECT s.payerId FROM tb_tax_source s JOIN tb_tax_payer p WHERE p.id=s.payerId)";
		return Integer.valueOf(DBUtil.query(sql).get(0).get("c"));
	}
	//分页查询
	public List<Map<String,String>> getStatisticals(String payerCode,String payerName,Integer pageNum,Integer pageSize){
		String sql="SELECT * FROM tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id WHERE p.removeState=0 and p.id NOT IN(SELECT s.payerId FROM tb_tax_source s JOIN tb_tax_payer p WHERE p.id=s.payerId)";
		//如果按纳税人识别号查
		if(payerCode.length()>0){
			sql=sql+" and p.payerCode="+payerCode;
		}
		//如果按纳税人名称查
		if(payerName.length()>0){
			sql=sql+" and p.payerName like '%"+payerName+"%'";
		}
		sql=sql+" limit ?,?";
		Object[] obj={(pageNum-1)*pageSize,pageSize};
		return DBUtil.query(sql,obj);
	}
	
	
}
