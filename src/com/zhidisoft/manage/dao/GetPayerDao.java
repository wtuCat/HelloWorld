package com.zhidisoft.manage.dao;

import java.util.List;
import java.util.Map;

import com.zhidisoft.util.DBUtil;

/**
 * ��ȡ������˰�˵���Ϣ��dao
 * @author Administrator
 *
 */
public class GetPayerDao {
	//��ȡ��ҳ�ܼ�¼��
	public int getcount(){
		String sql="select count(*) c from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0";
		return Integer.valueOf(DBUtil.query(sql).get(0).get("c"));
	}
	
	//����ѯ����ҳ
	public List<Map<String,String >> getPayer(String payerCode,String payerName,Integer pageNumber,Integer pageSize){
		String sql="select * from tb_tax_payer p join tb_tax_organ o join tb_industry i on p.taxOrganId=o.id and p.industryId=i.id where p.removeState=0";
		//����� ��˰��ʶ��Ų�ѯ
		if(payerCode!=null&&payerCode.length()>0){
			sql=sql+" and payerCode="+payerCode;
		}
		//�������˰�����Ʋ�ѯ
		if(payerName!=null&&payerName.length()>0){
			sql=sql+" and payerName like '%"+payerName+"%'";
		}
		sql=sql+" limit ?,?";
		Object[] obj={(pageNumber-1)*pageSize,pageSize};
		return DBUtil.query(sql,obj);
	}
}
