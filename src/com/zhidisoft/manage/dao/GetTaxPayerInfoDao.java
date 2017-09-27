package com.zhidisoft.manage.dao;

import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;

/**
 * ��ȡ��˰����ϸ��Ϣ
 * @author Administrator
 *
 */
public class GetTaxPayerInfoDao {
	//ͨ��id��ȡ������¼
	public List<Map<String,String>> getTaxPayerInfo(String id){
		String sql="select * from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0 and p.id="+id;
		return DBUtil.query(sql);
	}
	//ͨ��payerCode��ȡ������¼
	public List<Map<String,String>> getTaxPayer(String payerCode){
		String sql="select * from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0 and p.payerCode="+payerCode;
		return DBUtil.query(sql);
	}
}
