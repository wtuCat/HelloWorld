package com.zhidisoft.manage.dao;

import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;

/**
 * ��ȡδ��������ҵ����ط���
 * @author Administrator
 *
 */
public class GetStatisticalDao {
	//����ܼ�¼��
	public int getCount(){
		String sql="SELECT count(*) c FROM tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id WHERE p.removeState=0 and p.id NOT IN(SELECT s.payerId FROM tb_tax_source s JOIN tb_tax_payer p WHERE p.id=s.payerId)";
		return Integer.valueOf(DBUtil.query(sql).get(0).get("c"));
	}
	//��ҳ��ѯ
	public List<Map<String,String>> getStatisticals(String payerCode,String payerName,Integer pageNum,Integer pageSize){
		String sql="SELECT * FROM tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id WHERE p.removeState=0 and p.id NOT IN(SELECT s.payerId FROM tb_tax_source s JOIN tb_tax_payer p WHERE p.id=s.payerId)";
		//�������˰��ʶ��Ų�
		if(payerCode.length()>0){
			sql=sql+" and p.payerCode="+payerCode;
		}
		//�������˰�����Ʋ�
		if(payerName.length()>0){
			sql=sql+" and p.payerName like '%"+payerName+"%'";
		}
		sql=sql+" limit ?,?";
		Object[] obj={(pageNum-1)*pageSize,pageSize};
		return DBUtil.query(sql,obj);
	}
	
	
}
